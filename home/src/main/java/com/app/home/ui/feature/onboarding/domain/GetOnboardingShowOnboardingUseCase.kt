package com.app.home.ui.feature.onboarding.domain

import com.app.home.ui.feature.onboarding.data.OnboardingRepository

class GetOnboardingShowOnboardingUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke(): Boolean = onboardingRepository.isShowOnboarding()
}