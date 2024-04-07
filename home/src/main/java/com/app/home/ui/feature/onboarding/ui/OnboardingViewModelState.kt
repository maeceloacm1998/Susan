package com.app.home.ui.feature.onboarding.ui

import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType
import com.google.android.gms.maps.model.LatLng

sealed interface OnboardingUiState {
    data class Data(
        val steps: OnboardingStepsType,
        val currentLocation: LatLng?
    ) : OnboardingUiState
}

data class OnBoardingViewModelState(
    val steps: OnboardingStepsType = OnboardingStepsType.WELCOME,
    val currentLocation: LatLng? = null
) {
    fun toUiState(): OnboardingUiState =
        OnboardingUiState.Data(
            steps = steps,
            currentLocation = currentLocation
        )
}