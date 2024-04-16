package com.app.home.locationpermission

import com.app.home.KoinModuleRule
import com.app.home.MainCoroutineRule
import com.app.home.ui.di.LocationPermissionModule
import com.app.home.ui.di.LocationPermissionUseCaseModule
import com.app.home.ui.feature.locationpermission.data.LocationPermissionRepository
import com.app.home.ui.feature.locationpermission.domain.GetLocationActiveUseCase
import com.app.home.ui.feature.locationpermission.domain.OpenManualConfigUseCase
import com.app.home.ui.feature.locationpermission.ui.LocationPermissionViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.assertNotNull

class LocationPermissionModuleTest : KoinTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val koinModuleRule = KoinModuleRule(
        listOf(LocationPermissionUseCaseModule.modules, LocationPermissionModule.modules)
    )

    @Test
    fun checkAllModules() {
        assertNotNull(get<LocationPermissionRepository>())
        assertNotNull(get<LocationPermissionViewModel>())
        assertNotNull(get<GetLocationActiveUseCase>())
        assertNotNull(get<OpenManualConfigUseCase>())
    }
}