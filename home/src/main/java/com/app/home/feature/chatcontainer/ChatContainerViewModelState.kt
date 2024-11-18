package com.app.home.feature.chatcontainer

sealed interface ChatContainerViewModelUiState {
    val isLoading: Boolean
    val progressAudio: Float
    val timerAudio: Int
    val startAudio: Boolean

    data class AudioChat(
        override val isLoading: Boolean,
        override val progressAudio: Float,
        override val timerAudio: Int,
        override val startAudio: Boolean
    ) : ChatContainerViewModelUiState

    data class TextChat(
        override val isLoading: Boolean,
        override val progressAudio: Float,
        override val timerAudio: Int,
        override val startAudio: Boolean
    ) : ChatContainerViewModelUiState
}

data class ChatContainerViewModelState(
    val isLoading: Boolean = false,
    val progressAudio: Float = 0.1f,
    val timerAudio: Int = 0,
    val startAudio: Boolean = false
) {
    fun toUiState(): ChatContainerViewModelUiState = if (isLoading) {
        ChatContainerViewModelUiState.AudioChat(
            isLoading = isLoading,
            progressAudio = progressAudio,
            timerAudio = timerAudio,
            startAudio = startAudio
        )
    } else {
        ChatContainerViewModelUiState.TextChat(
            isLoading = isLoading,
            progressAudio = progressAudio,
            timerAudio = timerAudio,
            startAudio = startAudio
        )
    }
}