package org.example.project.di

import org.example.project.data.DatabaseDriverFactory
import org.example.project.platform.AndroidNetworkMonitor
import org.example.project.platform.NetworkMonitor
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

import org.example.project.platform.getDeviceInfo

actual fun platformModule() = module {
    single { DatabaseDriverFactory(androidContext()) }
    single<NetworkMonitor> { AndroidNetworkMonitor(androidContext()) }
    single { getDeviceInfo() }
}
