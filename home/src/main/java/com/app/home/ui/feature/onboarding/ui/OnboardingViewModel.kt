package com.app.home.ui.feature.onboarding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.service.location.domain.GetLocationUseCase
import com.app.core.service.location.domain.UpdateLastCurrentLocationUseCase
import com.app.home.ui.feature.home.domain.UpdateShowOnboardingUseCase
import com.app.home.ui.feature.onboarding.models.OnboardingStepsType
import com.app.home.ui.feature.onboarding.models.OnboardingStepsType.FINISH
import com.app.home.ui.feature.onboarding.models.OnboardingStepsType.INTRODUCTION
import com.app.home.ui.feature.onboarding.models.OnboardingStepsType.WELCOME
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class OnboardingViewModel(
    private val getLocationUseCase: GetLocationUseCase,
    private val updateLastCurrentLocationUseCase: UpdateLastCurrentLocationUseCase,
    private val updateShowOnboardingUseCase: UpdateShowOnboardingUseCase
) : ViewModel() {
    private val viewModelState =
        MutableStateFlow(OnBoardingViewModelState(steps = WELCOME))

    val stepsOrder = mutableListOf(
        WELCOME,
        INTRODUCTION,
        FINISH
    )

    val uiState = viewModelState
        .map(OnBoardingViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onNextStep(steps: OnboardingStepsType) {
        if(steps == FINISH) {
            onFinishActiveLocation()
        } else {
            val index = stepsOrder.indexOf(steps)
            viewModelState.update { it.copy(steps = stepsOrder[index + 1]) }
        }
    }

    fun onRemoveNewStep(steps: OnboardingStepsType) {
        val index = stepsOrder.indexOf(steps)

        if (index > 0) {
            viewModelState.update { it.copy(steps = stepsOrder[index - 1]) }
        }
    }

    private fun onFinishActiveLocation() {
        updateShowOnboardingUseCase()
    }
}