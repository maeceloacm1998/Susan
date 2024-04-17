package com.app.searchmed

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.core.routes.Routes
import com.app.core.utils.AnimatedUtils.animatedTransitionFadeIn
import com.app.core.utils.AnimatedUtils.animatedTransitionFadeOut
import com.app.home.feature.home.ui.HomeRoute
import com.app.home.feature.locationpermission.ui.LocationPermissionRoute
import com.app.home.feature.onboarding.ui.OnboardingRoute
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SearchMedNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = animatedTransitionFadeIn(),
        exitTransition = animatedTransitionFadeOut()
    ) {
        composable(route = Routes.Onboarding.route) {
            OnboardingRoute(navController)
        }

        composable(route = Routes.CheckPermissions.route) {
            LocationPermissionRoute(navController)
        }

        composable(route = Routes.Home.route) {
            HomeRoute()
        }
    }
}