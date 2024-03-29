package com.app.core.service.sharedpreferences

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object SharedPreferencesModule {
    val modules = module {
        single<SharedPreferencesBuilder> { SharedPreferencesBuilderImpl(androidContext()) }
    }
}