package com.app.home.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.home.feature.chat.data.local.ChatMessageDBModule

object HomeDI {
    @RequiresApi(Build.VERSION_CODES.S)
    val instance = listOf(
        ChatModule.modules,
        ChatMessageDBModule.modules,
        LocationPermissionModule.modules,
        LocationPermissionUseCaseModule.modules,
        OnboardingUseCaseModule.modules,
        OnboardingModule.modules,
    )
}