package com.app.home.ui.feature.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsState()
    HomeRoute(uiState = uiState)
}

@Composable
fun HomeRoute(
    uiState: HomeUiState
) {
    check(uiState is HomeUiState.HasHospital)
    HomeScreen(uiState = uiState)
}