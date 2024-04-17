package com.app.home.feature.home.ui

import com.app.home.feature.home.data.models.ErrorMessage
import com.google.android.gms.maps.model.LatLng

sealed interface HomeUiState {
    val isLoading: Boolean
    val isLocationActive: Boolean
    val errorMessages: ErrorMessage?
    val currentLocation: LatLng?
    val showOnboarding: Boolean

    data class NoHospital(
        override val currentLocation: LatLng?,
        override val isLoading: Boolean,
        override val isLocationActive: Boolean,
        override val errorMessages: ErrorMessage?,
        override val showOnboarding: Boolean,
    ) : HomeUiState

    data class HasHospital(
        override val isLoading: Boolean,
        override val isLocationActive: Boolean,
        override val errorMessages: ErrorMessage?,
        override val currentLocation: LatLng?,
        override val showOnboarding: Boolean,
        val hospitals: List<LatLng>?,
    ) : HomeUiState
}

data class HomeViewModelState(
    val hospitals: List<LatLng>? = null,
    val currentLocation: LatLng? = null,
    val isLoading: Boolean = false,
    val isLocationActive: Boolean = false,
    val isRevokedPermissions: Boolean = false,
    val showOnboarding: Boolean = false,
    val errorMessages: ErrorMessage? = null,
) {
    fun toUiState(): HomeUiState =
        HomeUiState.HasHospital(
            isLoading = isLoading,
            isLocationActive = isLocationActive,
            errorMessages = errorMessages,
            hospitals = hospitals,
            currentLocation = currentLocation,
            showOnboarding = showOnboarding
        )
}