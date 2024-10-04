package com.app.home.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.home.feature.chat.data.ChatRepository
import com.app.home.feature.chat.data.ChatRepositoryImpl
import com.app.home.feature.chat.domain.GetChatCurrentLocationUseCase
import com.app.home.feature.chat.domain.ObserveChatCurrentLocationUseCase
import com.app.home.feature.chat.ui.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ChatModule {
    @RequiresApi(Build.VERSION_CODES.S)
    val modules = module {
        single<ChatRepository> {
            ChatRepositoryImpl(
                getLocationUseCase = get(),
                getLastCurrentLocationUseCase = get()
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
            ChatViewModel()
        }
    }
}