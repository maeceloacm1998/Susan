package com.app.home.ui.feature.home.domain

import com.app.home.ui.feature.home.data.HomeRepository
import com.google.android.gms.maps.model.LatLng

class GetHomeCurrentLocationUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): LatLng? {
        homeRepository.run {
            if (isLocationActive()) {
                return handleCurrentLocation()
            }
        }

        return LatLng(0.0, 0.0)
    }
}