package com.app.home.ui.feature.home.domain

import com.app.home.ui.feature.home.data.HomeRepository

class GetNearbyHospitalsUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke() = homeRepository.handleNearbyHospitals()
}