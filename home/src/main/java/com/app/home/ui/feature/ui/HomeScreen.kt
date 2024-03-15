package com.app.home.ui.feature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(uiState: HomeUiState.HasHospital) {
    Column {
        Text(text = "Deu certo")
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