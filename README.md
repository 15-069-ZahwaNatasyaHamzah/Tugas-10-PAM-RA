# NoteApp - Tugas 9 PAM (AI Integration)

A modern, cross-platform Note-Taking application built with **Compose Multiplatform**, now integrated with **Google Gemini AI** for smart note assistance.

## 🤖 AI Integration (Tugas 9)

Project ini telah mengintegrasikan fitur kecerdasan buatan menggunakan **Gemini AI** untuk membantu pengguna mengelola catatan mereka secara lebih efisien.

### Fitur Utama AI:
- **Smart Note Summarizer**: Meringkas isi catatan yang panjang menjadi ringkasan singkat dalam Bahasa Indonesia hanya dengan satu klik.
- **AI-Powered Title Suggestion**: Memberikan saran judul yang relevan berdasarkan isi konten catatan.
- **Responsive UI**: Dilengkapi dengan loading indicators (progress bar) dan penanganan error yang intuitif.

### Implementasi Teknis:
- **Model**: `Gemini 1.5 Flash` (via Google AI Studio).
- **SDK**: `dev.shreyaspatil.generativeai:generativeai-google` (Kotlin Multiplatform SDK).
- **Error Handling**: Implementasi `runCatching` dengan logika **fallback** untuk memastikan aplikasi tidak crash jika koneksi API bermasalah.
- **Prompt Engineering**: System prompt yang dirancang khusus untuk menghasilkan ringkasan dalam Bahasa Indonesia yang profesional namun mudah dipahami.

## 🏗️ Architecture & DI
Aplikasi ini menggunakan **Koin** untuk Dependency Injection, memisahkan logika AI ke dalam service layer yang bersih.

```mermaid
graph TD
    subgraph "UI Layer"
        UI[NoteDetailScreen] --> VM[AiViewModel]
    end
    subgraph "Logic Layer"
        VM --> Service[AiService/GeminiAiService]
        Service --> Gemini[Google Gemini API]
    end
    subgraph "DI (Koin)"
        Koin --> Service
        Koin --> VM
    end
```

## 🛠️ Tech Stack
- **UI Framework**: Compose Multiplatform
- **Language**: Kotlin
- **Dependency Injection**: Koin
- **Database**: SQLDelight
- **AI API**: Google Gemini AI Studio
- **Testing**: MockK & Compose UI Test

## 🔧 Troubleshooting: Fixing Unresolved Reference 'io'

Jika Anda menemui error `Unresolved reference 'io'` saat menjalankan instrumented test (`androidTest`), berikut adalah langkah-langkah perbaikannya:

### 1. Perbaikan Typo Import
Pastikan di file `NotesScreenTest.kt` tidak ada kesalahan pengetikan pada import MockK.
- **Salah**: `import us io.mockk.every`
- **Benar**: `import io.mockk.every`

### 2. Konfigurasi Dependencies
Tambahkan `mockk` ke dalam blok `dependencies` di file `composeApp/build.gradle.kts` agar library tersedia untuk pengujian Android:

```kotlin
dependencies {
    // ...
    androidTestImplementation(libs.mockk)
}
```

### 3. Verifikasi Build
Jalankan perintah berikut di terminal untuk memastikan build berhasil:
```bash
./gradlew :composeApp:compileDebugAndroidTestKotlinAndroid
```

## 📸 Panduan Screenshot untuk Tugas
Untuk melengkapi laporan, silakan ambil screenshot pada bagian-bagian berikut:

1.  **Screenshot Code Fix**: Ambil gambar file `NotesScreenTest.kt` yang menunjukkan baris `import io.mockk.every` sudah benar (tanpa kata `us`).
2.  **Screenshot Build.gradle**: Ambil gambar file `composeApp/build.gradle.kts` pada bagian `dependencies` yang menunjukkan adanya `androidTestImplementation(libs.mockk)`.
3.  **Screenshot Terminal Sukses**: Jalankan perintah `./gradlew :composeApp:compileDebugAndroidTestKotlinAndroid` di terminal bawah Android Studio dan ambil gambar yang menunjukkan pesan **"BUILD SUCCESSFUL"**.
4.  **Screenshot UI Test**: Jika memungkinkan, jalankan test tersebut (klik ikon play hijau di sebelah class `NotesScreenTest`) dan ambil gambar panel "Run" yang menunjukkan semua test berwarna hijau (Passed).

## 🚀 Cara Menjalankan Fitur AI
1. Dapatkan API Key dari [Google AI Studio](https://aistudio.google.com/).
2. Masukkan API Key di file `composeApp/src/commonMain/kotlin/org/example/project/di/Koin.kt`.
3. Jalankan aplikasi pada Android Emulator atau Desktop.
4. Buka salah satu catatan dan klik tombol **"Summarize Note"** di bagian bawah.

## 📸 Screenshots
*(Pastikan file gambar tersedia di folder screenshots/)*

| Dashboard Notes | AI Summarizer |
| :---: | :---: |
| ![Notes List](screenshots/notes_list.png) | ![AI Result](screenshots/ai_result.png) |

---
**Tugas 9 - Pengembangan Aplikasi Mobile**
**Oleh: Jihwi (NoteApp Project)**
