package com.app.home.ui.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.home.ui.feature.home.data.HomeRepository
import com.app.home.ui.feature.home.data.HomeRepositoryImpl
import com.app.home.ui.feature.home.domain.GetHomeCurrentLocationUseCase
import com.app.home.ui.feature.home.domain.GetNearbyHospitalsUseCase
import com.app.home.ui.feature.home.domain.GetShowOnboardingUseCase
import com.app.home.ui.feature.home.domain.ObserveHomeCurrentLocationUseCase
import com.app.home.ui.feature.home.domain.ObserveHomeShowOnboardingUseCase
import com.app.home.ui.feature.home.domain.UpdateShowOnboardingUseCase
import com.app.home.ui.feature.home.ui.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeModule {
    @RequiresApi(Build.VERSION_CODES.S)
    val modules = module {
        single<HomeRepository> {
            HomeRepositoryImpl(
                context = androidContext(),
                getLocationUseCase = get(),
                getLastCurrentLocationUseCase = get(),
                sharedPreferences = get()
            )
        }

        viewModel {
            HomeViewModel(
                getHomeCurrentLocationUseCase = get(),
                observeHomeCurrentLocationUseCase = get(),
                observeHomeShowOnboardingUseCase = get(),
                getShowOnboardingUseCase = get()
            )
        }
    }
}