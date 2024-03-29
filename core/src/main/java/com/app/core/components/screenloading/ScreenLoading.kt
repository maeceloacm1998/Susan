package com.app.core.components.screenloading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.Background
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayLight

@Composable
fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean = false,
    loadingContent: @Composable () -> Unit = { ScreenLoading() },
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        if (loading) {
            loadingContent()
        } else {
            content()
        }
    }
}

@Composable
fun ScreenLoading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = GrayLight,
            modifier = Modifier.size(
                width = CustomDimensions.padding30,
                height = CustomDimensions.padding30
            )
        )
    }
}

@Preview
@Composable
fun ScreenLoadingPreview() {
    ScreenLoading()
}