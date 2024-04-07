package com.app.home.ui.feature.home.domain

import com.app.home.ui.feature.home.data.HomeRepository
import com.app.home.ui.feature.locationpermission.domain.GeLocationActiveUseCase
import com.google.android.gms.maps.model.LatLng

class GetHomeCurrentLocationUseCase(
    private val getLocationActiveUseCase: GeLocationActiveUseCase,
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): LatLng? {
        homeRepository.run {
            if (getLocationActiveUseCase()) {
                return handleCurrentLocation()
            }
        }

        return LatLng(0.0, 0.0)
    }
}