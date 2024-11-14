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
import com.app.home.feature.chat.data.models.ChatMessageAuthor


@Composable
fun ChatContainerTextMessageScreen(
    type: ChatMessageAuthor = ChatMessageAuthor.SUSAN
) {
    val messageColor = if (type == ChatMessageAuthor.USER) Primary else Color.White
    val backgroundMessageColor = if (type == ChatMessageAuthor.USER) BackgroundUserChat else Primary
    val marginStart = if (type == ChatMessageAuthor.USER) CustomDimensions.padding50 else CustomDimensions.padding1
    val marginEnd = if (type == ChatMessageAuthor.USER) CustomDimensions.padding1 else CustomDimensions.padding50

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
                text = "Voce Digitou:",
                color = messageColor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Sofri um acidente de carro e minha perna esta doendo muito.",
                color = messageColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun ChatContainerTextMessageScreenPreview() {
    ChatContainerTextMessageScreen()
}