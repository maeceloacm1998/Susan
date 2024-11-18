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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.BackgroundUserChat
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayLight
import com.app.core.ui.theme.Primary
import com.app.home.feature.chat.data.external.models.EmergencyData
import com.app.home.feature.chat.data.models.ChatMessage
import com.app.home.feature.chat.data.models.ChatMessageAuthor
import com.valentinilk.shimmer.shimmer


@Composable
fun ChatContainerTextMessageScreen(
    chatMessage: ChatMessage
) {
    val messageColor = if (chatMessage.author == ChatMessageAuthor.USER.author) Primary else Color.White
    val backgroundMessageColor = if (chatMessage.author == ChatMessageAuthor.USER.author) BackgroundUserChat else Primary
    val marginStart = if (chatMessage.author == ChatMessageAuthor.USER.author) CustomDimensions.padding50 else CustomDimensions.padding1
    val marginEnd = if (chatMessage.author == ChatMessageAuthor.USER.author) CustomDimensions.padding1 else CustomDimensions.padding50

    if(chatMessage.isLoading) {
        ShimmeringTextPlaceholder()
    } else {
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
                    modifier = Modifier.padding(bottom = CustomDimensions.padding8),
                    text =  chatMessage.author,
                    color = messageColor,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = chatMessage.message,
                    color = messageColor,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
fun ShimmeringTextPlaceholder() {
    Row(
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
        horizontalArrangement = Arrangement.spacedBy(CustomDimensions.padding14),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(CustomDimensions.padding16),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(CustomDimensions.padding24)
                    .background(Color.LightGray),
            )
            Box(
                modifier = Modifier
                    .size(CustomDimensions.padding120, CustomDimensions.padding20)
                    .background(Color.LightGray),
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

@Preview
@Composable
fun ShimmeringPlaceholderPreview() {
    ShimmeringTextPlaceholder()
}
