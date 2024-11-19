package com.app.home.feature.chat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import org.koin.androidx.compose.get
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
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
import com.app.home.feature.chatcontainer.ChatContainerRoute
import com.app.home.feature.chatcontainer.ChatContainerViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf

@Composable
fun ChatScreen(
    uiState: ChatUiState,
    timer: Int,
    onCreateNewChatListener: () -> Unit,
    onPressStartAudio: () -> Unit,
    onPressSendMessage: (message: String) -> Unit,
    onChangeUserMessage: (message: String) -> Unit = {}
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
            timer = timer,
            onPressStartAudio = onPressStartAudio,
            onPressSendMessage = onPressSendMessage,
            onChangeUserMessage = onChangeUserMessage
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
                check(uiState is ChatUiState.HasChatMessage)
                val chatState = rememberLazyListState()

                val coroutineScope = rememberCoroutineScope()
                var showScrollToBottomButton by remember { mutableStateOf(false) }

                LaunchedEffect(chatState) {
                    snapshotFlow { chatState.layoutInfo.visibleItemsInfo }
                        .collect { visibleItems ->
                            showScrollToBottomButton = visibleItems.lastOrNull()?.index != chatState.layoutInfo.totalItemsCount - 1
                        }
                }

                LaunchedEffect(uiState.messageList) {
                    coroutineScope.launch {
                        chatState.scrollToItem(chatState.layoutInfo.totalItemsCount - 1)
                    }
                }

                LazyColumn(
                    state = chatState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(CustomDimensions.padding20),
                    verticalArrangement = Arrangement.spacedBy(CustomDimensions.padding8)
                ) {
                    items(uiState.messageList) { item ->
                        val itemViewModel: ChatContainerViewModel =
                            get(parameters = { parametersOf(item) })

                        ChatContainerRoute(
                            chatMessage = item,
                            chatContainerViewModel = itemViewModel
                        )
                    }
                }

                if (showScrollToBottomButton) {
                    FloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                chatState.animateScrollToItem(chatState.layoutInfo.totalItemsCount - 1)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(CustomDimensions.padding14)
                    ) {
                        Icon(Icons.Filled.ArrowDownward, contentDescription = "Scroll to bottom")
                    }
                }
            }
        )
    }
}

@Composable
fun MessageContainer(
    uiState: ChatUiState,
    onPressStartAudio: () -> Unit,
    onPressSendMessage: (message: String) -> Unit,
    onChangeUserMessage: (message: String) -> Unit = {},
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
            if (uiState.recordAudio) {
                AudioRecordingButton(
                    modifier = Modifier.weight(1f),
                    timer = timer,
                )
            } else {
                TextFieldChat(
                    value = uiState.userMessage,
                    onValueChange = { onChangeUserMessage(it) },
                    label = "Digite sua emergência aqui...",
                    onPressDoneListener = { onPressSendMessage(uiState.userMessage) },
                    modifier = Modifier.weight(1f)
                )
            }

            Column(
                modifier = Modifier
                    .width(CustomDimensions.padding300)
                    .weight(0.25f),
                horizontalAlignment = Alignment.End
            ) {
                if (uiState.userMessage.isNotBlank()) {
                    ExpandableButton(
                        modifier = Modifier.padding(start = CustomDimensions.padding10),
                        icon = Icons.Filled.Send,
                        isPressed = false,
                        onPressStart = { onPressSendMessage(uiState.userMessage) },
                    )
                } else {
                    ExpandableButton(
                        modifier = Modifier.padding(start = CustomDimensions.padding10),
                        isPressed = uiState.recordAudio,
                        onPressStart = onPressStartAudio,
                    )
                }
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
            recordAudio = false,
            userMessage = ""
        ),
        onCreateNewChatListener = { },
        onPressStartAudio = { },
        onPressSendMessage = { },
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
            recordAudio = false,
            userMessage = ""
        ),
        onCreateNewChatListener = { },
        onPressStartAudio = { },
        onPressSendMessage = { },
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
            recordAudio = false,
            userMessage = ""
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
            recordAudio = false,
            userMessage = ""
        ),
        onPressStartAudio = { },
        onPressSendMessage = { },
        timer = 0
    )
}

@Preview
@Composable
fun ToolbarPreview() {
    ToolbarCustom(onCreateNewChatListener = { })
}
