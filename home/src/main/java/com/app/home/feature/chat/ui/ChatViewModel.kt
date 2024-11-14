package com.app.home.feature.chat.ui

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.utils.Utils
import com.app.core.utils.Utils.onLaunchAudioRecording
import com.app.home.feature.chat.data.models.ChatMessage
import com.app.home.feature.chat.data.models.ChatMessageAuthor
import com.app.home.feature.chat.data.models.ChatMessageType
import com.app.home.feature.chat.domain.CreateMessageInternalUseCase
import com.app.home.feature.chat.domain.GetChatEmergencyUseCase
import com.app.home.feature.chat.domain.GetMessageInternalUseCase
import com.app.home.feature.chat.domain.UpdateMessageInternalUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class ChatViewModel(
    private val getMessageInternalUseCase: GetMessageInternalUseCase,
    private val createMessageInternalUseCase: CreateMessageInternalUseCase,
    private val getChatEmergencyUseCase: GetChatEmergencyUseCase,
    private val updateMessageInternalUseCase: UpdateMessageInternalUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(ChatViewModelState(isLoading = false))

    private val _timerFlow = MutableStateFlow(0)
    val timerFlow: StateFlow<Int> = _timerFlow.asStateFlow()

    private var tts: TextToSpeech? = null

    val uiState = viewModelState
        .map(ChatViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onUpdateMessage() {
        viewModelScope.launch {
            val messages = getMessageInternalUseCase()
            val sortedMessages = messages.sortedBy { it.timestamp }
            viewModelState.update { it.copy(messageList = sortedMessages) }
        }
    }

    fun onStartRecordingAudio(speech: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        onLaunchAudioRecording(speech)
        onChangeRecordingAudioState(true)
        startTimer()
    }

    fun onStopRecordingAudio() {
        viewModelState.update { it.copy(recordAudio = false) }
        _timerFlow.value = 0
    }

    fun onCreateRecordingMessage(textAudio: String, timer: Int) {
        val chatMessage = onCreateChatMessage(
            message = textAudio,
            author = ChatMessageAuthor.USER.author,
            type = ChatMessageType.AUDIO.type,
            timer = timer,
            isLoading = true
        )

        viewModelScope.launch {
            onCreateMessageInternal(chatMessage)
            onCreateLoadingMessage(chatMessage)
        }
    }

    fun onCreateSendMessage(message: String) {
        val chatMessage = onCreateChatMessage(
            message = message,
            author = ChatMessageAuthor.USER.author,
            type = ChatMessageType.TEXT.type,
            isLoading = true
        )

        viewModelScope.launch {
            onCreateMessageInternal(chatMessage)
            onCreateLoadingMessage(chatMessage)
        }
    }

    private fun onCreateChatMessage(
        message: String,
        author: String,
        type: String,
        timer: Int = 0,
        isLoading: Boolean = false
    ): ChatMessage {
        return ChatMessage(
            id = Utils.generateRandomId(),
            message = message,
            author = author,
            type = type,
            timer = timer,
            timestamp = Utils.getCurrentTimestamp(),
            isLoading = isLoading
        )
    }

    private suspend fun onCreateLoadingMessage(chatMessage: ChatMessage) {
        val chatSusanMessage = onCreateChatMessage(
            message = "Loading...",
            author = ChatMessageAuthor.SUSAN.author,
            type = chatMessage.type,
            isLoading = true
        )

        onCreateMessageInternal(chatSusanMessage)
        fetchSusanEmergency(
            chatMessage = chatSusanMessage,
            userMessage = chatMessage.message
        )
    }

    private suspend fun fetchSusanEmergency(chatMessage: ChatMessage, userMessage: String) {
        val result = getChatEmergencyUseCase(userMessage)
        result.onSuccess { response ->
            chatMessage.apply {
                isLoading = false
                message = response.result.message
                extraItems = response.result.data
            }

            onUpdateMessageInternal(chatMessage)
        }.onFailure { throwable ->
            val x = ""
        }
    }

    private suspend fun onCreateMessageInternal(chatMessage: ChatMessage) {
        createMessageInternalUseCase(chatMessage)
        onUpdateMessage()
    }

    private suspend fun onUpdateMessageInternal(chatMessage: ChatMessage) {
        updateMessageInternalUseCase(chatMessage)
        onUpdateMessage()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (viewModelState.value.recordAudio) {
                delay(1000L)
                _timerFlow.value += 1
            }
        }
    }

    private fun onChangeRecordingAudioState(state: Boolean) {
        viewModelState.update { it.copy(recordAudio = state) }
    }

    private fun speakText(context: Context, text: String) {
        tts = TextToSpeech(context) { status ->
            if (status != TextToSpeech.ERROR) {
                tts?.language = Locale("pt", "BR")
                tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }
}