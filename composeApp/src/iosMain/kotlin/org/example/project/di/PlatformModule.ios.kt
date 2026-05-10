package org.example.project.di

import org.example.project.data.DatabaseDriverFactory
import org.example.project.platform.IosNetworkMonitor
import org.example.project.platform.NetworkMonitor
import org.koin.dsl.module

import org.example.project.platform.getDeviceInfo

actual fun platformModule() = module {
    single { DatabaseDriverFactory() }
    single<NetworkMonitor> { IosNetworkMonitor() }
    single { getDeviceInfo() }
}
