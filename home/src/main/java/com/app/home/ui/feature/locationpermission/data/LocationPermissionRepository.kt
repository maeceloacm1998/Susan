package com.app.home.ui.feature.locationpermission.data

interface LocationPermissionRepository {
    fun isLocationActive(): Boolean
}