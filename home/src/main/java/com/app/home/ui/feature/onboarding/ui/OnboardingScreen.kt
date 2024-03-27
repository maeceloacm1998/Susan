package com.app.home.ui.feature.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.app.core.components.buttons.ButtonComponent
import com.app.core.ui.theme.Background
import com.app.core.ui.theme.CustomDimensions
import com.app.home.R
import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType
import com.google.android.gms.maps.model.LatLng

@Composable
fun OnboardingWelcomeScreen(
    uiState: OnboardingUiState.Data,
    onClickNextStep: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .padding(CustomDimensions.padding14)
                .weight(10f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bem vindo ao SearchMed",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                lineHeight = 45.sp,
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding10))

            Text(
                text = "Esse aplicativo tem o objetivo de te ajudar nos casos de emergências",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding40))

            Image(
                modifier = Modifier.size(
                    height = CustomDimensions.padding300,
                    width = CustomDimensions.padding300
                ),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "hospital"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(CustomDimensions.padding14),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            ButtonComponent(
                modifier = Modifier.width(CustomDimensions.padding150),
                title = "Próximo",
                titleColor = Color.White,
                onButtonListener = onClickNextStep
            )
        }
    }
}

@Composable
fun OnboardingIntroductionScreen(
    uiState: OnboardingUiState.Data,
    permissionState: Boolean,
    onClickActiveLocation: () -> Unit,
    onClickAfterStep: () -> Unit,
    onClickFinishStep: (currentLocation: LatLng) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .padding(CustomDimensions.padding14)
                .weight(10f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ativar Localização",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                lineHeight = 45.sp,
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding10))

            Text(
                text = "Para melhorar a experiência com o aplicativo, ative sua localização em tempo real, assim nos momentos de emergência conseguimos mostrar o hospital mais próximo de você",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding40))

            Column {
                Image(
                    modifier = Modifier.size(
                        height = CustomDimensions.padding300,
                        width = CustomDimensions.padding300
                    ),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "hospital"
                )

                Spacer(modifier = Modifier.padding(vertical = CustomDimensions.padding10))

                ButtonComponent(
                    modifier = Modifier.width(CustomDimensions.padding300),
                    title = "Ativar Localização",
                    titleColor = Color.White,
                    isChecked = permissionState,
                    onButtonListener = onClickActiveLocation
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(CustomDimensions.padding14),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonComponent(
                modifier = Modifier.width(CustomDimensions.padding150),
                title = "Anterior",
                titleColor = Color.White,
                onButtonListener = onClickAfterStep
            )

            ButtonComponent(
                modifier = Modifier.width(CustomDimensions.padding150),
                title = "Concluir",
                titleColor = Color.White,
                onButtonListener = { uiState.currentLocation?.let { onClickFinishStep(it) } }
            )
        }
    }
}

@Preview
@Composable
fun OnboardingWelcomeScreenPreview() {
    val uiState = OnboardingUiState.Data(
        steps = OnboardingStepsType.WELCOME,
        currentLocation = null
    )
    OnboardingWelcomeScreen(
        uiState = uiState,
        onClickNextStep = {}
    )
}

@Preview
@Composable
fun OnboardingIntroductionScreenPreview() {
    val uiState = OnboardingUiState.Data(
        steps = OnboardingStepsType.WELCOME,
        currentLocation = null
    )

    OnboardingIntroductionScreen(
        uiState = uiState,
        permissionState = false,
        onClickAfterStep = {},
        onClickActiveLocation = {},
        onClickFinishStep = {}
    )
}