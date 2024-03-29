package com.app.home.ui.feature.home.data

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun handleNearbyHospitals()
    suspend fun handleCurrentLocation(): LatLng?
    suspend fun observeCurrentLocation(): Flow<LatLng?>
    fun onFinishOnboarding()
    fun isLocationActive(): Boolean
    fun isShowOnboarding(): Boolean
}