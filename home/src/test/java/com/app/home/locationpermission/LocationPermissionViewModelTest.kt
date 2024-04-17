package com.app.home.locationpermission

import androidx.activity.ComponentActivity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import com.app.core.service.location.domain.GetCheckLocationPermissionUseCase
import com.app.core.service.location.domain.GetLocationUseCase
import com.app.core.service.location.domain.UpdateLastCurrentLocationUseCase
import com.app.home.MainCoroutineRule
import com.app.home.awaitExecuteCoroutines
import com.app.home.feature.locationpermission.domain.OpenManualConfigUseCase
import com.app.home.feature.locationpermission.ui.LocationPermissionViewModel
import com.google.android.gms.maps.model.LatLng
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerifyAll
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class LocationPermissionViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val getLocationUseCase: GetLocationUseCase = mockk()
    private val getCheckLocationPermissionUseCase: GetCheckLocationPermissionUseCase = mockk()
    private val updateLastCurrentLocationUseCase: UpdateLastCurrentLocationUseCase = mockk()
    private val openManualConfigUseCase: OpenManualConfigUseCase = mockk()
    private lateinit var locationPermissionViewModel: LocationPermissionViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        locationPermissionViewModel = LocationPermissionViewModel(
            getLocationUseCase = getLocationUseCase,
            getCheckLocationPermissionUseCase = getCheckLocationPermissionUseCase,
            updateLastCurrentLocationUseCase = updateLastCurrentLocationUseCase,
            openManualConfigUseCase = openManualConfigUseCase
        )
    }

    @Test
    fun `when onGetUseLocation is called and location is different null, update last current location should be invoked`() =
        runTest {
            val navController: NavController = mockk(relaxed = true)
            val location = LatLng(0.0, 0.0)
            val context: ComponentActivity = mockk(relaxed = true)

            coEvery { getLocationUseCase() } returns flowOf(location)
            coEvery { updateLastCurrentLocationUseCase(any()) } just Runs

            locationPermissionViewModel.onGetUserLocation(
                navController = navController,
                context = context
            ).awaitExecuteCoroutines(this)

            coVerifyAll {
                getLocationUseCase()
                updateLastCurrentLocationUseCase(location)
            }
        }

    @Test
    fun `when onGetUseLocation is called and location is null, openManualConfig should be invoked`() =
        runTest {
            val navController: NavController = mockk(relaxed = true)
            val context: ComponentActivity = mockk(relaxed = true)

            coEvery { getLocationUseCase() } returns flowOf(null)
            coEvery { openManualConfigUseCase(context) } just Runs

            locationPermissionViewModel.onGetUserLocation(
                navController = navController,
                context = context
            ).awaitExecuteCoroutines(this)

            coVerifyAll {
                getLocationUseCase()
                openManualConfigUseCase(context)
            }
        }

    @Test
    fun `when onOpenManualConfig is called, openAppSpecificSettings should be invoked`() {
        val context: ComponentActivity = mockk(relaxed = true)
        every { openManualConfigUseCase(context) } just Runs

        locationPermissionViewModel.onOpenManualConfig(context)

        verify { openManualConfigUseCase(context) }
    }
}

