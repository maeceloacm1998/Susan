package com.app.home.feature.chatcontainer

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.service.phonedialog.OpenPhoneDialogUseCase
import com.app.core.utils.Utils.ONE_SECOND_IN_MILLISECONDS
import com.app.core.utils.Utils.onCopyTextToClipboard
import com.app.core.utils.Utils.onOpenGoogleMaps
import com.app.core.utils.Utils.onOpenUber
import com.app.core.utils.Utils.onOpenWaze
import com.app.home.feature.chat.data.models.ChatMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class ChatContainerViewModel(
    private val chatMessage: ChatMessage,
    private val openPhoneDialogUseCase: OpenPhoneDialogUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(ChatContainerViewModelState(isLoading = false))

    val uiState = viewModelState
        .map(ChatContainerViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    private var tts: TextToSpeech? = null
    private var timerJob: Job? = null

    fun onStartAudio(context: Context, chat: ChatMessage) {
        if (!viewModelState.value.startAudio) {
            onStartAudioState()
            onStartSpeakText(context, chatMessage.message)
            onStartAudioTimer(chatMessage.timer)
        } else {
            onStopAudio()
        }
    }

    fun onHandleCopyAddress(context: Context) {
        onCopyTextToClipboard(context = context, message = chatMessage.message)
    }

    fun onHandlePhoneNumber(context: Context) {
        chatMessage.extraItems?.phoneNumber?.let {
            openPhoneDialogUseCase(
                context = context,
                phone = it
            )
        }
    }

    fun onHandleGoogleMaps(context: Context) {
        onOpenGoogleMaps(
            context = context,
            lat = chatMessage.extraItems?.lat ?: 0.0,
            lng = chatMessage.extraItems?.lng ?: 0.0
        )
    }

    fun onHandleWaze(context: Context) {
        onOpenWaze(
            context = context,
            lat = chatMessage.extraItems?.lat ?: 0.0,
            lng = chatMessage.extraItems?.lng ?: 0.0
        )
    }

    fun onHandleUber(context: Context) {
       onOpenUber(
            context = context,
            lat = chatMessage.extraItems?.lat ?: 0.0,
            lng = chatMessage.extraItems?.lng ?: 0.0
        )
    }

    private fun onStopAudio() {
        tts?.stop()
        timerJob?.cancel()
        onStopAudioState()
        updateProgressAudio(0.1f)
        updateTimerAudio(0)
    }

    private fun handleCalculateProgress(currentTime: Int, totalTime: Int): Float {
        if (totalTime == 0) return 0.1f
        val progress = 0.1f + (currentTime.toFloat() / totalTime.toFloat()) * 0.9f
        return progress.coerceIn(0.1f, 1f)
    }

    private fun onStartAudioTimer(totalTime: Int) {
        timerJob = viewModelScope.launch {
            for (i in 0..totalTime) {
                if (i == totalTime) {
                    onStopAudio()
                    break
                }
                updateTimerAudio(i)
                updateProgressAudio(handleCalculateProgress(i, totalTime))
                delay(ONE_SECOND_IN_MILLISECONDS)
            }
        }
    }

    private fun onStartAudioState() {
        viewModelState.update { state ->
            state.copy(startAudio = true)
        }
    }

    private fun onStopAudioState() {
        viewModelState.update { state ->
            state.copy(startAudio = false)
        }
    }

    private fun updateProgressAudio(progress: Float) {
        viewModelState.update { state ->
            state.copy(progressAudio = progress)
        }
    }

    private fun updateTimerAudio(time: Int) {
        viewModelState.update { state ->
            state.copy(timerAudio = time)
        }
    }

    private fun onStartSpeakText(context: Context, text: String) {
        tts = TextToSpeech(context) { status ->
            if (status != TextToSpeech.ERROR) {
                tts?.language = Locale("pt", "BR")
                tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }
}