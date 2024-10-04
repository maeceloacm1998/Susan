package com.app.home.feature.chat.ui

import android.content.Context
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.io.File
import java.io.IOException
import java.util.Locale

class ChatViewModel : ViewModel() {
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

    fun onCreateMessage(context: Context, text: String, timer: Int) {
       // criar mensagem e chamar a api
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