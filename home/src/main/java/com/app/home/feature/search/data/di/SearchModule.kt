package com.app.home.feature.search.data.di

import com.app.home.feature.search.data.SearchRepository
import com.app.home.feature.search.data.SearchRepositoryImpl
import com.app.home.feature.search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SearchModule {
    val modules = module {
        factory<SearchRepository> { SearchRepositoryImpl() }

        viewModel { SearchViewModel() }
    }
}