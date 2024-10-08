package com.app.home.feature.chat.data

import com.app.home.feature.chat.data.models.ChatMessage
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun handleCurrentLocation(): LatLng?
    suspend fun observeCurrentLocation(): Flow<LatLng?>
    suspend fun getMessages(): List<ChatMessage>?
    suspend fun createMessage(chatMessage: ChatMessage)
    suspend fun clearTable()
}