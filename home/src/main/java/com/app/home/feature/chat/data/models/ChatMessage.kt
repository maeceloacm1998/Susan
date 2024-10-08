package com.app.home.feature.chat.data.models

data class ChatMessage(
    val id: Int,
    val author: String,
    val message: String,
    val type: String,
    val timestamp: Long
)