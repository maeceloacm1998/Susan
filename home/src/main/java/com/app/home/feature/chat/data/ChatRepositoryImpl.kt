package com.app.home.feature.chat.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.core.service.location.domain.GetLastCurrentLocationUseCase
import com.app.core.service.location.domain.GetLocationUseCase
import com.app.home.feature.chat.data.local.dao.ChatMessageEntity
import com.app.home.feature.chat.data.local.database.ChatMessageDB
import com.app.home.feature.chat.data.models.ChatMessage
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ChatRepositoryImpl(
    private val getLocationUseCase: GetLocationUseCase,
    private val getLastCurrentLocationUseCase: GetLastCurrentLocationUseCase,
    private val chatMessageDB: ChatMessageDB
) : ChatRepository {
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
                timestamp = it.timestamp,
                author = it.author,
                type = it.type
            )
        }
    }

    override suspend fun createMessage(chatMessage: ChatMessage) {
        val entity = ChatMessageEntity().apply {
            id = chatMessage.id
            message = chatMessage.message
            timestamp = chatMessage.timestamp
            author = chatMessage.author
            type = chatMessage.type
        }
        chatMessageDB.chatMessageDAO().createMessage(entity)
    }

    override suspend fun clearTable() {
        chatMessageDB.chatMessageDAO().clearTable()
    }
}