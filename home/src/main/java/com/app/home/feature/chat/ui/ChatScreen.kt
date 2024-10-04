package com.app.home.feature.chat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.components.screenerror.ScreenError
import com.app.core.components.screenloading.LoadingContent
import com.app.core.components.screenloading.ScreenLoading
import com.app.core.ui.theme.Background
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.Primary
import com.app.home.components.audiochat.AudioRecordingButton
import com.app.home.components.expandablebutton.ExpandableButton
import com.app.home.components.textfieldchat.TextFieldChat
import com.app.home.components.topbar.ToolbarCustom

@Composable
fun ChatScreen(
    uiState: ChatUiState,
    onCreateNewChatListener: () -> Unit,
    onPressStartAudio: () -> Unit,
    onTimerChange: (Int) -> Unit,
    timer: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        ToolbarCustom(onCreateNewChatListener = onCreateNewChatListener)
        ChatContainer(modifier = Modifier.weight(1f), uiState = uiState)
        MessageContainer(
            uiState = uiState,
            onPressStartAudio = onPressStartAudio,
            onTimerChange = onTimerChange,
            timer = timer
        )
    }
}

@Composable
fun ChatContainer(
    modifier: Modifier = Modifier,
    uiState: ChatUiState,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Background)
    ) {
        LoadingContent(
            empty = when (uiState) {
                is ChatUiState.HasChatMessage -> false
                is ChatUiState.NoHasChatMessage -> true
            },
            emptyContent = { ScreenError() },
            loading = uiState.isLoading,
            loadingContent = { ScreenLoading() },
            content = {
                // Ensure this does not recursively call itself
//                ChatContent(uiState = uiState)
            }
        )
    }
}

@Composable
fun MessageContainer(
    uiState: ChatUiState,
    onPressStartAudio: () -> Unit,
    onTimerChange: (Int) -> Unit,
    timer: Int = 0
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary)
            .height(CustomDimensions.padding120)
            .padding(CustomDimensions.padding20),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var message by rememberSaveable { mutableStateOf("") }

            if (uiState.recordAudio) {
                AudioRecordingButton(
                    modifier = Modifier.weight(1f),
                    isPressed = uiState.recordAudio,
                    onTimerChange = onTimerChange,
                    timer = timer,
                )
            } else {
                TextFieldChat(
                    value = message,
                    onValueChange = { message = it },
                    label = "Digite sua emergência aqui...",
                    onPressDoneListener = { /* Handle done action */ },
                    modifier = Modifier.weight(1f)
                )
            }

            Column(
                modifier = Modifier
                    .width(CustomDimensions.padding300)
                    .weight(0.25f),
                horizontalAlignment = Alignment.End
            ) {
                ExpandableButton(
                    modifier = Modifier.padding(start = CustomDimensions.padding10),
                    onClick = { /* Handle click action */ },
                    isPressed = uiState.recordAudio,
                    onPressStart = onPressStartAudio,
                )
            }
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        uiState = ChatUiState.HasChatMessage(
            messageList = listOf(),
            currentLocation = null,
            errorMessages = null,
            isLoading = false,
            isLocationActive = false,
            recordAudio = false
        ),
        onCreateNewChatListener = { },
        onPressStartAudio = { },
        onTimerChange = { },
        timer = 0
    )
}

@Preview
@Composable
fun ChatInitialScreenPreview() {
    ChatScreen(
        uiState = ChatUiState.NoHasChatMessage(
            currentLocation = null,
            errorMessages = null,
            isLoading = false,
            isLocationActive = false,
            recordAudio = false
        ),
        onCreateNewChatListener = { },
        onPressStartAudio = { },
        onTimerChange = { },
        timer = 0
    )
}

@Preview
@Composable
fun ChatContainerPreview() {
    ChatContainer(
        uiState = ChatUiState.HasChatMessage(
            messageList = listOf(),
            currentLocation = null,
            errorMessages = null,
            isLoading = false,
            isLocationActive = false,
            recordAudio = false
        )
    )
}

@Preview
@Composable
fun MessageContainerPreview() {
    MessageContainer(
        uiState = ChatUiState.HasChatMessage(
            messageList = listOf(),
            currentLocation = null,
            errorMessages = null,
            isLoading = false,
            isLocationActive = false,
            recordAudio = false
        ),
        onPressStartAudio = { },
        onTimerChange = { },
        timer = 0
    )
}

@Preview
@Composable
fun ToolbarPreview() {
    ToolbarCustom(onCreateNewChatListener = { })
}
