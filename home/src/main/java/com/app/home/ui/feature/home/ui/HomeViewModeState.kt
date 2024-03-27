package com.app.home.ui.feature.home.ui

import com.app.home.ui.feature.home.data.models.ErrorMessage
import com.google.android.gms.maps.model.LatLng

sealed interface HomeUiState {
    val isLoading: Boolean
    val errorMessages: ErrorMessage?
    val currentLocation: LatLng?

    data class NoHospital(
        override val currentLocation: LatLng?,
        override val isLoading: Boolean,
        override val errorMessages: ErrorMessage?
    ) : HomeUiState

    data class HasHospital(
        override val isLoading: Boolean,
        override val errorMessages: ErrorMessage?,
        override val currentLocation: LatLng?,
        val hospitals: List<LatLng>?,
    ) : HomeUiState
}

data class HomeViewModelState(
    val hospitals: List<LatLng>? = null,
    val currentLocation: LatLng? = null,
    val isLoading: Boolean = false,
    val isRevokedPermissions: Boolean = false,
    val errorMessages: ErrorMessage? = null,
) {
    fun toUiState(): HomeUiState =
        HomeUiState.HasHospital(
            isLoading = isLoading,
            errorMessages = errorMessages,
            hospitals = hospitals,
            currentLocation = currentLocation
        )
}