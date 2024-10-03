package com.app.home.feature.chat.data

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun handleCurrentLocation(): LatLng?
    suspend fun observeCurrentLocation(): Flow<LatLng?>
}