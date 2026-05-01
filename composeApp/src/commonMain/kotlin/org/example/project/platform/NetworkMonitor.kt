package org.example.project.platform

import kotlinx.coroutines.flow.StateFlow

interface NetworkMonitor {
    val isOnline: StateFlow<Boolean>
}
