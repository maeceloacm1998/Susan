package com.app.core.service.location

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.core.service.location.model.LocationService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class GetLocationUseCase(
    private val locationService: LocationService
) {
    @RequiresApi(Build.VERSION_CODES.S)
    operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()
}