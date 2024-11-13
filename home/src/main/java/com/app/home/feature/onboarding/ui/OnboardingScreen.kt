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
                height = CustomDimensions.padding150,
                width = CustomDimensions.padding150
            ),
            painter = painterResource(id = R.drawable.susan_onboarding_rounded),
            contentDescription = "susan logo"
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding20))

        Text(
            modifier = Modifier.padding(horizontal = CustomDimensions.padding24),
            text = stringResource(id = R.string.onboarding_welcome_title),
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
            color = Primary
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
            painter = painterResource(id = R.drawable.ic_onboarding_conversation),
            contentDescription = "logo chat"
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
            color = Primary
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
fun OnboardingFinishScreenPreview() {
    OnboardingFinishScreen()
}