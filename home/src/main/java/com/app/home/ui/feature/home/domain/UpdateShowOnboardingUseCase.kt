package com.app.home.ui.feature.home.domain

import com.app.home.ui.feature.home.data.HomeRepository

class UpdateShowOnboardingUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke() = homeRepository.onFinishOnboarding()
}