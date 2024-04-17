package com.app.home.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.home.feature.home.data.HomeRepository
import com.app.home.feature.home.data.HomeRepositoryImpl
import com.app.home.feature.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeModule {
    @RequiresApi(Build.VERSION_CODES.S)
    val modules = module {
        single<HomeRepository> {
            HomeRepositoryImpl(
                getLocationUseCase = get(),
                getLastCurrentLocationUseCase = get()
            )
        }

        viewModel {
            HomeViewModel(
                getHomeCurrentLocationUseCase = get(),
                observeHomeCurrentLocationUseCase = get(),
            )
        }
    }
}