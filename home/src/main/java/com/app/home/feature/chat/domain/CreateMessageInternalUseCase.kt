package com.app.home.feature.chat.domain

import com.app.home.feature.chat.data.ChatRepository
import com.app.home.feature.chat.data.models.ChatMessage

class CreateMessageInternalUseCase(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(message: ChatMessage) = chatRepository.createMessage(message)
}