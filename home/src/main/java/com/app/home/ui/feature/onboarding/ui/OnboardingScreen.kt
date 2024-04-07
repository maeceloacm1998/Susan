package com.app.home.ui.feature.onboarding.ui

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
            text = "Bem vindo ao\nSearchMed!",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Primary
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding10))

        Text(
            modifier = Modifier.padding(horizontal = CustomDimensions.padding24),
            text = "Encontre hospitais próximos rapidamente e com facilidade.",
            style = MaterialTheme.typography.bodyMedium,
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
            text = "Busque por hospitais",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Primary
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding10))

        Text(
            modifier = Modifier.padding(horizontal = CustomDimensions.padding24),
            text = "Busque por hospitais usando a barra de busca ou explore o mapa interativo para ver as opções ao seu redor.",
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
            text = "Em caso de emegência",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Primary
        )

        Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding10))

        Text(
            modifier = Modifier.padding(horizontal = CustomDimensions.padding24),
            text = "Em caso de emergência, pressione o botão de emergência para localizar imediatamente o hospital mais próximo de você.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Secondary
        )
    }
}

@Preview
@Composable
fun OnboardingWelcomeScreenPreview() {
    val uiState = OnboardingUiState.Data(
        steps = OnboardingStepsType.WELCOME,
        currentLocation = null
    )
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