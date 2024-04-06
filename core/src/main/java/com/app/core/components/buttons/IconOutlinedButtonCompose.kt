package com.app.core.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayDark
import com.app.core.ui.theme.GrayLight

@Composable
fun IconOutlinedButtonComponent(
    icon: ImageVector = Icons.Filled.ArrowForward,
    iconColor: Color = Color.White,
    borderColor: Color = GrayDark,
    containerColor: Color = Color.Transparent,
    loading: Boolean = false,
    isChecked: Boolean = false,
    onButtonListener: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        modifier = modifier
            .size(CustomDimensions.padding70),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = GrayLight
        ),
        border = BorderStroke(
            width = CustomDimensions.padding1,
            color = borderColor
        ),
        enabled = !loading,
        onClick = onButtonListener,
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = Color.White, modifier = Modifier.size(
                    width = CustomDimensions.padding20,
                    height = CustomDimensions.padding20
                )
            )
        } else {
            if (isChecked) {
                Image(
                    imageVector = Icons.Filled.Check,
                    colorFilter = ColorFilter.tint(iconColor),
                    contentDescription = "check",
                )
            } else {
                Image(
                    imageVector = icon,
                    colorFilter = ColorFilter.tint(iconColor),
                    contentDescription = "check",
                )
            }
        }
    }
}

@Preview
@Composable
fun IconOutlinedButtonComponentPreview() {
    IconOutlinedButtonComponent(onButtonListener = {})
}