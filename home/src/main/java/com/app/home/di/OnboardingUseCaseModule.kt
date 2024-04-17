package com.app.home.di

import com.app.home.feature.onboarding.domain.GetOnboardingShowOnboardingUseCase
import com.app.home.feature.onboarding.domain.UpdateOnboardingShowOnboardingUseCase
import org.koin.dsl.module

object OnboardingUseCaseModule {
    val modules = module {
        single { GetOnboardingShowOnboardingUseCase(onboardingRepository = get()) }
        single { UpdateOnboardingShowOnboardingUseCase(onboardingRepository = get()) }
    }
}