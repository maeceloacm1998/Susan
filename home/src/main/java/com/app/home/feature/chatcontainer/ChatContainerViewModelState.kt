package com.app.home.feature.chatcontainer

sealed interface ChatContainerViewModelUiState {
    val isLoading: Boolean

    data class AudioChat(
        override val isLoading: Boolean
    ) : ChatContainerViewModelUiState

    data class TextChat(
        override val isLoading: Boolean
    ) : ChatContainerViewModelUiState
}

data class ChatContainerViewModelState(
    val isLoading: Boolean = false
) {
    fun toUiState(): ChatContainerViewModelUiState = if (isLoading) {
        ChatContainerViewModelUiState.AudioChat(
            isLoading = isLoading
        )
    } else {
        ChatContainerViewModelUiState.TextChat(
            isLoading = isLoading
        )
    }
}