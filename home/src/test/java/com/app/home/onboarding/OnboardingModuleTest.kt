package com.app.home.onboarding

import com.app.home.KoinModuleRule
import com.app.home.MainCoroutineRule
import com.app.home.ui.feature.locationpermission.data.LocationPermissionRepository
import com.app.home.ui.feature.locationpermission.data.LocationPermissionRepositoryImpl
import com.app.home.ui.feature.locationpermission.domain.GetLocationActiveUseCase
import com.app.home.ui.feature.onboarding.data.OnboardingRepository
import com.app.home.ui.feature.onboarding.data.OnboardingRepositoryImpl
import com.app.home.ui.feature.onboarding.domain.UpdateOnboardingShowOnboardingUseCase
import com.app.home.ui.feature.onboarding.ui.OnboardingViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get

class OnboardingModuleTest : KoinTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val koinModuleRule = KoinModuleRule(
        listOf(
            module {
                single<LocationPermissionRepository> {
                    LocationPermissionRepositoryImpl(context = mockk(relaxed = true))
                }
                single { UpdateOnboardingShowOnboardingUseCase(onboardingRepository = get()) }
                single { GetLocationActiveUseCase(locationPermissionRepository = get()) }
                single<OnboardingRepository> {
                    OnboardingRepositoryImpl(
                        sharedPreferences = get()
                    )
                }
                viewModel {
                    OnboardingViewModel(
                        getHomeLocationActiveUseCase = get(),
                        updateShowOnboardingUseCase = get()
                    )
                }
            }
        )
    )

    @Test
    fun checkAllModules() {
        assertNotNull(get<OnboardingRepository>())
        assertNotNull(get<GetLocationActiveUseCase>())
        assertNotNull(get<UpdateOnboardingShowOnboardingUseCase>())
        assertNotNull(get<OnboardingViewModel>())
        assertNotNull(get<LocationPermissionRepository>())
    }
}