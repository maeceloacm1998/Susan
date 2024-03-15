package com.app.searchmed.routes

sealed class Routes(val route: String) {
    data object Home : Routes("home")
}