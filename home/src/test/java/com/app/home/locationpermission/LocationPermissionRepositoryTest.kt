package com.app.home.locationpermission

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.service.location.domain.GetCheckLocationPermissionUseCase
import com.app.home.MainCoroutineRule
import com.app.home.feature.locationpermission.data.LocationPermissionRepository
import com.app.home.feature.locationpermission.data.LocationPermissionRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertTrue

class LocationPermissionRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val getCheckLocationPermissionUseCase: GetCheckLocationPermissionUseCase = mockk(relaxed = true)
    private lateinit var locationPermissionRepository: LocationPermissionRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        locationPermissionRepository = LocationPermissionRepositoryImpl(getCheckLocationPermissionUseCase)
    }

    @Test
    fun `when isLocationActive is called, check if location method is called`() {
        locationPermissionRepository.isLocationActive()

        verify {
            getCheckLocationPermissionUseCase()
        }
    }

    @Test
    fun `when isLocationActive is called, check if location is true`() {
        every { getCheckLocationPermissionUseCase() } returns true

        val isLocationActive = locationPermissionRepository.isLocationActive()

        assertTrue(isLocationActive)
    }

    @Test
    fun `when isLocationActive is called, check if location is false`() {
        every { getCheckLocationPermissionUseCase() } returns false

        val isLocationActive = locationPermissionRepository.isLocationActive()

        assertFalse(isLocationActive)
    }
}