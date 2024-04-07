package com.app.home.ui.feature.onboarding.data

interface OnboardingRepository {
    fun isShowOnboarding(): Boolean
    fun onFinishOnboarding()
}