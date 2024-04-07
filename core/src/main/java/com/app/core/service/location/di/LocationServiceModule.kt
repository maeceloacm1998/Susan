package com.app.core.service.location.di

import com.app.core.service.location.domain.GetLocationUseCase
import com.app.core.service.location.LocationServiceImpl
import com.app.core.service.location.LocationService
import com.app.core.service.location.domain.GetLastCurrentLocationUseCase
import com.app.core.service.location.domain.UpdateLastCurrentLocationUseCase
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object LocationServiceModule {
    val modules = module {
        single<LocationService> {
            LocationServiceImpl(
                context = androidContext(),
                locationClient = LocationServices.getFusedLocationProviderClient(androidContext())
            )
        }
        single { GetLocationUseCase(locationService = get()) }
        single { GetLastCurrentLocationUseCase(locationService = get()) }
        single { UpdateLastCurrentLocationUseCase(locationService = get()) }
    }
}