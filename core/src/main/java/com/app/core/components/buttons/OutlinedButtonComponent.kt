package com.app.core.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayDark
import com.app.core.ui.theme.SoftBlack
import com.app.core.ui.theme.Success

@Composable
fun OutlinedButtonComponent(
    title: String,
    titleColor: Color = SoftBlack,
    borderColor: Color = GrayDark,
    loading: Boolean = false,
    isChecked: Boolean = false,
    onButtonListener: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(CustomDimensions.padding50),
        border = BorderStroke(
            width = CustomDimensions.padding1,
            color = borderColor
        ),
        enabled = !loading,
        onClick = onButtonListener,
    ) {
        if (isChecked) {
            Image(
                imageVector = Icons.Filled.Check,
                colorFilter = ColorFilter.tint(Success),
                contentDescription = "check",
            )
        } else {
            if (loading) {
                CircularProgressIndicator(
                    color = Color.White, modifier = Modifier.size(
                        width = CustomDimensions.padding20,
                        height = CustomDimensions.padding20
                    )
                )
            } else {
                Text(
                    text = title,
                    color = titleColor,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}