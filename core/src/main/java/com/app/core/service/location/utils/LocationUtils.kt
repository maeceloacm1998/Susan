package com.app.core.service.location.utils

import android.content.Intent
import android.provider.Settings
import androidx.activity.ComponentActivity

object LocationUtils {
    fun openAppSpecificSettings(activity: ComponentActivity) {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = android.net.Uri.fromParts("package", activity.packageName, null)
        }
        activity.startActivity(intent)
    }
}