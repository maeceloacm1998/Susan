package com.app.home.feature.chat.domain

import com.app.home.feature.chat.data.ChatRepository
import com.app.home.feature.chat.data.models.ChatMessage

class GetMessageInternalUseCase(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(): List<ChatMessage> {
        return chatRepository.getMessages() ?: emptyList()
    }
}