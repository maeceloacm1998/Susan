package com.app.core.service.location.domain

import com.app.core.service.location.LocationService

class GetCheckLocationPermissionUseCase(
    private val locationService: LocationService
) {
    operator fun invoke(): Boolean = locationService.checkLocationPermission()
}