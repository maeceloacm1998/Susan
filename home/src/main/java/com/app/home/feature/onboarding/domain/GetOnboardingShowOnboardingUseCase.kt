package com.app.home.feature.onboarding.domain

import com.app.home.feature.onboarding.data.OnboardingRepository

class GetOnboardingShowOnboardingUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke(): Boolean = onboardingRepository.isShowOnboarding()
}