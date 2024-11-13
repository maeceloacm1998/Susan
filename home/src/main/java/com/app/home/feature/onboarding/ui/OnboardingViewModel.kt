package com.app.home.feature.onboarding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.core.routes.Routes
import com.app.home.feature.locationpermission.domain.GetLocationActiveUseCase
import com.app.home.feature.onboarding.data.models.OnboardingStepsType
import com.app.home.feature.onboarding.data.models.OnboardingStepsType.FINISH
import com.app.home.feature.onboarding.data.models.OnboardingStepsType.WELCOME
import com.app.home.feature.onboarding.domain.UpdateOnboardingShowOnboardingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class OnboardingViewModel(
    private val getHomeLocationActiveUseCase: GetLocationActiveUseCase,
    private val updateShowOnboardingUseCase: UpdateOnboardingShowOnboardingUseCase
) : ViewModel() {
    private val viewModelState =
        MutableStateFlow(OnBoardingViewModelState(steps = WELCOME))

    val stepsOrder = mutableListOf(
        WELCOME,
        FINISH
    )

    val uiState = viewModelState
        .map(OnBoardingViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onNextStep(steps: OnboardingStepsType, navigation: NavController) {
        if(steps == FINISH) {
            onFinishActiveLocation(navigation)
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

    private fun onFinishActiveLocation(navigation: NavController) {
        updateShowOnboardingUseCase()
        if(getHomeLocationActiveUseCase()) {
            onGoToHome(navigation)
        } else {
            onGoToLocationPermission(navigation)
        }
    }

    private fun onGoToLocationPermission(navigation: NavController) {
        navigation.navigate(Routes.CheckPermissions.route) {
            popUpTo(Routes.Onboarding.route) {
                inclusive = true
            }
        }
    }

    private fun onGoToHome(navigation: NavController) {
        navigation.navigate(Routes.Chat.route) {
            popUpTo(Routes.Onboarding.route) {
                inclusive = true
            }
        }
    }
}