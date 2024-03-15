package com.app.searchmed.routes

sealed class Routes(val route: String) {
    object Home : Routes("home")
}