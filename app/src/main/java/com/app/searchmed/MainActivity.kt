@file:OptIn(ExperimentalPermissionsApi::class)

package com.app.searchmed

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.app.core.service.location.di.LocationServiceModule
import com.app.core.service.sharedpreferences.SharedPreferencesModule
import com.app.home.ui.di.HomeModule
import com.app.home.ui.di.OnboardingModule
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(listOf(
                SharedPreferencesModule.modules,
                LocationServiceModule.modules,
                HomeModule.modules,
                OnboardingModule.modules
            ))
        }

        setContent {
            SearchMedApp()
        }
    }
}