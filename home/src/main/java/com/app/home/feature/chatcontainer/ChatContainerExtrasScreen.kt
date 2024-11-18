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
import com.app.core.ui.theme.Secondary
import com.app.core.ui.theme.SoftBlack
import com.app.core.ui.theme.WazeBackgroundColor
import com.app.home.R
import com.app.home.feature.chat.data.external.models.EmergencyData

@Composable
fun ChatContainerExtrasScreen(
    extraItems: EmergencyData,
    onClickCopyAddress: () -> Unit,
    onClickPhoneNumber: () -> Unit,
    onClickGoogleMaps: () -> Unit,
    onClickWaze: () -> Unit,
    onClickUber: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = CustomDimensions.padding8)
            .background(Color.White, shape = RoundedCornerShape(CustomDimensions.padding10))
            .padding(CustomDimensions.padding20)
    ) {
        Text(
            text = extraItems.name ?: "Nome do local não informado",
            style = MaterialTheme.typography.titleMedium,
            color = SoftBlack,
            fontWeight = FontWeight.Bold,
        )

        Address(
            extraItems = extraItems,
            onClickCopyAddress = onClickCopyAddress
        )
        PhoneNumber(
            extraItems = extraItems,
            onClickPhoneNumber = onClickPhoneNumber
        )
        Spacer(modifier = Modifier.padding(vertical  = CustomDimensions.padding10))
        LocationServices(
            onClickGoogleMaps = onClickGoogleMaps,
            onClickWaze = onClickWaze,
            onClickUber = onClickUber
        )
    }
}

@Composable
fun Address(
    extraItems: EmergencyData,
    onClickCopyAddress: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = CustomDimensions.padding10),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(0.9f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(end = CustomDimensions.padding10),
                imageVector = Icons.Filled.Map,
                contentDescription = "Map icon",
                tint = Secondary
            )

            Text(
                text = extraItems.address ?: "Endereço não informado",
                style = MaterialTheme.typography.bodySmall,
                color = SoftBlack,
                fontWeight = FontWeight.Normal,
                maxLines = 2
            )
        }

        IconButton(
            modifier = Modifier.weight(0.1f),
            onClick = onClickCopyAddress
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
fun PhoneNumber(
    extraItems: EmergencyData,
    onClickPhoneNumber: () -> Unit
) {
    Row(
        modifier = Modifier.clickable { onClickPhoneNumber() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(end = CustomDimensions.padding10),
            imageVector = Icons.Filled.Phone,
            contentDescription = "Phone icon",
            tint = Secondary
        )

        Text(
            modifier = Modifier.width(CustomDimensions.padding250),
            text = extraItems.phoneNumber ?: "Telefone não informado",
            style = MaterialTheme.typography.bodyMedium,
            color = SoftBlack,
            fontWeight = FontWeight.Normal,
            maxLines = 2
        )
    }
}

@Composable
fun LocationServices(
    onClickGoogleMaps: () -> Unit = {},
    onClickWaze: () -> Unit = {},
    onClickUber: () -> Unit = {}
) {
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
                titleColor = GoogleMapsIconColor,
                onClickTips = onClickGoogleMaps
            )

            TipsOfLocation(
                title = "Waze",
                icon = painterResource(id = R.drawable.ic_waze),
                titleColor = Color.White,
                backgroundColor = WazeBackgroundColor,
                onClickTips = onClickWaze
            )

            TipsOfLocation(
                title = "Uber",
                icon = painterResource(id = R.drawable.ic_uber),
                titleColor = Color.White,
                backgroundColor = SoftBlack,
                onClickTips = onClickUber
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
                vertical = CustomDimensions.padding8,
                horizontal = CustomDimensions.padding10
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
            style = MaterialTheme.typography.bodySmall,
            color = titleColor,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun ChatContainerExtrasScreenPreview() {
    ChatContainerExtrasScreen(
        extraItems = EmergencyData(
            name = "Nome do local",
            address = "Endereço do local",
            phoneNumber = "Telefone do local"
        ),
        onClickCopyAddress = {},
        onClickPhoneNumber = {},
        onClickGoogleMaps = {},
        onClickWaze = {},
        onClickUber = {}
    )
}

@Preview
@Composable
fun TipsOfLocationPreview() {
    TipsOfLocation(
        title = "Como chegar",
        icon = painterResource(id = R.drawable.ic_google_maps)
    )
}