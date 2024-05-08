package com.app.home.components.searchbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.components.textfield.TextFieldCustom
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.Primary
import com.app.home.R

@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        TextFieldCustom(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = CustomDimensions.padding14,
                    start = CustomDimensions.padding14,
                    top = CustomDimensions.padding5
                ),
            startIconImageVector = Icons.Filled.Search,
            label = stringResource(id = R.string.home_search_bar_hint),
            focusedContainerColor = Primary,
            onChangeListener = {}
        )
    }
}

@Preview
@Composable
fun SearchBarComponentPreview() {
    SearchBarComponent()
}