package com.app.core.routes

sealed class Routes(val route: String) {
    object Onboarding : Routes("onboarding")
    object CheckPermissions : Routes("check_permissions")
    object Home : Routes("home")
}