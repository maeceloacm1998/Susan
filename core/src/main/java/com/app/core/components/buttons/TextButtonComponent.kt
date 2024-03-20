package com.app.core.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayLight
import com.app.core.ui.theme.Primary


@Composable
fun TextButtonComponent(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = Color.LightGray,
    containerColor: Color = Color.Transparent,
    loading: Boolean = false,
    onButtonListener: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = GrayLight
        ),
        enabled = !loading,
        onClick = onButtonListener,
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = Primary, modifier = Modifier.size(
                    width = CustomDimensions.padding30,
                    height = CustomDimensions.padding30
                )
            )
        } else {
            Text(
                text = title,
                color = titleColor,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun TextButtonComponentPreview() {
    TextButtonComponent(
        title = "Teste",
        onButtonListener = {}
    )
}