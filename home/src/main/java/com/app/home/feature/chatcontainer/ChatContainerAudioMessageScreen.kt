package com.app.home.feature.chatcontainer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.BackgroundUserChat
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayLight
import com.app.core.ui.theme.Primary
import com.app.core.ui.theme.Secondary
import com.app.core.utils.Utils.formatSeconds
import com.app.home.feature.chat.data.external.models.EmergencyData
import com.app.home.feature.chat.data.models.ChatMessage
import com.app.home.feature.chat.data.models.ChatMessageAuthor
import com.valentinilk.shimmer.shimmer

@Composable
fun ChatContainerAudioMessageScreen(
    chatMessage: ChatMessage,
    startAudio: Boolean,
    progressAudio: Float,
    timerAudio: Int,
    onClickPlayAudio: (chatMessage: ChatMessage) -> Unit
) {

    val messageColor =
        if (chatMessage.author == ChatMessageAuthor.USER.author) Primary else Color.White
    val backgroundMessageColor =
        if (chatMessage.author == ChatMessageAuthor.USER.author) BackgroundUserChat else Primary
    val marginStart =
        if (chatMessage.author == ChatMessageAuthor.USER.author) CustomDimensions.padding50 else CustomDimensions.padding1
    val marginEnd =
        if (chatMessage.author == ChatMessageAuthor.USER.author) CustomDimensions.padding1 else CustomDimensions.padding50

    if (chatMessage.isLoading) {
        ShimmeringAudioPlaceholder()
    } else {
        Box(modifier = Modifier.padding(start = marginStart, end = marginEnd)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        backgroundMessageColor,
                        shape = RoundedCornerShape(CustomDimensions.padding10)
                    )
                    .padding(
                        vertical = CustomDimensions.padding14,
                        horizontal = CustomDimensions.padding18
                    )
            ) {
                Text(
                    text = chatMessage.author,
                    color = messageColor,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )

                AudioMessage(
                    chatMessage = chatMessage,
                    startAudio = startAudio,
                    progressAudio = progressAudio,
                    timerAudio = timerAudio,
                    messageColor = messageColor,
                    onClickPlayAudio = onClickPlayAudio
                )
            }
        }
    }
}

@Composable
fun AudioMessage(
    chatMessage: ChatMessage,
    startAudio: Boolean,
    progressAudio: Float,
    timerAudio: Int,
    messageColor: Color,
    onClickPlayAudio: (chatMessage: ChatMessage) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { onClickPlayAudio(chatMessage) }
        ) {
            if (startAudio) {
                Icon(
                    imageVector = Icons.Default.Pause,
                    contentDescription = "Pause Icon",
                    tint = messageColor
                )
            } else {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Icon",
                    tint = messageColor
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = CustomDimensions.padding10),
                progress = progressAudio,
                color = Secondary,
                trackColor = messageColor,
            )

            Text(
                modifier = Modifier.padding(top = CustomDimensions.padding5),
                text = formatSeconds(timerAudio),
                color = messageColor,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ShimmeringAudioPlaceholder() {
    Column(
        modifier = Modifier
            .shimmer()
            .fillMaxWidth()
            .padding(
                end = CustomDimensions.padding50
            )
            .background(
                GrayLight,
                shape = RoundedCornerShape(CustomDimensions.padding10)
            )
            .padding(
                vertical = CustomDimensions.padding14,
                horizontal = CustomDimensions.padding18
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(CustomDimensions.padding24)
                .background(Color.LightGray)
                .padding(bottom = CustomDimensions.padding8),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = CustomDimensions.padding10),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = CustomDimensions.padding20, height = CustomDimensions.padding20)
                    .background(Color.LightGray)
                    .padding(end = CustomDimensions.padding14),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray),
            )
        }

    }
}

@Preview
@Composable
fun ChatContainerAudioMessageScreenPreview() {
    ChatContainerAudioMessageScreen(
        chatMessage = ChatMessage(
            author = "User",
            message = "Hello",
            type = ChatMessageAuthor.USER.author,
            timestamp = 0L,
            id = 0,
            timer = 0,
            isLoading = false,
            extraItems = EmergencyData()
        ),
        startAudio = false,
        progressAudio = 0.5f,
        timerAudio = 10,
        onClickPlayAudio = {}
    )
}

@Preview(showBackground = true)
@Composable
fun AudioMessagePreview() {
    AudioMessage(
        chatMessage = ChatMessage(
            author = "User",
            message = "Hello",
            type = ChatMessageAuthor.USER.author,
            timestamp = 0L,
            id = 0,
            timer = 0,
            isLoading = false,
            extraItems = EmergencyData()
        ),
        messageColor = Primary,
        startAudio = false,
        progressAudio = 0.5f,
        timerAudio = 10,
        onClickPlayAudio = {}
    )
}

@Preview
@Composable
fun ShimmeringAudioPlaceholderPreview() {
    ShimmeringAudioPlaceholder()
}
