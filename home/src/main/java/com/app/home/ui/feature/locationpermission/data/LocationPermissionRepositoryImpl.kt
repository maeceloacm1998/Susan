package com.app.home.ui.feature.locationpermission.data

import android.content.Context
import com.app.core.service.location.utils.LocationUtils

class LocationPermissionRepositoryImpl(
    private val context: Context
): LocationPermissionRepository {
    override fun isLocationActive(): Boolean {
        return LocationUtils.checkLocationPermission(context)
    }
}