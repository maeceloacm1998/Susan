package com.app.home.di


import com.app.home.feature.chatcontainer.ChatContainerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ChatContainerModule {
    val modules = module {
        viewModel {
            ChatContainerViewModel()
        }
    }
}