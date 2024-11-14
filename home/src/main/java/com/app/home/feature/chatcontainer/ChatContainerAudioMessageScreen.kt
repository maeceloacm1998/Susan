package com.app.home.feature.chatcontainer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.app.core.ui.theme.Primary
import com.app.core.ui.theme.Secondary
import com.app.home.feature.chat.data.models.ChatMessageAuthor

@Composable
fun ChatContainerAudioMessageScreen(
    type: ChatMessageAuthor = ChatMessageAuthor.USER,
    onClickPlayAudio: () -> Unit
) {

    val messageColor = if (type == ChatMessageAuthor.USER) Primary else Color.White
    val backgroundMessageColor = if (type == ChatMessageAuthor.USER) BackgroundUserChat else Primary
    val marginStart =
        if (type == ChatMessageAuthor.USER) CustomDimensions.padding50 else CustomDimensions.padding1
    val marginEnd =
        if (type == ChatMessageAuthor.USER) CustomDimensions.padding1 else CustomDimensions.padding50

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
                modifier = Modifier.padding(bottom = CustomDimensions.padding5),
                text = type.author,
                color = messageColor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            AudioMessage(
                messageColor = messageColor,
                onClickPlayAudio = onClickPlayAudio
            )
        }
    }
}

@Composable
fun AudioMessage(
    messageColor: Color,
    onClickPlayAudio: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onClickPlayAudio
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play",
                tint = messageColor
            )
        }

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth().padding(top = CustomDimensions.padding10),
                progress = {0.1f},
                color = Secondary,
                trackColor = messageColor,
            )

            Text(
                modifier = Modifier.padding(top = CustomDimensions.padding5),
                text = "00:06",
                color = messageColor,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun ChatContainerAudioMessageScreenPreview() {
    ChatContainerAudioMessageScreen(
        type = ChatMessageAuthor.USER,
        onClickPlayAudio = {}
    )
}

@Preview(showBackground = true)
@Composable
fun AudioMessagePreview() {
    AudioMessage(
        messageColor = Primary,
        onClickPlayAudio = {}
    )
}
