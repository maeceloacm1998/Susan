package com.app.home.ui.feature.ui

import com.app.home.ui.feature.ui.models.ErrorMessage

sealed interface HomeUiState {
    val isLoading: Boolean
    val errorMessages: ErrorMessage?

    data class NoHospital(
        override val isLoading: Boolean,
        override val errorMessages: ErrorMessage?
    ) : HomeUiState

    data class HasHospital(
        override val isLoading: Boolean,
        override val errorMessages: ErrorMessage?,
    ) : HomeUiState
}

data class HomeViewModelState(
    val hospitals: String? = null,
    val isLoading: Boolean = false,
    val errorMessages: ErrorMessage? = null,
) {
    fun toUiState(): HomeUiState =
        if (hospitals == null) {
            HomeUiState.NoHospital(
                isLoading = isLoading,
                errorMessages = errorMessages,
            )
        } else {
            HomeUiState.HasHospital(
                isLoading = isLoading,
                errorMessages = errorMessages,
            )
        }
}