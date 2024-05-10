package com.app.home.feature.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchRoute(
    navController: NavController,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

}

@Composable
fun SearchRoute(
    uiState: SearchViewModelUiState
) {
    check(uiState is SearchViewModelUiState.Data)
    SearchScreen(
        uiState = uiState
    )
}

