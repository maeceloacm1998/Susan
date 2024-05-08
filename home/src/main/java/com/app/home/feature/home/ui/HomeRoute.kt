package com.app.home.feature.home.ui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.core.components.screenloading.ScreenLoading
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val cameraState = rememberCameraPositionState()
    val context: Context = LocalContext.current

    HomeRoute(
        uiState = uiState,
        cameraState = cameraState,
        onInfoWindowClick = {},
        onClickEmergencyPhone = { homeViewModel.onClickEmergencyPhone(context) },
        onClickSearchEmergency = {}
    )
}

@RequiresApi(Build.VERSION_CODES.S)
@ExperimentalPermissionsApi
@Composable
fun HomeRoute(
    uiState: HomeUiState,
    cameraState: CameraPositionState,
    onInfoWindowClick: (Marker) -> Unit,
    onClickEmergencyPhone: () -> Unit,
    onClickSearchEmergency: () -> Unit
) {
    when {
        uiState.isLoading -> ScreenLoading()
        else -> HomeScreen(
            uiState = uiState,
            cameraState = cameraState,
            onInfoWindowClick = onInfoWindowClick,
            onClickEmergencyPhone = onClickEmergencyPhone,
            onClickSearchEmergency = onClickSearchEmergency
        )
    }
}