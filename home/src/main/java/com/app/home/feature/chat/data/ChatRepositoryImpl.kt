package com.app.home.feature.chat.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.core.service.location.domain.GetLastCurrentLocationUseCase
import com.app.core.service.location.domain.GetLocationUseCase
import com.app.home.feature.chat.data.external.EmergencyApi
import com.app.home.feature.chat.data.external.models.EmergencyRequest
import com.app.home.feature.chat.data.external.models.EmergencyResponse
import com.app.home.feature.chat.data.local.dao.ChatMessageEntity
import com.app.home.feature.chat.data.local.database.ChatMessageDB
import com.app.home.feature.chat.data.models.ChatMessage
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class ChatRepositoryImpl(
    private val getLocationUseCase: GetLocationUseCase,
    private val getLastCurrentLocationUseCase: GetLastCurrentLocationUseCase,
    private val chatMessageDB: ChatMessageDB,
    private val emergencyApi: EmergencyApi
) : ChatRepository {

    override suspend fun searchEmergency(request: EmergencyRequest): Result<EmergencyResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = emergencyApi.searchEmergency(request)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun handleCurrentLocation(): LatLng? {
        return getLocationUseCase().first()
    }

    override suspend fun observeCurrentLocation(): Flow<LatLng?> = getLastCurrentLocationUseCase()

    override suspend fun getMessages(): List<ChatMessage>? {
        val res = chatMessageDB.chatMessageDAO().getMessages()
        return res?.map {
            ChatMessage(
                id = it.id,
                message = it.message,
                author = it.author,
                type = it.type,
                timer = it.timer,
                isLoading = it.isLoading,
                timestamp = it.timestamp,
                extraItems = it.extraItems
            )
        }
    }

    override suspend fun createMessage(chatMessage: ChatMessage) {
        val entity = ChatMessageEntity().apply {
            id = chatMessage.id
            message = chatMessage.message
            author = chatMessage.author
            type = chatMessage.type
            timer = chatMessage.timer
            isLoading = chatMessage.isLoading
            timestamp = chatMessage.timestamp
            if (chatMessage.extraItems != null) {
                extraItems = chatMessage.extraItems!!
            }
        }
        chatMessageDB.chatMessageDAO().createMessage(entity)
    }

    override suspend fun updateMessage(chatMessage: ChatMessage) {
        chatMessageDB.chatMessageDAO().updateLoading(
            id = chatMessage.id,
            message = chatMessage.message,
            extraItems = chatMessage.extraItems!!,
            isLoading = chatMessage.isLoading
        )
    }

    override suspend fun clearTable() {
        chatMessageDB.chatMessageDAO().clearTable()
    }
}