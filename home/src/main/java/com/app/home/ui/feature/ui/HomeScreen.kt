package com.app.home.ui.feature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.Background
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayDark
import com.app.home.ui.components.textfield.TextFieldCustom

@Composable
fun HomeScreen(uiState: HomeUiState.HasHospital) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().weight(1f)
        ) {

        }
        FooterComponent()
    }
}

@Composable
fun FooterComponent(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
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
            TextFieldCustom(
                modifier = Modifier.fillMaxWidth(),
                startIconImageVector = Icons.Filled.Search,
                label = "Digite o nome do hospital",
                onChangeListener = {})

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    imageVector = Icons.Filled.GpsFixed,
                    contentDescription = "GPS",
                    colorFilter = ColorFilter.tint(color = GrayDark),
                    modifier = Modifier.padding(horizontal = CustomDimensions.padding10)
                )
                Text(
                    text = "Presscione aqui para ativar sua localização",
                    color = GrayDark,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    val uiState = HomeUiState.HasHospital(
        isLoading = false,
        errorMessages = null
    )
    HomeScreen(uiState = uiState)
}

@Preview
@Composable
fun FooterComponentPreview() {
    FooterComponent()
}