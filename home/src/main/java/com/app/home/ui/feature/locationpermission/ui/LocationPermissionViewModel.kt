package com.app.home.ui.feature.locationpermission.ui

import android.content.Context
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.core.routes.Routes
import com.app.core.service.location.domain.GetLocationUseCase
import com.app.core.service.location.domain.UpdateLastCurrentLocationUseCase
import com.app.home.ui.feature.locationpermission.domain.OpenManualConfigUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class LocationPermissionViewModel(
    private val getLocationUseCase: GetLocationUseCase,
    private val updateLastCurrentLocationUseCase: UpdateLastCurrentLocationUseCase,
    private val openManualConfigUseCase: OpenManualConfigUseCase
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.S)
    fun onGetUserLocation(navController: NavController, context: Context) {
        viewModelScope.launch {
            getLocationUseCase().collect { location ->
                if (location != null) {
                    onUpdateUserLocation(
                        navController = navController,
                        location = location
                    )
                } else {
                    onOpenManualConfig(context)
                }
            }
        }
    }

    fun onOpenManualConfig(context: Context) {
        openManualConfigUseCase(context as ComponentActivity)
    }

    private suspend fun onUpdateUserLocation(
        navController: NavController,
        location: LatLng
    ) {
        updateLastCurrentLocationUseCase(location)
        onGoToHome(navController)
    }

    private fun onGoToHome(navigation: NavController) {
        navigation.navigate(Routes.Home.route) {
            popUpTo(Routes.CheckPermissions.route) {
                inclusive = true
            }
        }
    }
}