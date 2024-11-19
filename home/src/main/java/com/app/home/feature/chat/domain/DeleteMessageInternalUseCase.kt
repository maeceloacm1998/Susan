package com.app.home.feature.chat.domain

import com.app.home.feature.chat.data.ChatRepository

class DeleteMessageInternalUseCase(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke() {
        chatRepository.clearTable()
    }
}