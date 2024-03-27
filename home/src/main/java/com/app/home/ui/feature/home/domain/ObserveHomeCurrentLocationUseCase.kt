package com.app.home.ui.feature.home.domain

import com.app.home.ui.feature.home.data.HomeRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class ObserveHomeCurrentLocationUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Flow<LatLng?> = homeRepository.observeCurrentLocation()
}