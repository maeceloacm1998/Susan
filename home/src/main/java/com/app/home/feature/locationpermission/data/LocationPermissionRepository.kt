package com.app.home.feature.locationpermission.data

interface LocationPermissionRepository {
    fun isLocationActive(): Boolean
}