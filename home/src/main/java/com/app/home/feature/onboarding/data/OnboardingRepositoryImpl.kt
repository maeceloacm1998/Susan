package com.app.home.feature.onboarding.data

import com.app.core.service.sharedpreferences.SharedPreferencesBuilder

class OnboardingRepositoryImpl(
    private val sharedPreferences: SharedPreferencesBuilder
): OnboardingRepository {
    override fun onFinishOnboarding() {
        sharedPreferences.putBoolean(ONBOARDING_KEY, false)
    }

    override fun isShowOnboarding(): Boolean {
        return sharedPreferences.getBoolean(ONBOARDING_KEY, true)
    }

    companion object {
        const val ONBOARDING_KEY = "onboarding"
    }
}