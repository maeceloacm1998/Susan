package com.app.home.di

import com.app.home.feature.home.domain.GetHomeCurrentLocationUseCase
import com.app.home.feature.home.domain.GetNearbyHospitalsUseCase
import com.app.home.feature.home.domain.ObserveHomeCurrentLocationUseCase
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