package com.app.core.service.location.domain

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.core.service.location.LocationService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class GetLocationUseCase(
    private val locationService: LocationService
) {
    @RequiresApi(Build.VERSION_CODES.S)
    suspend operator fun invoke(): Flow<LatLng?> = locationService.onRequestLocationUpdates()
}