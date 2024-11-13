package com.app.home.feature.chatcontainer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatContainerRoute(
    chatContainerViewModel: ChatContainerViewModel = koinViewModel()
) {
    val uiState by chatContainerViewModel.uiState.collectAsStateWithLifecycle()

    ChatContainerRoute(
        uiState = uiState,
    )
}

@Composable
fun ChatContainerRoute(
    uiState: ChatContainerViewModelUiState
) {

}