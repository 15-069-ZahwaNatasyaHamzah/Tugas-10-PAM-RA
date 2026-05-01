package org.example.project.platform

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.InetAddress

class JvmNetworkMonitor : NetworkMonitor {
    private val _isOnline = MutableStateFlow(true) // JVM target usually has persistent connection or different monitoring needs
    override val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()
}
