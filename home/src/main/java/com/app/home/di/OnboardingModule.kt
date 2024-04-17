package com.app.home.di

import com.app.home.feature.onboarding.data.OnboardingRepository
import com.app.home.feature.onboarding.data.OnboardingRepositoryImpl
import com.app.home.feature.onboarding.ui.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object OnboardingModule {
    val modules = module {
        single<OnboardingRepository> {
            OnboardingRepositoryImpl(
                sharedPreferences = get()
            )
        }

        viewModel {
            OnboardingViewModel(
                getHomeLocationActiveUseCase = get(),
                updateShowOnboardingUseCase = get()
            )
        }
    }
}