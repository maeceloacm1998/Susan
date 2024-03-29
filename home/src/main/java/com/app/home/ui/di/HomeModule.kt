package com.app.home.ui.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.home.ui.feature.home.data.HomeRepository
import com.app.home.ui.feature.home.data.HomeRepositoryImpl
import com.app.home.ui.feature.home.domain.GetHomeCurrentLocationUseCase
import com.app.home.ui.feature.home.domain.GetNearbyHospitalsUseCase
import com.app.home.ui.feature.home.domain.GetShowOnboardingUseCase
import com.app.home.ui.feature.home.domain.ObserveHomeCurrentLocationUseCase
import com.app.home.ui.feature.home.domain.UpdateShowOnboardingUseCase
import com.app.home.ui.feature.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeModule {
    @RequiresApi(Build.VERSION_CODES.S)
    val modules = module {
        single<HomeRepository> {
            HomeRepositoryImpl(
                getLocationUseCase = get(),
                getLastCurrentLocationUseCase = get(),
                sharedPreferences = get()
            )
        }

        single { GetHomeCurrentLocationUseCase(homeRepository = get()) }
        single { GetShowOnboardingUseCase(homeRepository = get()) }
        single { UpdateShowOnboardingUseCase(homeRepository = get()) }
        single { GetNearbyHospitalsUseCase(homeRepository = get()) }
        single { ObserveHomeCurrentLocationUseCase(homeRepository = get()) }

        viewModel {
            HomeViewModel(
                getHomeCurrentLocationUseCase = get(),
                observeHomeCurrentLocationUseCase = get(),
                getShowOnboardingUseCase = get()
            )
        }
    }
}