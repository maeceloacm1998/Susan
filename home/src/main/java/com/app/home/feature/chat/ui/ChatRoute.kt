package com.app.home.feature.chat.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.core.components.screenloading.ScreenLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatRoute(
    chatViewModel: ChatViewModel = koinViewModel()
) {
    val uiState by chatViewModel.uiState.collectAsStateWithLifecycle()

    ChatRoute(
        uiState = uiState,
        onCreateNewChatListener = { }
    )
}

@Composable
fun ChatRoute(
    uiState: ChatUiState,
    onCreateNewChatListener: () -> Unit,

    ) {
    when {
        uiState.isLoading -> ScreenLoading()
        else -> ChatScreen(
            uiState = uiState,
            onCreateNewChatListener = onCreateNewChatListener
        )
    }
}