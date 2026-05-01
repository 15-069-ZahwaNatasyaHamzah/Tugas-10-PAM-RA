package org.example.project.platform

import android.os.Build

class AndroidDeviceInfo : DeviceInfo {
    override val deviceName: String = "${Build.MANUFACTURER} ${Build.MODEL}"
    override val osName: String = "Android"
    override val osVersion: String = Build.VERSION.RELEASE
}

actual fun getDeviceInfo(): DeviceInfo = AndroidDeviceInfo()
