package org.example.project.platform

import kotlinx.browser.window

class JsDeviceInfo : DeviceInfo {
    override val deviceName: String = "Web Browser"
    override val osName: String = window.navigator.userAgent
    override val osVersion: String = "Web"
}

actual fun getDeviceInfo(): DeviceInfo = JsDeviceInfo()
