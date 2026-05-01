package org.example.project.di

import org.example.project.data.DatabaseDriverFactory
import org.example.project.platform.JvmNetworkMonitor
import org.example.project.platform.NetworkMonitor
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory() }
    single<NetworkMonitor> { JvmNetworkMonitor() }
}
