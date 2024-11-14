package com.app.home.feature.chat.data.local.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.app.home.feature.chat.data.external.models.EmergencyData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "chat_message_table")
@TypeConverters(Converters::class)
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

    @ColumnInfo(name = "timer")
    var timer: Int = 0

    @ColumnInfo(name = "isLoading")
    var isLoading: Boolean = false

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = 0

    @ColumnInfo(name = "extraItems")
    var extraItems: EmergencyData = EmergencyData("", 0.0, "", "", 0.0, 0.0)
}

class Converters {
    @TypeConverter
    fun fromExtraItems(extraItems: EmergencyData): String {
        return Gson().toJson(extraItems)
    }

    @TypeConverter
    fun toExtraItems(extraItemsString: String): EmergencyData {
        val type = object : TypeToken<EmergencyData>() {}.type
        return Gson().fromJson(extraItemsString, type)
    }
}