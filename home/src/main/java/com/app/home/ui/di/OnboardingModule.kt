package com.app.home.ui.di

import com.app.home.ui.feature.onboarding.ui.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object OnboardingModule {
    val modules = module {
        viewModel {
            OnboardingViewModel(
                getLocationUseCase = get(),
                updateLastCurrentLocationUseCase = get(),
                updateShowOnboardingUseCase = get()
            )
        }
    }
}