package com.app.home.feature.chatcontainer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.core.ui.theme.CustomDimensions
import com.app.home.feature.chat.data.models.ChatMessage
import com.app.home.feature.chat.data.models.ChatMessageType
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatContainerRoute(
    messageList: List<ChatMessage>,
    chatContainerViewModel: ChatContainerViewModel = koinViewModel()
) {
    val uiState by chatContainerViewModel.uiState.collectAsStateWithLifecycle()

    ChatContainerRoute(
        uiState = uiState,
        messageList = messageList
    )
}

@Composable
fun ChatContainerRoute(
    uiState: ChatContainerViewModelUiState,
    messageList: List<ChatMessage>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(CustomDimensions.padding20),
        verticalArrangement = Arrangement.spacedBy(CustomDimensions.padding8)
    ) {
        items(messageList) { item ->
            when (item.type) {
                ChatMessageType.TEXT.type -> {
                    ChatContainerTextMessageScreen(
                        chatMessage = item
                    )
                }

                ChatMessageType.AUDIO.type -> {
                    ChatContainerAudioMessageScreen(
                        chatMessage = item,
                        onClickPlayAudio = {}
                    )
                }
            }
        }
    }
}