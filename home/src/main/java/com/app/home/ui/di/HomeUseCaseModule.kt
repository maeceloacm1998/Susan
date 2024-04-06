package com.app.home.ui.di

import com.app.home.ui.feature.home.domain.GetHomeCurrentLocationUseCase
import com.app.home.ui.feature.home.domain.GetNearbyHospitalsUseCase
import com.app.home.ui.feature.home.domain.GetShowOnboardingUseCase
import com.app.home.ui.feature.home.domain.ObserveHomeCurrentLocationUseCase
import com.app.home.ui.feature.home.domain.ObserveHomeShowOnboardingUseCase
import com.app.home.ui.feature.home.domain.UpdateShowOnboardingUseCase
import org.koin.dsl.module

object HomeUseCaseModule {
    val modules = module {
        single { GetHomeCurrentLocationUseCase(homeRepository = get()) }
        single { GetShowOnboardingUseCase(homeRepository = get()) }
        single { UpdateShowOnboardingUseCase(homeRepository = get()) }
        single { GetNearbyHospitalsUseCase(homeRepository = get()) }
        single { ObserveHomeCurrentLocationUseCase(homeRepository = get()) }
        single { ObserveHomeShowOnboardingUseCase(homeRepository = get()) }
    }
}