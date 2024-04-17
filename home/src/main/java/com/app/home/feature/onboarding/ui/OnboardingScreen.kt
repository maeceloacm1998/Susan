package com.app.home.feature.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.Background
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.Primary
import com.app.core.ui.theme.Secondary
import com.app.home.R
import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType

@Composable
fun OnboardingWelcomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(
                height = CustomDimensions.padding250,
                width = CustomDimensions.padding250
            ),
            painter = painterResource(id = R.drawable.ic_search_med_onboarding_1),
            contentDescription = "onboarding image 1"
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding20))

        Text(
            text = stringResource(id = R.string.onboarding_welcome_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Primary
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding10))

        Text(
            modifier = Modifier.padding(horizontal = CustomDimensions.padding24),
            text = stringResource(id = R.string.onboarding_welcome_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Secondary
        )
    }
}

@Composable
fun OnboardingIntroductionScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(
                height = CustomDimensions.padding250,
                width = CustomDimensions.padding250
            ),
            painter = painterResource(id = R.drawable.ic_search_med_onboarding_2),
            contentDescription = "onboarding image 2"
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding20))

        Text(
            text = stringResource(id = R.string.onboarding_introduction_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Primary
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding10))

        Text(
            modifier = Modifier.padding(horizontal = CustomDimensions.padding24),
            text = stringResource(id = R.string.onboarding_welcome_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Secondary
        )
    }
}

@Composable
fun OnboardingFinishScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(
                height = CustomDimensions.padding250,
                width = CustomDimensions.padding250
            ),
            painter = painterResource(id = R.drawable.ic_search_med_onboarding_3),
            contentDescription = "onboarding image 2"
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding20))

        Text(
            text = stringResource(id = R.string.onboarding_finish_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Primary
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding10))

        Text(
            modifier = Modifier.padding(horizontal = CustomDimensions.padding24),
            text = stringResource(id = R.string.onboarding_finish_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Secondary
        )
    }
}

@Preview
@Composable
fun OnboardingWelcomeScreenPreview() {
    OnboardingWelcomeScreen()
}

@Preview
@Composable
fun OnboardingIntroductionScreenPreview() {
    OnboardingIntroductionScreen()
}


@Preview
@Composable
fun OnboardingFinishScreenPreview() {
    OnboardingFinishScreen()
}