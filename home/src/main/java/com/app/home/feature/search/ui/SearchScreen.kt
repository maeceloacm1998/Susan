package com.app.home.feature.search.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.app.core.ui.theme.Background
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.Primary
import com.app.core.ui.theme.Secondary
import com.app.home.R
import com.app.home.components.rating.RatingComponent
import com.app.home.components.rating.RatingComponentModel
import com.app.home.components.searchbar.SearchBarComponent

@Composable
fun SearchScreen(
    uiState: SearchViewModelUiState.Data
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        SearchBarComponent(
            modifier = Modifier
                .padding(CustomDimensions.padding10)
                .clip(RoundedCornerShape(CustomDimensions.padding20))
        )

        Text(
            text = "Hospitais próximos de você :)",
            color = Primary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                horizontal = CustomDimensions.padding24,
                vertical = CustomDimensions.padding10
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(10) { hospitals ->
                HospitalCard(
                    modifier = Modifier
                        .padding(CustomDimensions.padding10)
                        .clip(RoundedCornerShape(CustomDimensions.padding20))
                )
            }
        }
    }
}

@Composable
fun HospitalCard(
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp
        ),
    ) {
        Row(
            modifier = Modifier.padding(CustomDimensions.padding20),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(
                            height = CustomDimensions.padding85,
                            width = CustomDimensions.padding95
                        )
                        .padding(end = CustomDimensions.padding10)
                        .clip(RoundedCornerShape(CustomDimensions.padding16)),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "hospital_image"
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Hospital metropolitano Dr. Célio de Castro",
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    modifier = Modifier.padding(top = CustomDimensions.padding5),
                    text = "Endereço",
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    modifier = Modifier.padding(top = CustomDimensions.padding2),
                    text = "Rua blablabla, 235, Barreiro - Belo Horizonte/MG",
                    color = Secondary,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodySmall
                )

                RatingComponent(
                    modifier = Modifier.padding(top = CustomDimensions.padding5),
                    ratingComponentModel = RatingComponentModel("5.0")
                )
            }
        }
    }
}


@Preview
@Composable
fun HospitalCardPreview() {
    Column(
        modifier = Modifier.padding(CustomDimensions.padding10)
    ) {
        HospitalCard(
        )
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    val uiState = SearchViewModelUiState.Data(
        loading = false
    )
    SearchScreen(uiState = uiState)
}