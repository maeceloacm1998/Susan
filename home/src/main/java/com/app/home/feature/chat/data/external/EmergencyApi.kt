package com.app.home.feature.chat.data.external

import com.app.home.feature.chat.data.external.models.EmergencyRequest
import com.app.home.feature.chat.data.external.models.EmergencyResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface EmergencyApi {
    @POST("api/search/emergency")
    suspend fun searchEmergency(@Body request: EmergencyRequest): EmergencyResponse
}