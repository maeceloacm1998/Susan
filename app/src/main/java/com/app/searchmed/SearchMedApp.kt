package com.app.searchmed

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.app.searchmed.ui.theme.SearchMedTheme


@Composable
fun SearchMedApp() {
    val navController = rememberNavController()

    SearchMedTheme {
        SearchMedNavGraph(
            navController = navController
        )
    }
}