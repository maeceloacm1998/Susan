package com.app.home.ui.feature.home.domain

import com.app.home.ui.feature.home.data.HomeRepository

class GetShowOnboardingUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(): Boolean = homeRepository.isShowOnboarding()
}