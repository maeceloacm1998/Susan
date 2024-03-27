package com.app.home.ui.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.home.ui.feature.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeModule {
    @RequiresApi(Build.VERSION_CODES.S)
    val modules = module {
        viewModel {
            HomeViewModel(
                getLocationUseCase = get(),
                getLastCurrentLocationUseCase = get()
            )
        }
    }
}