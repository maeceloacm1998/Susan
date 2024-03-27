package com.app.core.service.location.domain

import com.app.core.service.location.LocationService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class GetLastCurrentLocationUseCase(
    private val locationService: LocationService
) {
    suspend operator fun invoke(): Flow<LatLng?> = locationService.onRequestLastCurrentLocation()
}