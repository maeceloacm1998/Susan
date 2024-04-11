package com.app.home

import android.content.Context
import com.app.core.service.di.ServiceDI
import io.mockk.mockk
import io.mockk.mockkClass
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.test.mock.MockProvider

class KoinModuleRule(private val modules: List<Module> = emptyList()) : TestWatcher() {
    override fun starting(description: Description) {
        super.starting(description)
        val context = mockk<Context>(relaxed = true)

        if (GlobalContext.getOrNull() == null) {
            startKoin {
                MockProvider.register { mockkClass(it) }
                androidContext(context)
                printLogger(Level.DEBUG)
                modules(ServiceDI.instance)
                modules(modules)
            }
        }
    }

    override fun finished(description: Description) {
        super.finished(description)
        stopKoin()
    }
}