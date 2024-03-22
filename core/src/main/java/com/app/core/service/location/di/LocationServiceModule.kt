package com.app.core.service.location.di

import com.app.core.service.location.GetLocationUseCase
import com.app.core.service.location.LocationServiceImpl
import com.app.core.service.location.model.LocationService
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object LocationServiceModule {
    val modules = module {
        factory<LocationService> {
            LocationServiceImpl(
                context = androidContext(),
                locationClient = LocationServices.getFusedLocationProviderClient(androidContext())
            )
        }
        factory { GetLocationUseCase(locationService = get()) }
    }
}