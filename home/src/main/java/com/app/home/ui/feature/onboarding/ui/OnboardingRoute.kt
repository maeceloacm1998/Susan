package com.app.home.ui.feature.onboarding.ui

import android.content.Context
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.core.service.location.utils.LocationUtils.checkLocationPermission
import com.app.core.service.location.utils.LocationUtils.openAppSpecificSettings
import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType.INTRODUCTION
import com.app.home.ui.feature.onboarding.data.models.OnboardingStepsType.WELCOME
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.model.LatLng
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.S)
@ExperimentalPermissionsApi
@Composable
fun OnboardingRoute(
    onboardingViewModel: OnboardingViewModel = koinViewModel(),
) {
    val context: Context = LocalContext.current
    val uiState by onboardingViewModel.uiState.collectAsStateWithLifecycle()
    var permissionLocationRemember by rememberSaveable { mutableStateOf(false) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
            if(checkLocationPermission(context)) {
                permissionLocationRemember = true
                onboardingViewModel.onGetCurrentLocation()
            }
        }
    }

    OnboardingRoute(
        uiState = uiState,
        permissionsState = permissionLocationRemember,
        onClickAfterStep = { onboardingViewModel.onRemoveNewStep() },
        onClickFinishStep = { onboardingViewModel.onFinishActiveLocation(it)},
        onClickActiveLocation = { openAppSpecificSettings(context as ComponentActivity) },
        onClickNextStep = { onboardingViewModel.onNextStep() }
    )
}

@Composable
fun OnboardingRoute(
    uiState: OnboardingUiState,
    permissionsState: Boolean,
    onClickNextStep: () -> Unit,
    onClickAfterStep: () -> Unit,
    onClickActiveLocation: () -> Unit,
    onClickFinishStep: (currentLocation: LatLng) -> Unit
) {
    check(uiState is OnboardingUiState.Data)

    when (uiState.steps) {
        WELCOME -> OnboardingWelcomeScreen(
            uiState = uiState,
            onClickNextStep = onClickNextStep
        )

        INTRODUCTION -> OnboardingIntroductionScreen(
            uiState = uiState,
            permissionState = permissionsState,
            onClickFinishStep = onClickFinishStep,
            onClickActiveLocation = onClickActiveLocation,
            onClickAfterStep = onClickAfterStep
        )
    }
}