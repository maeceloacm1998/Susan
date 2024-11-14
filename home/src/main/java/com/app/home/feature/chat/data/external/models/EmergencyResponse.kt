package com.app.home.feature.chat.data.external.models

data class EmergencyResponse(
    val status: String,
    val result: ResultData
)

data class ResultData(
    val message: String,
    val data: EmergencyData
)

data class EmergencyData(
    val name: String? = null,
    val distance: Double? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
    val lat: Double? = null,
    val lng: Double? = null
)