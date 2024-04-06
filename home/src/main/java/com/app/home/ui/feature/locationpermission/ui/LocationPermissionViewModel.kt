package com.app.home.ui.feature.locationpermission.ui

import android.content.Context
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.service.location.domain.GetLocationUseCase
import com.app.core.service.location.domain.UpdateLastCurrentLocationUseCase
import com.app.core.service.location.utils.LocationUtils.openAppSpecificSettings
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class LocationPermissionViewModel(
    private val getLocationUseCase: GetLocationUseCase,
    private val updateLastCurrentLocationUseCase: UpdateLastCurrentLocationUseCase
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.S)
    fun onGetUserLocation() {
        viewModelScope.launch {
            getLocationUseCase().collect { location ->
                if(location != null) {
                    onUpdateUserLocation(location)
                }
            }
        }
    }

    fun onOpenManualConfig(context: Context) {
        openAppSpecificSettings(context as ComponentActivity)
    }

    private suspend fun onUpdateUserLocation(location: LatLng) {
        updateLastCurrentLocationUseCase(location)
    }
}