package com.app.home.feature.chatcontainer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.home.feature.chat.data.models.ChatMessage
import com.app.home.feature.chat.data.models.ChatMessageType

@Composable
fun ChatContainerRoute(
    chatMessage: ChatMessage,
    chatContainerViewModel: ChatContainerViewModel
) {
    val context = LocalContext.current
    val uiState by chatContainerViewModel.uiState.collectAsStateWithLifecycle()

    ChatContainerRoute(
        chatMessage = chatMessage,
        startAudio = uiState.startAudio,
        progressAudio = uiState.progressAudio,
        timerAudio = uiState.timerAudio,
        onClickPlayAudio = {
            chatContainerViewModel.onStartAudio(context = context)
        }
    )

    if (chatMessage.extraItems?.name?.isNotBlank() == true) {
        ChatContainerExtrasScreen(
            extraItems = chatMessage.extraItems!!,
            onClickPhoneNumber = {
                chatContainerViewModel.onHandlePhoneNumber(context = context)
            },
            onClickCopyAddress = {
                chatContainerViewModel.onHandleCopyAddress(context = context)
            },
            onClickUber = {
                chatContainerViewModel.onHandleUber(context = context)
            },
            onClickGoogleMaps = {
                chatContainerViewModel.onHandleGoogleMaps(context = context)
            },
            onClickWaze = {
                chatContainerViewModel.onHandleWaze(context = context)
            },
        )
    }
}

@Composable
fun ChatContainerRoute(
    chatMessage: ChatMessage,
    startAudio: Boolean,
    progressAudio: Float,
    timerAudio: Int,
    onClickPlayAudio: (chatMessage: ChatMessage) -> Unit
) {
    when (chatMessage.type) {
        ChatMessageType.TEXT.type -> {
            ChatContainerTextMessageScreen(
                chatMessage = chatMessage
            )
        }

        ChatMessageType.AUDIO.type -> {
            ChatContainerAudioMessageScreen(
                chatMessage = chatMessage,
                startAudio = startAudio,
                progressAudio = progressAudio,
                timerAudio = timerAudio,
                onClickPlayAudio = onClickPlayAudio
            )
        }
    }
}