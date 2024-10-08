package com.app.home.feature.chat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.home.feature.chat.data.models.ChatMessage

@Dao
interface ChatMessageDAO {
    @Query("SELECT * FROM chat_message_table")
    fun getMessages(): List<ChatMessageEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createMessage(chatMessage: ChatMessageEntity)

    @Query("DELETE FROM chat_message_table")
    fun clearTable()
}