package org.example.project.platform

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class IosNetworkMonitor : NetworkMonitor {
    private val _isOnline = MutableStateFlow(true) // Simplified for iOS
    override val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()
}
