package com.app.home.feature.chatcontainer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.BackgroundUserChat
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.Primary
import com.app.home.feature.chat.data.external.models.EmergencyData
import com.app.home.feature.chat.data.models.ChatMessage
import com.app.home.feature.chat.data.models.ChatMessageAuthor


@Composable
fun ChatContainerTextMessageScreen(
    chatMessage: ChatMessage
) {
    val messageColor = if (chatMessage.author == ChatMessageAuthor.USER.author) Primary else Color.White
    val backgroundMessageColor = if (chatMessage.author == ChatMessageAuthor.USER.author) BackgroundUserChat else Primary
    val marginStart = if (chatMessage.author == ChatMessageAuthor.USER.author) CustomDimensions.padding50 else CustomDimensions.padding1
    val marginEnd = if (chatMessage.author == ChatMessageAuthor.USER.author) CustomDimensions.padding1 else CustomDimensions.padding50

    Box(
        modifier = Modifier.padding(start = marginStart, end = marginEnd)
    ) {
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
                text =  chatMessage.author,
                color = messageColor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = chatMessage.message,
                color = messageColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun ChatContainerTextMessageScreenPreview() {
    ChatContainerTextMessageScreen(
        chatMessage = ChatMessage(
            author = "User",
            message = "Hello",
            type = ChatMessageAuthor.USER.author,
            timestamp = 0L,
            id = 0,
            timer = 0,
            isLoading = false,
            extraItems = EmergencyData()
        )
    )
}