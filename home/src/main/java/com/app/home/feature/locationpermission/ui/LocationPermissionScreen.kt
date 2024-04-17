package com.app.home.feature.locationpermission.ui

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.components.buttons.ButtonComponent
import com.app.core.components.buttons.OutlinedButtonComponent
import com.app.core.ui.theme.Background
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.Primary
import com.app.core.ui.theme.Secondary
import com.app.home.R

@Composable
fun LocationPermissionScreen(
    onActiveAutomateAccess: () -> Unit,
    onActiveManualAccess: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextContainer()

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding20))

        ButtonComponent(
            modifier = Modifier.padding(horizontal = CustomDimensions.padding24),
            title = "Conceder acesso a localização",
            titleColor = Color.White,
            containerColor = Primary,
            onButtonListener = onActiveAutomateAccess
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding5))

        OutlinedButtonComponent(
            modifier = Modifier.padding(horizontal = CustomDimensions.padding24),
            title = "Ativar de forma manual",
            borderColor = Color.Transparent,
            titleColor = Primary,
            onButtonListener = onActiveManualAccess
        )
    }
}

@Composable
fun TextContainer() {
    Image(
        modifier = Modifier.size(
            height = CustomDimensions.padding150,
            width = CustomDimensions.padding150
        ),
        painter = painterResource(id = R.drawable.ic_search_med_location_img),
        contentDescription = "location image 1"
    )

    Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding16))

    Text(
        text = "Qual é a sua localização",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        color = Primary
    )

    Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding10))

    Text(
        modifier = Modifier.padding(horizontal = CustomDimensions.padding24),
        text = "Encontre hospitais próximos rapidamente e com facilidade.",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        color = Secondary
    )
}

@Preview
@Composable
fun LocationPermissionScreenPreview() {
    LocationPermissionScreen(
        onActiveAutomateAccess = {},
        onActiveManualAccess = {}
    )
}