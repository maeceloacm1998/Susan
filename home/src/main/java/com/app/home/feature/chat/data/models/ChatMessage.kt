package com.app.home.feature.chat.data.models

import com.app.core.utils.Utils.generateRandomId
import com.app.home.feature.chat.data.external.models.EmergencyData

data class ChatMessage(
    val id: Int = generateRandomId(),
    var message: String,
    var timer: Int,
    val author: String,
    val type: String,
    val timestamp: Long,
    var isLoading: Boolean = false,
    var extraItems: EmergencyData? = null
)