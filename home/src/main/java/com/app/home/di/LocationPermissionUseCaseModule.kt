package com.app.home.di

import com.app.home.ui.feature.locationpermission.domain.GetLocationActiveUseCase
import com.app.home.ui.feature.locationpermission.domain.OpenManualConfigUseCase
import org.koin.dsl.module

object LocationPermissionUseCaseModule {
    val modules = module {
        single { GetLocationActiveUseCase(locationPermissionRepository = get()) }
        single { OpenManualConfigUseCase() }
    }
}