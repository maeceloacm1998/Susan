package com.app.home.ui.di

import com.app.home.ui.feature.locationpermission.data.LocationPermissionRepository
import com.app.home.ui.feature.locationpermission.data.LocationPermissionRepositoryImpl
import com.app.home.ui.feature.locationpermission.ui.LocationPermissionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LocationPermissionModule {
    val modules = module {
        single<LocationPermissionRepository> { LocationPermissionRepositoryImpl(
            getCheckLocationPermissionUseCase = get()
        ) }

        viewModel {
            LocationPermissionViewModel(
                getLocationUseCase = get(),
                getCheckLocationPermissionUseCase = get(),
                updateLastCurrentLocationUseCase = get(),
                openManualConfigUseCase = get(),
            )
        }
    }
}