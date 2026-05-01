package org.example.project.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.ui.theme.AppTheme

import org.example.project.platform.DeviceInfo

@Preview(showBackground = true)
@Composable
fun ProfileScreenAndroidPreview() {
    val mockDeviceInfo = object : DeviceInfo {
        override val deviceName: String = "Preview Device"
        override val osName: String = "Android"
        override val osVersion: String = "13"
    }
    val viewModel = ProfileViewModel(mockDeviceInfo)
    AppTheme(darkTheme = true) {
        ProfileScreen(
            viewModel = viewModel,
            onEditClick = {}
        )
    }
}
