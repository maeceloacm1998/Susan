package com.app.core.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayDark
import com.app.core.ui.theme.GrayLight
import com.app.core.ui.theme.SoftBlack
import com.app.core.ui.theme.Success
import com.app.core.ui.theme.SuccessLight

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = SoftBlack,
    containerColor: Color = GrayDark,
    imageVector: ImageVector? = null,
    painter: Painter? = null,
    iconColor: Color = Color.White,
    loading: Boolean = false,
    isChecked: Boolean = false,
    onButtonListener: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(CustomDimensions.padding50),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isChecked) SuccessLight else containerColor,
            disabledContainerColor = GrayLight
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (imageVector != null) {
                        Image(
                            modifier = Modifier.padding(end = CustomDimensions.padding10),
                            imageVector = imageVector,
                            colorFilter = ColorFilter.tint(iconColor),
                            contentDescription = "icon_button",
                        )
                    }

                    if (painter != null) {
                        Image(
                            modifier = Modifier.padding(end = CustomDimensions.padding10),
                            painter = painter,
                            colorFilter = ColorFilter.tint(iconColor),
                            contentDescription = "icon_button",
                        )
                    }

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
}

@Preview
@Composable
fun ButtonComponentPreview() {
    ButtonComponent(title = "Buscar emergência", onButtonListener = { /*TODO*/ })
}