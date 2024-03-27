package com.app.core.service.location

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface LocationService {
    suspend fun onRequestLocationUpdates(): Flow<LatLng?>
    suspend fun onUpdateLastCurrentLocation(currentLocation: LatLng)
    suspend fun onRequestLastCurrentLocation(): Flow<LatLng?>
}