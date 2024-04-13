package com.app.home.features.onboarding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.service.sharedpreferences.SharedPreferencesBuilder
import com.app.home.MainCoroutineRule
import com.app.home.ui.feature.onboarding.data.OnboardingRepository
import com.app.home.ui.feature.onboarding.data.OnboardingRepositoryImpl
import com.app.home.ui.feature.onboarding.data.OnboardingRepositoryImpl.Companion.ONBOARDING_KEY
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertTrue

class OnboardingRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val sharedPreferencesBuilder: SharedPreferencesBuilder = mockk()
    private lateinit var onboardingRepository: OnboardingRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        onboardingRepository = OnboardingRepositoryImpl(sharedPreferencesBuilder)
    }

    @Test
    fun `when onFinishOnboarding is called, check if shared preferences value is changed`() =
        runTest {
            every { sharedPreferencesBuilder.putBoolean(ONBOARDING_KEY, false)  } just Runs

            onboardingRepository.onFinishOnboarding()

            verify { sharedPreferencesBuilder.putBoolean(ONBOARDING_KEY, false) }
        }

    @Test
    fun `when isShowOnboarding is called, check if shared preferences value is changed`() =
        runTest {
            every { sharedPreferencesBuilder.getBoolean(ONBOARDING_KEY, true) } returns true

            onboardingRepository.isShowOnboarding()

            verify { sharedPreferencesBuilder.getBoolean(ONBOARDING_KEY, true) }
        }

    @Test
    fun `when called isShowOnboarding, check if result is equals saved shared preferences`() {
        every { sharedPreferencesBuilder.getBoolean(ONBOARDING_KEY, true) } returns true

        val isShowOnboarding = onboardingRepository.isShowOnboarding()

        assertTrue { isShowOnboarding }
    }
}