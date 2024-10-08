package com.app.home.feature.chat.data.local

import android.content.Context
import com.app.home.feature.chat.data.local.database.ChatMessageDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object ChatMessageDBModule {
    val modules = module {
        single { provideChatMessageDB(androidContext()) }
    }

    private fun provideChatMessageDB(context: Context): ChatMessageDB {
        return ChatMessageDB.getDatabase(context)
    }
}