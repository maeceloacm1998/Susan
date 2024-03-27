package com.app.home.ui.feature.onboarding.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.service.location.domain.GetLocationUseCase
import com.app.core.service.location.domain.UpdateLastCurrentLocationUseCase
import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType.INTRODUCTION
import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType.WELCOME
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val getLocationUseCase: GetLocationUseCase,
    private val updateLastCurrentLocationUseCase: UpdateLastCurrentLocationUseCase
) : ViewModel() {
    private val viewModelState =
        MutableStateFlow(OnBoardingViewModelState(steps = WELCOME))

    private val stepsOrder = mutableListOf(
        WELCOME,
        INTRODUCTION
    )

    val uiState = viewModelState
        .map(OnBoardingViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    @RequiresApi(Build.VERSION_CODES.S)
    fun onGetCurrentLocation() {
        viewModelScope.launch {
            getLocationUseCase().collect { location ->
                viewModelState.update { it.copy(currentLocation = location) }
            }
        }
    }

    fun onFinishActiveLocation(currentLocation: LatLng) {
        viewModelScope.launch {
            updateLastCurrentLocationUseCase(currentLocation)
        }
    }

    fun onNextStep() {
        val stepList = viewModelState.value.steps
        val index = stepsOrder.indexOf(stepList)

        viewModelState.update { it.copy(steps = stepsOrder[index + 1]) }
    }

    fun onRemoveNewStep() {
        val stepList = viewModelState.value.steps
        val index = stepsOrder.indexOf(stepList)

        if (index > 0) {
            viewModelState.update { it.copy(steps = stepsOrder[index - 1]) }
        }
    }
}