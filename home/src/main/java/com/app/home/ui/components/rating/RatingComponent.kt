package com.app.home.ui.components.rating

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.CustomDimensions

@Composable
fun RatingComponent(
    modifier: Modifier = Modifier,
    ratingComponentModel: RatingComponentModel
) {
    Column(
        modifier = modifier
            .background(color = ratingComponentModel.background)
    ) {
        Text(
            text = "Avaliação:",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        RatingBox(rating = ratingComponentModel.rating)
    }
}

@Composable
fun RatingBox(rating: String) {
    Column(
        modifier = Modifier
            .padding(top = CustomDimensions.padding5)
            .clip(RoundedCornerShape(CustomDimensions.padding5))
            .background(ParseRating.parse(rating).color),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = CustomDimensions.padding5,
                vertical = CustomDimensions.padding2
            ),
            text = rating,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun RatingComponentPreview() {
    val ratingComponentModel = RatingComponentModel(
        rating = "5.0"
    )
    RatingComponent(ratingComponentModel = ratingComponentModel)
}