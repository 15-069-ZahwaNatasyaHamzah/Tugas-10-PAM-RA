# NoteApp - Tugas 8 PAM (Platform Features & DI)

A modern, cross-platform Note-Taking application built with **Compose Multiplatform**, upgraded with Dependency Injection and Platform-specific features.

## 🚀 Upgrade Features (Task 8)

1.  **Koin Dependency Injection**: Full app migration to Koin DI. All components (Repository, ViewModels, Platform Services) are now managed and injected through Koin.
2.  **Platform DeviceInfo**: Implementation of `DeviceInfo` using `expect`/`actual` to retrieve device name, OS, and version across Android, iOS, JVM, and Web.
3.  **Real-time NetworkMonitor**: Implementation of `NetworkMonitor` using `expect`/`actual` to track internet connectivity status.
4.  **Settings Integration**: Device information is dynamically displayed on the Profile/Settings screen.
5.  **Network Status Indicator**: A prominent "Offline Mode" indicator appears on the main screen when the device loses connection.
6.  **Koin-injected ViewModels**: ViewModels are provided using `koinViewModel()` from the `io.insert-koin:koin-compose-viewmodel` library.

## 🏗️ Architecture Diagram

The application uses a Clean Architecture approach with Koin as the central Dependency Injection container.

```mermaid
graph TD
    subgraph "UI Layer (Common)"
        App[App.kt - Main Entry]
        Screens[Screens - Notes, Profile, Add/Edit]
        ViewModels[ViewModels - NotesViewModel, ProfileViewModel]
    end

    subgraph "Domain/Data Layer (Common)"
        Repository[NoteRepository]
        Database[NoteDatabase - SQLDelight]
    end

    subgraph "Platform Layer (Expect/Actual)"
        DeviceInfo[DeviceInfo Interface]
        NetworkMonitor[NetworkMonitor Interface]
        DI_Platform[Platform DI Module]
    end

    subgraph "DI (Koin)"
        Koin[Koin Container]
    end

    ViewModels --> Repository
    Repository --> Database
    ViewModels --> DeviceInfo
    App --> NetworkMonitor
    Koin --> ViewModels
    Koin --> Repository
    Koin --> DeviceInfo
    Koin --> NetworkMonitor
```

## 📸 Screenshots

| Device Info (Settings) | Network Indicator (Offline) |
| :---: | :---: |
| ![Device Info](screenshots/device_info.png) | ![Network Indicator](screenshots/network_indicator.png) |

## 🎥 Video Demo
A 45-second demo video showing Koin DI initialization, Device Info display, and real-time Network Status (On/Off) transitions can be found here:
**[Link to Video Demo](video/demo.mp4)**

---

## 🛠️ Tech Stack

-   **UI Framework**: [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
-   **Dependency Injection**: [Koin 4.0.0](https://insert-koin.io/)
-   **Database**: [SQLDelight](https://cashapp.github.io/sqldelight/)
-   **Navigation**: [Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation)

## 🏗️ Getting Started

### Prerequisites
- Android Studio Ladybug or later.
- JDK 17 or later.

### Running the App
- **Android**: Select `composeApp` and run on an emulator or device.
- **Desktop**: Run `./gradlew :composeApp:run`.
- **Web**: Run `./gradlew :composeApp:jsBrowserDevelopmentRun`.
