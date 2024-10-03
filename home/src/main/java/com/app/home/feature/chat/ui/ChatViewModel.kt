package com.app.home.feature.chat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.home.feature.chat.domain.GetChatCurrentLocationUseCase
import com.app.home.feature.chat.domain.ObserveChatCurrentLocationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ChatViewModel(
    private val getChatCurrentLocationUseCase: GetChatCurrentLocationUseCase,
    private val observeChatCurrentLocationUseCase: ObserveChatCurrentLocationUseCase,
): ViewModel() {
    private val viewModelState = MutableStateFlow(ChatViewModelState(isLoading = false))

    val uiState = viewModelState
        .map(ChatViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        viewModelState.update { it.copy(messageList = listOf()) }
    }
}