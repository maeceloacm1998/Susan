package com.app.home.ui.feature.locationpermission.data

import com.app.core.service.location.domain.GetCheckLocationPermissionUseCase

class LocationPermissionRepositoryImpl(
    private val getCheckLocationPermissionUseCase: GetCheckLocationPermissionUseCase
): LocationPermissionRepository {
    override fun isLocationActive(): Boolean = getCheckLocationPermissionUseCase()
}