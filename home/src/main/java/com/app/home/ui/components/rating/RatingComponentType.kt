package com.app.home.ui.components.rating

import androidx.compose.ui.graphics.Color
import com.app.core.ui.theme.BrowDark
import com.app.core.ui.theme.Error
import com.app.core.ui.theme.Success

enum class RatingComponentType(val color: Color) {
    GOOD(color = Success),
    MEDIUM(color = BrowDark),
    BAD(color = Error)
}

class ParseRating {
    companion object {
        fun parse(rating: String): RatingComponentType {
            val parseRating = rating.toFloat()
            val goodRating = 4.0..5.0
            val mediumRating = 2.5..3.9

            return when {
                goodRating.contains(parseRating) -> RatingComponentType.GOOD
                mediumRating.contains(parseRating) -> RatingComponentType.MEDIUM
                else -> RatingComponentType.BAD
            }
        }
    }
}


