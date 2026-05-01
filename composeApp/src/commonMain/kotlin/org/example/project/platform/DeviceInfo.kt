package org.example.project.platform

interface DeviceInfo {
    val deviceName: String
    val osName: String
    val osVersion: String
}

expect fun getDeviceInfo(): DeviceInfo
