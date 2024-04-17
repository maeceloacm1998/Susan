package com.app.home.feature.locationpermission.domain

import com.app.home.ui.feature.locationpermission.data.LocationPermissionRepository

class GetLocationActiveUseCase(
    private val locationPermissionRepository: LocationPermissionRepository
) {
    operator fun invoke(): Boolean = locationPermissionRepository.isLocationActive()
}