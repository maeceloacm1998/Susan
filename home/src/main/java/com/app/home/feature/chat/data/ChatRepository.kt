package com.app.home.feature.chat.data

import com.app.home.feature.chat.data.external.models.EmergencyRequest
import com.app.home.feature.chat.data.external.models.EmergencyResponse
import com.app.home.feature.chat.data.models.ChatMessage
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun searchEmergency(request: EmergencyRequest): Result<EmergencyResponse>
    suspend fun handleCurrentLocation(): LatLng?
    suspend fun observeCurrentLocation(): Flow<LatLng?>
    suspend fun getMessages(): List<ChatMessage>?
    suspend fun createMessage(chatMessage: ChatMessage)
    suspend fun updateMessage(chatMessage: ChatMessage)
    suspend fun clearTable()
}