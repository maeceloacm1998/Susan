package com.app.home.feature.search.ui

sealed interface SearchViewModelUiState {
    data class Data(
        val loading: Boolean
    ) : SearchViewModelUiState
}

data class SearchViewModelState(
    val loading: Boolean = false
) {
    fun toUiState(): SearchViewModelUiState =
        SearchViewModelUiState.Data(
            loading = loading
        )
}