package com.app.home.feature.onboarding.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.core.ui.theme.CustomDimensions
import com.app.core.utils.AnimatedUtils.animatedTransitionPage
import com.app.home.components.carrouselsteps.CarrouselSteps
import com.app.home.feature.onboarding.data.models.OnboardingStepsType
import com.app.home.feature.onboarding.data.models.OnboardingStepsType.FINISH
import com.app.home.feature.onboarding.data.models.OnboardingStepsType.WELCOME
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.S)
@ExperimentalPermissionsApi
@Composable
fun OnboardingRoute(
    navController: NavController,
    onboardingViewModel: OnboardingViewModel = koinViewModel(),
) {
    val uiState by onboardingViewModel.uiState.collectAsStateWithLifecycle()

    OnboardingRoute(
        uiState = uiState,
        stepsOrder = onboardingViewModel.stepsOrder,
        onClickAfterStep = { onboardingViewModel.onRemoveNewStep(it) },
        onClickNextStep = {
            onboardingViewModel.onNextStep(
                navigation = navController,
                steps = it
            )
        }
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
        AnimatedContent(
            modifier = Modifier.weight(2f),
            targetState = uiState.steps,
            label = "AnimatedContent",
            transitionSpec = animatedTransitionPage()
        ) { targetState ->
            when (targetState) {
                WELCOME -> OnboardingWelcomeScreen()
                FINISH -> OnboardingFinishScreen()
            }
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