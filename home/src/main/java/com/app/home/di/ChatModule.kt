package com.app.home.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.home.feature.chat.data.ChatRepository
import com.app.home.feature.chat.data.ChatRepositoryImpl
import com.app.home.feature.chat.data.external.EmergencyApi
import com.app.home.feature.chat.domain.CreateMessageInternalUseCase
import com.app.home.feature.chat.domain.GetChatCurrentLocationUseCase
import com.app.home.feature.chat.domain.GetChatEmergencyUseCase
import com.app.home.feature.chat.domain.GetMessageInternalUseCase
import com.app.home.feature.chat.domain.ObserveChatCurrentLocationUseCase
import com.app.home.feature.chat.domain.UpdateMessageInternalUseCase
import com.app.home.feature.chat.ui.ChatViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ChatModule {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)  // Increase connection timeout
        .readTimeout(60, TimeUnit.SECONDS)     // Increase read timeout
        .writeTimeout(60, TimeUnit.SECONDS)    // Increase write timeout
        .build()

    @RequiresApi(Build.VERSION_CODES.S)
    val modules = module {
        single<EmergencyApi> {
            Retrofit.Builder()
                .baseUrl("https://susan-api.onrender.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EmergencyApi::class.java)
        }

        single<ChatRepository> {
            ChatRepositoryImpl(
                getLocationUseCase = get(),
                getLastCurrentLocationUseCase = get(),
                emergencyApi = get(),
                chatMessageDB = get()
            )
        }

        single {
            GetChatEmergencyUseCase(
                chatRepository = get()
            )
        }

        single {
            CreateMessageInternalUseCase(
                chatRepository = get()
            )
        }

        single {
            GetMessageInternalUseCase(
                chatRepository = get()
            )
        }

        single {
            UpdateMessageInternalUseCase(
                chatRepository = get()
            )
        }

        single {
            GetChatCurrentLocationUseCase(
                chatRepository = get(),
                getLocationActiveUseCase = get()
            )
        }
        single { ObserveChatCurrentLocationUseCase(chatRepository = get()) }

        viewModel {
            ChatViewModel(
                getMessageInternalUseCase = get(),
                createMessageInternalUseCase = get(),
                getChatEmergencyUseCase = get(),
                updateMessageInternalUseCase = get()
            )
        }
    }
}