package com.app.home.ui.di

import com.app.home.ui.feature.locationpermission.domain.GeLocationActiveUseCase
import org.koin.dsl.module

object LocationPermissionUseCaseModule {
    val modules = module {
        single { GeLocationActiveUseCase(locationPermissionRepository = get()) }
    }
}