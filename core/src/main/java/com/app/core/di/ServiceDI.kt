package com.app.core.di

import com.app.core.service.location.di.LocationServiceModule
import com.app.core.service.sharedpreferences.SharedPreferencesModule

object ServiceDI {
    val instance = listOf(
        LocationServiceModule.modules,
        SharedPreferencesModule.modules
    )
}