package com.app.home.components.makerinfocontainer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.core.components.buttons.TextButtonComponent
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.Primary
import com.app.home.R
import com.app.home.ui.components.rating.RatingComponent
import com.app.home.ui.components.rating.RatingComponentModel


@Composable
fun MakerInfoContainer(title: String?) {
    Column(
        modifier = Modifier
            .padding(CustomDimensions.padding14)
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 6.dp),
            text = "Hospital Metropolitano Dr. Célio de Castro",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.padding(top = CustomDimensions.padding10)
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .height(CustomDimensions.padding80)
                    .width(CustomDimensions.padding80),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Hospital Image"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = CustomDimensions.padding10,
                        top = CustomDimensions.padding5
                    )
            ) {
                Text(
                    text = "Endereço:",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Rua Hildebrando de Oliveira, 245",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    RatingComponent(
                        modifier = Modifier
                            .padding(top = CustomDimensions.padding10),
                        ratingComponentModel = RatingComponentModel("5.0")
                    )

                    TextButtonComponent(
                        modifier = Modifier.height(CustomDimensions.padding40),
                        title = "Ver mais",
                        titleColor = Primary,
                        onButtonListener = {}
                    )
                }
            }
        }
    }
}