package com.app.home.di

import android.os.Build
import androidx.annotation.RequiresApi

object HomeDI {
    @RequiresApi(Build.VERSION_CODES.S)
    val instance = listOf(
        ChatModule.modules,
        LocationPermissionModule.modules,
        LocationPermissionUseCaseModule.modules,
        OnboardingUseCaseModule.modules,
        OnboardingModule.modules
    )
}