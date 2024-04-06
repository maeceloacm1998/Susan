package com.app.home.ui.feature.onboarding.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.core.ui.theme.CustomDimensions
import com.app.home.ui.components.carrouselsteps.CarrouselSteps
import com.app.home.ui.feature.onboarding.models.OnboardingStepsType
import com.app.home.ui.feature.onboarding.models.OnboardingStepsType.FINISH
import com.app.home.ui.feature.onboarding.models.OnboardingStepsType.INTRODUCTION
import com.app.home.ui.feature.onboarding.models.OnboardingStepsType.WELCOME
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.S)
@ExperimentalPermissionsApi
@Composable
fun OnboardingRoute(
    onboardingViewModel: OnboardingViewModel = koinViewModel(),
) {
    val uiState by onboardingViewModel.uiState.collectAsStateWithLifecycle()

    OnboardingRoute(
        uiState = uiState,
        stepsOrder = onboardingViewModel.stepsOrder,
        onClickAfterStep = { onboardingViewModel.onRemoveNewStep(it) },
        onClickNextStep = { onboardingViewModel.onNextStep(it) }
    )
}

@Composable
fun OnboardingRoute(
    uiState: OnboardingUiState,
    stepsOrder: List<OnboardingStepsType>,
    onClickNextStep: (actualShowScreen: OnboardingStepsType) -> Unit,
    onClickAfterStep: (actualShowScreen: OnboardingStepsType) -> Unit,
) {
    check(uiState is OnboardingUiState.Data)

    Column {
        when (uiState.steps) {
            WELCOME -> OnboardingWelcomeScreen(modifier = Modifier.weight(2f))
            INTRODUCTION -> OnboardingIntroductionScreen(modifier = Modifier.weight(2f))
            FINISH -> OnboardingFinishScreen(modifier = Modifier.weight(2f))
        }

        CarrouselSteps(
            modifier = Modifier
                .weight(0.5f)
                .padding(horizontal = CustomDimensions.padding24),
            screenList = stepsOrder,
            actualShowScreen = uiState.steps,
            onClickAfterStep = onClickAfterStep,
            onClickNextStep = onClickNextStep
        )
    }
}