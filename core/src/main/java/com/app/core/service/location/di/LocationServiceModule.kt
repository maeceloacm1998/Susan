package com.app.core.service.location.di

import com.app.core.service.location.domain.GetLocationUseCase
import com.app.core.service.location.LocationServiceImpl
import com.app.core.service.location.LocationService
import com.app.core.service.location.domain.GetCheckLocationPermissionUseCase
import com.app.core.service.location.domain.GetLastCurrentLocationUseCase
import com.app.core.service.location.domain.UpdateLastCurrentLocationUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object LocationServiceModule {
    val modules = module {
        single<LocationService> {
            LocationServiceImpl(context = androidContext())
        }
        single { GetLocationUseCase(locationService = get()) }
        single { GetLastCurrentLocationUseCase(locationService = get()) }
        single { GetCheckLocationPermissionUseCase(locationService = get()) }
        single { UpdateLastCurrentLocationUseCase(locationService = get()) }
    }
}
