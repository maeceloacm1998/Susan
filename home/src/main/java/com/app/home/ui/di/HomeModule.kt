package com.app.home.ui.di

import com.app.home.ui.feature.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeModule {
    val modules = module {
        viewModel {
            HomeViewModel()
        }
    }
}