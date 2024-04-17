package com.app.home.feature.onboarding.data

interface OnboardingRepository {
    fun isShowOnboarding(): Boolean
    fun onFinishOnboarding()
}