package com.app.home.feature.chat.domain

import com.app.home.feature.chat.data.ChatRepository
import com.app.home.feature.chat.data.models.ChatMessage

class UpdateMessageInternalUseCase(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(chatMessage: ChatMessage) {
        chatRepository.updateMessage(chatMessage)
    }
}