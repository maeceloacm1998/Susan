package com.app.home.feature.chat.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.home.feature.chat.data.local.dao.ChatMessageDAO
import com.app.home.feature.chat.data.local.dao.ChatMessageEntity

@Database(entities = [ChatMessageEntity::class], version = 1)
abstract class ChatMessageDB  : RoomDatabase() {
    abstract fun chatMessageDAO(): ChatMessageDAO

    companion object {
        private lateinit var INSTANCE: ChatMessageDB
        private const val DATABASE_NAME = "chat_message_database"

        fun getDatabase(context: Context): ChatMessageDB {
            if(!::INSTANCE.isInitialized) {
                synchronized(ChatMessageDB::class.java) {
                    INSTANCE = Room.databaseBuilder(context, ChatMessageDB::class.java, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}