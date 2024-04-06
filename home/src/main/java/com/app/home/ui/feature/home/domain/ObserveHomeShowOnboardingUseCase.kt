package com.app.home.ui.feature.home.domain

import com.app.home.ui.feature.home.data.HomeRepository
import kotlinx.coroutines.flow.Flow

class ObserveHomeShowOnboardingUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Flow<Boolean> = homeRepository.observeShowOnboarding()
}