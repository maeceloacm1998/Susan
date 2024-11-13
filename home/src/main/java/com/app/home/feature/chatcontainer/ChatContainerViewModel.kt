package com.app.home.feature.chatcontainer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ChatContainerViewModel: ViewModel() {
    private val viewModelState = MutableStateFlow(ChatContainerViewModelState(isLoading = false))

    val uiState = viewModelState
        .map(ChatContainerViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )
}