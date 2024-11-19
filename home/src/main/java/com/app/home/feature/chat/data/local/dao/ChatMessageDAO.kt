package com.app.home.feature.chat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.home.feature.chat.data.external.models.EmergencyData

@Dao
interface ChatMessageDAO {
    @Query("SELECT * FROM chat_message_table")
    fun getMessages(): List<ChatMessageEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createMessage(chatMessage: ChatMessageEntity)

    @Query("UPDATE chat_message_table SET message= :message, timer= :timer, extraItems= :extraItems, isLoading= :isLoading WHERE id = :id")
    fun updateLoading(id: Int, message: String, timer: Int, extraItems: EmergencyData, isLoading: Boolean)

    @Query("DELETE FROM chat_message_table")
    fun clearTable()
}