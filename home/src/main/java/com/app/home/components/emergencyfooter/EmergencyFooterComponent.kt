package com.app.home.components.emergencyfooter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CrisisAlert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.components.buttons.ButtonComponent
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.RedDark

@Composable
fun EmergencyFooterComponent(
    modifier: Modifier = Modifier,
    onClickSearchEmergency: () -> Unit,
    onClickEmergencyPhone: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = CustomDimensions.padding20,
                vertical = CustomDimensions.padding16
            ),
        ) {
            ButtonComponent(
                modifier = Modifier.height(CustomDimensions.padding40),
                title = "Clique aqui para ligar para o Samu - 192",
                containerColor = Color.White,
                titleColor = RedDark,
                onButtonListener = onClickEmergencyPhone
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = CustomDimensions.padding20,
                        topEnd = CustomDimensions.padding20
                    )
                )
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(
                        horizontal = CustomDimensions.padding20,
                        vertical = CustomDimensions.padding24
                    )
            ) {
                ButtonComponent(
                    title = "Buscar emergência",
                    containerColor = RedDark,
                    titleColor = Color.White,
                    imageVector = Icons.Filled.CrisisAlert,
                    onButtonListener = onClickSearchEmergency
                )
            }
        }
    }
}

@Preview
@Composable
fun FooterComponentPreview() {
    EmergencyFooterComponent(
        onClickEmergencyPhone = {},
        onClickSearchEmergency = {}
    )
}