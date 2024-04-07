package com.app.home.ui.feature.locationpermission.domain

import com.app.home.ui.feature.locationpermission.data.LocationPermissionRepository

class GeLocationActiveUseCase(
    private val locationPermissionRepository: LocationPermissionRepository
) {
    operator fun invoke(): Boolean = locationPermissionRepository.isLocationActive()
}