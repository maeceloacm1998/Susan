package com.app.core.service.di

import com.app.core.service.location.di.LocationServiceModule
import com.app.core.service.phonedialog.PhoneDialogModule
import com.app.core.service.sharedpreferences.SharedPreferencesModule

object ServiceDI {
    val instance = listOf(
        SharedPreferencesModule.modules,
        LocationServiceModule.modules,
        PhoneDialogModule.modules,
    )
}