package com.app.home.feature.chat.ui

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.utils.Utils
import com.app.home.feature.chat.data.models.ChatMessage
import com.app.home.feature.chat.data.models.ChatMessageAuthor
import com.app.home.feature.chat.data.models.ChatMessageType
import com.app.home.feature.chat.domain.CreateMessageInternalUseCase
import com.app.home.feature.chat.domain.GetMessageInternalUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class ChatViewModel(
    private val getMessageInternalUseCase: GetMessageInternalUseCase,
    private val createMessageInternalUseCase: CreateMessageInternalUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(ChatViewModelState(isLoading = false))
    private var tts: TextToSpeech? = null

    val uiState = viewModelState
        .map(ChatViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onStartRecordingAudio() {
        viewModelState.update { it.copy(recordAudio = true) }
    }

    fun onUpdateMessage() {
        viewModelScope.launch {
            val messages = getMessageInternalUseCase()
            viewModelState.update { it.copy(messageList = messages) }
        }
    }

    fun onCreateRecordingMessage(context: Context, text: String, timer: Int) {
        val message = ChatMessage(
            id = Utils.generateRandomId(),
            message = text,
            timestamp = Utils.getCurrentTimestamp(),
            author = ChatMessageAuthor.USER.author,
            type = ChatMessageType.AUDIO.type,
        )
        viewModelScope.launch {
            createMessageInternalUseCase(message)
            onUpdateMessage()
        }
    }

    fun onStopRecordingAudio() {
        viewModelState.update { it.copy(recordAudio = false) }
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