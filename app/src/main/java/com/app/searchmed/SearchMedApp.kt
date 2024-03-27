package com.app.searchmed

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.app.core.service.location.domain.GetLocationUseCase
import com.app.core.service.location.domain.UpdateLastCurrentLocationUseCase
import com.app.core.ui.theme.SearchMedTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.koin.compose.koinInject

@RequiresApi(Build.VERSION_CODES.S)
@ExperimentalPermissionsApi
@Composable
fun SearchMedApp() {
    val locationService: GetLocationUseCase = koinInject()
    val updateCurrentLocation: UpdateLastCurrentLocationUseCase = koinInject()
    val navController = rememberNavController()

    SearchMedTheme {
        val permissionState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        LaunchedEffect(Unit) {
            val permissionsToRequest = permissionState.permissions.filter {
                !it.status.isGranted
            }

            when {
                permissionsToRequest.isNotEmpty() -> permissionState.launchMultiplePermissionRequest()
                permissionState.allPermissionsGranted -> {
                    locationService().collect { location ->
                        location?.let { updateCurrentLocation(it) }
                    }
                }
            }
        }

        SearchMedNavGraph(
            navController = navController
        )
    }
}