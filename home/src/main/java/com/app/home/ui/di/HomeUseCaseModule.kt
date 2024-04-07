package com.app.home.ui.di

import com.app.home.ui.feature.home.domain.GetHomeCurrentLocationUseCase
import com.app.home.ui.feature.home.domain.GetNearbyHospitalsUseCase
import com.app.home.ui.feature.home.domain.ObserveHomeCurrentLocationUseCase
import org.koin.dsl.module

object HomeUseCaseModule {
    val modules = module {
        single { GetHomeCurrentLocationUseCase(
            homeRepository = get(),
            getLocationActiveUseCase = get()
        ) }
        single { GetNearbyHospitalsUseCase(homeRepository = get()) }
        single { ObserveHomeCurrentLocationUseCase(homeRepository = get()) }
    }
}