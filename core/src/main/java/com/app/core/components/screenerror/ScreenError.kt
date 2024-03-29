package com.app.core.components.screenerror

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.components.buttons.ButtonComponent
import com.app.core.ui.theme.Background
import com.app.core.ui.theme.CustomDimensions

@Composable
fun ScreenError(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(
                height = CustomDimensions.padding300,
                width = CustomDimensions.padding300
            ),
            painter = painterResource(id = androidx.core.R.drawable.ic_call_decline_low),
            contentDescription = "conection error"
        )

        Text(
            text = "Ops... Aconteceu algum problema.",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(top = CustomDimensions.padding16))

        ButtonComponent(
            modifier = Modifier.padding(horizontal = CustomDimensions.padding16),
            title = "Tentar novamente",
            titleColor = Color.White,
            onButtonListener = { /*TODO*/ }
        )
    }
}

@Preview(device = "id:pixel_4a")
@Composable
fun ScreenErrorPreview() {
    ScreenError()
}