package com.app.core.service.location.domain

import com.app.core.service.location.LocationService
import com.google.android.gms.maps.model.LatLng

class UpdateLastCurrentLocationUseCase(
    private val locationService: LocationService
) {
    suspend operator fun invoke(currentLocation: LatLng) =
        locationService.onUpdateLastCurrentLocation(currentLocation)
}