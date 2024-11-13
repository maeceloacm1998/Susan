package com.app.home.components.audiochat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.Primary
import com.app.core.ui.theme.Secondary

@Composable
fun AudioRecordingButton(
    modifier: Modifier = Modifier,
    timer: Int,
    minHeight: Dp = 40.dp,
    maxHeight: Dp = 55.dp,
    minWidth: Dp = 0.dp,
    maxWidth: Dp = 280.dp
) {
    Row(
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(CustomDimensions.padding40))
            .heightIn(min = minHeight, max = maxHeight)
            .widthIn(min = minWidth, max = maxWidth)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(end = CustomDimensions.padding8),
                imageVector = Icons.Filled.Mic,
                contentDescription = "Audio Icon",
                tint = Secondary
            )
            Text(
                text = String.format("00:%02d", timer),
                color = Primary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun AudioRecordingButtonPreview() {
    AudioRecordingButton(timer = 0)
}