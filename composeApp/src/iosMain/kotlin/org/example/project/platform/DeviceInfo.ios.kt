package org.example.project.platform

import platform.UIKit.UIDevice

class IosDeviceInfo : DeviceInfo {
    override val deviceName: String = UIDevice.currentDevice.name
    override val osName: String = UIDevice.currentDevice.systemName
    override val osVersion: String = UIDevice.currentDevice.systemVersion
}

actual fun getDeviceInfo(): DeviceInfo = IosDeviceInfo()
