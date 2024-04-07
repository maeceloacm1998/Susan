package com.app.home.ui.components.carrouselsteps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.components.buttons.IconButtonComponent
import com.app.core.components.buttons.IconOutlinedButtonComponent
import com.app.core.ui.theme.Background
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayLight
import com.app.core.ui.theme.Primary
import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType
import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType.FINISH
import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType.INTRODUCTION
import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType.WELCOME

@Composable
fun CarrouselSteps(
    modifier: Modifier = Modifier,
    screenList: List<OnboardingStepsType>,
    actualShowScreen: OnboardingStepsType,
    onClickNextStep: (actualShowScreen: OnboardingStepsType) -> Unit,
    onClickAfterStep: (actualShowScreen: OnboardingStepsType) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconOutlinedButtonComponent(
            icon = Icons.Filled.ArrowBack,
            iconColor = if (isFirstScreen(screenList, actualShowScreen)) {
                Color.Transparent
            } else {
                Primary
            },
            borderColor = if (isFirstScreen(screenList, actualShowScreen)) {
                Color.Transparent
            } else {
                Primary
            },
            onButtonListener = { onClickAfterStep(actualShowScreen) }
        )

        isFirstScreen(screenList, actualShowScreen)

        Row {
            screenList.forEach { screen ->
                StepsToggle(
                    modifier = Modifier.padding(end = CustomDimensions.padding5),
                    isChecked = isSelectedScreen(screen, actualShowScreen)
                )
            }
        }

        IconButtonComponent(
            icon = Icons.Filled.ArrowForward,
            containerColor = Primary,
            isChecked = isLastScreen(screenList, actualShowScreen),
            onButtonListener = { onClickNextStep(actualShowScreen) }
        )
    }
}

private fun isFirstScreen(
    screenList: List<OnboardingStepsType>,
    actualShowScreen: OnboardingStepsType
): Boolean {
    return screenList.first() == actualShowScreen
}

private fun isLastScreen(
    screenList: List<OnboardingStepsType>,
    actualShowScreen: OnboardingStepsType
): Boolean {
    return screenList.last() == actualShowScreen
}

private fun isSelectedScreen(
    screen: OnboardingStepsType,
    actualShowScreen: OnboardingStepsType
): Boolean {
    return screen == actualShowScreen
}

@Composable
fun StepsToggle(
    modifier: Modifier = Modifier,
    isChecked: Boolean
) {
    Box(
        modifier = modifier
            .size(
                width = CustomDimensions.padding10,
                height = CustomDimensions.padding10
            )
            .clip(RoundedCornerShape(CustomDimensions.padding16))
            .background(if (isChecked) Primary else GrayLight)
    ) {}
}

@Preview
@Composable
fun CarrouselStepsPreview() {
    val stepsOrder = mutableListOf(
        WELCOME,
        INTRODUCTION,
        FINISH
    )
    CarrouselSteps(
        screenList = stepsOrder,
        actualShowScreen = INTRODUCTION,
        onClickAfterStep = {},
        onClickNextStep = {}
    )
}

@Preview
@Composable
fun StepsToggleTruePreview() {
    StepsToggle(isChecked = true)
}

@Preview
@Composable
fun StepsToggleFalsePreview() {
    StepsToggle(isChecked = false)
}