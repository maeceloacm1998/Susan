package com.app.home.ui.feature.locationpermission.domain

import androidx.activity.ComponentActivity
import com.app.core.service.location.utils.LocationUtils

class OpenManualConfigUseCase {
    operator fun invoke(activity: ComponentActivity) = LocationUtils.openAppSpecificSettings(activity)
}