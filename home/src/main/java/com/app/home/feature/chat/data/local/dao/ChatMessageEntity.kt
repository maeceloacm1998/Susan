package com.app.home.feature.chat.data.local.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_message_table")
class ChatMessageEntity {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "author")
    var author: String = ""

    @ColumnInfo(name = "email")
    var type: String = ""

    @ColumnInfo(name = "message")
    var message: String = ""

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = 0
}