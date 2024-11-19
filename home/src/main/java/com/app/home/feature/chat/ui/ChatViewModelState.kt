package com.app.home.feature.chat.ui

import com.app.home.feature.chat.data.models.ChatMessage
import com.app.home.feature.home.data.models.ErrorMessage
import com.google.android.gms.maps.model.LatLng

sealed interface ChatUiState {
    val isLoading: Boolean
    val isLocationActive: Boolean
    val errorMessages: ErrorMessage?
    val currentLocation: LatLng?
    val recordAudio: Boolean
    val userMessage: String

    data class NoHasChatMessage(
        override val currentLocation: LatLng?,
        override val isLoading: Boolean,
        override val isLocationActive: Boolean,
        override val errorMessages: ErrorMessage?,
        override val recordAudio: Boolean,
        override val userMessage: String
    ) : ChatUiState

    data class HasChatMessage(
        override val isLoading: Boolean,
        override val isLocationActive: Boolean,
        override val errorMessages: ErrorMessage?,
        override val currentLocation: LatLng?,
        override val recordAudio: Boolean,
        override val userMessage: String,
        val messageList: List<ChatMessage>
    ) : ChatUiState
}

data class ChatViewModelState(
    val messageList: List<ChatMessage>? = null,
    val currentLocation: LatLng? = null,
    val isLoading: Boolean = false,
    val isLocationActive: Boolean = false,
    val errorMessages: ErrorMessage? = null,
    val recordAudio: Boolean = false,
    val userMessage: String = ""
) {
    fun toUiState(): ChatUiState = if (messageList.isNullOrEmpty()) {
        ChatUiState.NoHasChatMessage(
            isLoading = isLoading,
            isLocationActive = isLocationActive,
            errorMessages = errorMessages,
            currentLocation = currentLocation,
            recordAudio = recordAudio,
            userMessage = userMessage
        )
    } else {
        ChatUiState.HasChatMessage(
            isLoading = isLoading,
            isLocationActive = isLocationActive,
            errorMessages = errorMessages,
            currentLocation = currentLocation,
            messageList = messageList,
            recordAudio = recordAudio,
            userMessage = userMessage
        )
    }
}