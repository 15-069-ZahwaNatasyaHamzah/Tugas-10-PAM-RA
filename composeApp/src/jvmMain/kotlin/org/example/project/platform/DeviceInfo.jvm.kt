package org.example.project.platform

class JvmDeviceInfo : DeviceInfo {
    override val deviceName: String = System.getProperty("os.arch") ?: "Unknown JVM Device"
    override val osName: String = System.getProperty("os.name") ?: "JVM"
    override val osVersion: String = System.getProperty("os.version") ?: "Unknown"
}

actual fun getDeviceInfo(): DeviceInfo = JvmDeviceInfo()
