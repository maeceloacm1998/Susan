package com.app.home.ui.feature.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.android.gms.maps.model.Marker
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsState()
    HomeRoute(
        uiState = uiState,
        onInfoWindowClick = {}
    )
}

@Composable
fun HomeRoute(
    uiState: HomeUiState,
    onInfoWindowClick: (Marker) -> Unit
) {
    check(uiState is HomeUiState.HasHospital)
    HomeScreen(
        uiState = uiState,
        onInfoWindowClick = onInfoWindowClick
    )
}