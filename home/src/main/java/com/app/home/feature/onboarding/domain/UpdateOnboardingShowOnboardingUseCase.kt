package com.app.home.feature.onboarding.domain

import com.app.home.feature.onboarding.data.OnboardingRepository

class UpdateOnboardingShowOnboardingUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke() = onboardingRepository.onFinishOnboarding()
}