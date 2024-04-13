package com.app.home.onboarding

import com.app.home.KoinModuleRule
import com.app.home.MainCoroutineRule
import com.app.home.ui.feature.onboarding.data.OnboardingRepository
import com.app.home.ui.feature.onboarding.data.OnboardingRepositoryImpl
import com.app.home.ui.feature.onboarding.domain.GetOnboardingShowOnboardingUseCase
import com.app.home.ui.feature.onboarding.domain.UpdateOnboardingShowOnboardingUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.assertNotNull

class OnboardingUseCaseModuleTest: KoinTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val koinModuleRule = KoinModuleRule(
        listOf(
            module {
                single<OnboardingRepository> {
                    OnboardingRepositoryImpl(
                        sharedPreferences = get()
                    )
                }
                single { GetOnboardingShowOnboardingUseCase(onboardingRepository = get()) }
                single { UpdateOnboardingShowOnboardingUseCase(onboardingRepository = get()) }
            }
        )
    )

    @Test
    fun checkAllModules() {
        assertNotNull(get<GetOnboardingShowOnboardingUseCase>())
        assertNotNull(get<UpdateOnboardingShowOnboardingUseCase>())
    }
}