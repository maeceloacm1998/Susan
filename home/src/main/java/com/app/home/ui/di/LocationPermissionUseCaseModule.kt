package com.app.home.ui.di

import com.app.home.ui.feature.locationpermission.domain.GetLocationActiveUseCase
import org.koin.dsl.module

object LocationPermissionUseCaseModule {
    val modules = module {
        single { GetLocationActiveUseCase(locationPermissionRepository = get()) }
    }
}