package com.app.home.ui.feature.onboarding.domain

import com.app.home.ui.feature.onboarding.data.OnboardingRepository

class UpdateOnboardingShowOnboardingUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke() = onboardingRepository.onFinishOnboarding()
}