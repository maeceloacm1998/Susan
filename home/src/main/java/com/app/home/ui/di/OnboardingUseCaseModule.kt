package com.app.home.ui.di

import com.app.home.ui.feature.onboarding.domain.GetOnboardingShowOnboardingUseCase
import com.app.home.ui.feature.onboarding.domain.UpdateOnboardingShowOnboardingUseCase
import org.koin.dsl.module

object OnboardingUseCaseModule {
    val modules = module {
        single { GetOnboardingShowOnboardingUseCase(onboardingRepository = get()) }
        single { UpdateOnboardingShowOnboardingUseCase(onboardingRepository = get()) }
    }
}