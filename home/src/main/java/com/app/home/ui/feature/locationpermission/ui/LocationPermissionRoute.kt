package com.app.home.ui.feature.locationpermission.ui

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.app.core.service.location.utils.LocationUtils.checkLocationPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LocationPermissionRoute(
    navController: NavController,
    locationViewModel: LocationPermissionViewModel = koinViewModel()
) {
    val context: Context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(permissionState) {
        if (permissionState.allPermissionsGranted) {
            locationViewModel.onGetUserLocation(
                navController = navController,
                context = context
            )
        }
    }

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
            if (checkLocationPermission(context)) {
                locationViewModel.onGetUserLocation(
                    navController = navController,
                    context = context
                )
            }
        }
    }

    LocationPermissionScreen(
        onActiveAutomateAccess = { permissionState.launchMultiplePermissionRequest() },
        onActiveManualAccess = { locationViewModel.onOpenManualConfig(context) }
    )
}

