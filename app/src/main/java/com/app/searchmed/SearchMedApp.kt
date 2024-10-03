
package com.app.searchmed

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.app.core.ui.theme.SearchMedTheme
import com.app.core.routes.Routes
import com.app.home.feature.locationpermission.domain.GetLocationActiveUseCase
import com.app.home.feature.onboarding.domain.GetOnboardingShowOnboardingUseCase
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.koin.compose.koinInject

@RequiresApi(Build.VERSION_CODES.S)
@ExperimentalPermissionsApi
@Composable
fun SearchMedApp() {
    val navController = rememberNavController()
    val getLocationActiveUseCase: GetLocationActiveUseCase = koinInject()
    val getOnboardingShowOnboardingUseCase: GetOnboardingShowOnboardingUseCase = koinInject()

    val startDestination = when {
        getOnboardingShowOnboardingUseCase() -> Routes.Onboarding.route
        !getLocationActiveUseCase() -> Routes.CheckPermissions.route
        else -> Routes.Chat.route
    }

    SearchMedTheme {
        SearchMedNavGraph(
            navController = navController,
            startDestination = startDestination
        )
    }
}