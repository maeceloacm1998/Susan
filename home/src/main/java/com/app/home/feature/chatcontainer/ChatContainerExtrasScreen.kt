package com.app.home.feature.chatcontainer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GoogleMapsIconColor
import com.app.core.ui.theme.SoftBlack
import com.app.core.ui.theme.WazeBackgroundColor
import com.app.home.R

@Composable
fun ChatContainerExtrasScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(CustomDimensions.padding10))
            .padding(CustomDimensions.padding20)
    ) {
        Text(
            text = "Hospital metropolitano Dr. Célio de Castro",
            style = MaterialTheme.typography.bodyLarge,
            color = SoftBlack,
            fontWeight = FontWeight.Bold,
        )

        Address()
        PhoneNumber()
        Spacer(modifier = Modifier.padding(vertical  = CustomDimensions.padding10))
        LocationServices()
    }
}

@Composable
fun Address(
    onClickPhoneNumber: (phoneNumber: String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickPhoneNumber("31999999999") }
            .padding(vertical = CustomDimensions.padding10),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(end = CustomDimensions.padding10),
                imageVector = Icons.Filled.Map,
                contentDescription = "Map icon",
                tint = SoftBlack
            )

            Text(
                modifier = Modifier.width(CustomDimensions.padding250),
                text = "Rua blablabla, 235, Barreiro - Belo Horizonte/MG daisndisandisandisa",
                style = MaterialTheme.typography.bodyMedium,
                color = SoftBlack,
                fontWeight = FontWeight.Normal,
                maxLines = 2
            )
        }

        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Filled.CopyAll,
                contentDescription = "Copy icon",
                tint = SoftBlack
            )
        }
    }
}

@Composable
fun PhoneNumber() {
    Row {
        Icon(
            modifier = Modifier.padding(end = CustomDimensions.padding10),
            imageVector = Icons.Filled.Phone,
            contentDescription = "Phone icon",
            tint = SoftBlack
        )

        Text(
            modifier = Modifier.width(CustomDimensions.padding250),
            text = "(31) 00000-0000",
            style = MaterialTheme.typography.bodyMedium,
            color = SoftBlack,
            fontWeight = FontWeight.Normal,
            maxLines = 2
        )
    }
}

@Composable
fun LocationServices() {
    Column {
        Text(
            text = "Ir para o local:",
            style = MaterialTheme.typography.bodyLarge,
            color = SoftBlack,
            fontWeight = FontWeight.Bold,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TipsOfLocation(
                title = "Google Maps",
                icon = painterResource(id = R.drawable.ic_google_maps),
                titleColor = GoogleMapsIconColor
            )

            TipsOfLocation(
                title = "Waze",
                icon = painterResource(id = R.drawable.ic_waze),
                titleColor = Color.White,
                backgroundColor = WazeBackgroundColor
            )

            TipsOfLocation(
                title = "Uber",
                icon = painterResource(id = R.drawable.ic_uber),
                titleColor = Color.White,
                backgroundColor = SoftBlack
            )
        }
    }
}

@Composable
fun TipsOfLocation(
    title: String,
    icon: Painter,
    backgroundColor: Color = Color.White,
    titleColor: Color = SoftBlack,
    onClickTips: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .clickable { onClickTips() }
            .background(
                backgroundColor,
                shape = RoundedCornerShape(CustomDimensions.padding30)
            )
            .padding(
                vertical = CustomDimensions.padding10,
                horizontal = CustomDimensions.padding16
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = icon,
            contentDescription = "Icon",
        )

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = titleColor,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun ChatContainerExtrasScreenPreview() {
    ChatContainerExtrasScreen()
}

@Preview
@Composable
fun TipsOfLocationPreview() {
    TipsOfLocation(
        title = "Como chegar",
        icon = painterResource(id = R.drawable.ic_google_maps)
    )
}