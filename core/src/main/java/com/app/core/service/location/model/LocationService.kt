package com.app.core.service.location.model

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface LocationService {
    fun requestLocationUpdates(): Flow<LatLng?>
    fun requestCurrentLocation(): Flow<LatLng?>
}