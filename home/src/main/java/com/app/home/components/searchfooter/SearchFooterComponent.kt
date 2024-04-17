package com.app.home.components.searchfooter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.app.core.components.textfield.TextFieldCustom
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayDark

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
                label = "Busque por um hospital",
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
                    text = "Habilitar sua localização em tempo real",
                    color = GrayDark,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}