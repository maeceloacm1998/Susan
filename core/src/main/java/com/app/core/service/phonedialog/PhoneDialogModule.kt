package com.app.core.service.phonedialog

import org.koin.dsl.module

object PhoneDialogModule {
    val modules = module {
        factory { OpenPhoneDialogUseCase() }
    }
}