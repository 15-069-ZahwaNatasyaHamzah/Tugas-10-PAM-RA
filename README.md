# NoteApp - Tugas 10 PAM (Testing)

A modern, cross-platform Note-Taking application built with **Compose Multiplatform**, focused on implementing robust **Unit Testing** and **UI Testing** for the 10th assignment.

## 🧪 Testing Implementation (Tugas 10)

Project ini difokuskan pada implementasi pengujian otomatis untuk memastikan kualitas kode dan fungsionalitas UI berjalan dengan baik.

### Fitur Utama Pengujian:
- **Unit Testing**: Menguji logika bisnis pada `NoteRepository` menggunakan **MockK** untuk memverifikasi penyimpanan dan pengambilan data.
- **UI/Instrumented Testing**: Menguji komponen antarmuka pengguna pada `NotesScreen` menggunakan **Compose Test Rule** untuk memastikan elemen UI seperti judul, search bar, dan tombol tambah muncul dengan benar.

### Implementasi Teknis:
- **Framework Pengujian**: `androidx.compose.ui:ui-test-junit4` dan `io.mockk:mockk`.
- **Mocking Strategy**: Menggunakan **MockK** untuk membuat repository palsu sehingga pengujian UI dapat berjalan secara terisolasi tanpa bergantung pada database asli.
- **Test Scenarios**:
    - Memverifikasi kemunculan teks utama "My Notes".
    - Memverifikasi fungsi input pada kolom pencarian "Search notes...".
    - Memverifikasi keberadaan Floating Action Button (FAB) dengan deskripsi konten "Add Note".

## 🔧 Troubleshooting: Fixing Unresolved Reference 'io'

Selama pengerjaan, ditemukan error `Unresolved reference 'io'` yang telah diperbaiki dengan langkah berikut:

1.  **Perbaikan Typo Import**: Mengubah `import us io.mockk.every` menjadi `import io.mockk.every` di file `NotesScreenTest.kt`.
2.  **Konfigurasi Dependencies**: Menambahkan `androidTestImplementation(libs.mockk)` ke dalam file `composeApp/build.gradle.kts` agar library MockK tersedia di lingkungan testing Android.

## 🏗️ Tech Stack
- **UI Framework**: Compose Multiplatform
- **Language**: Kotlin
- **Mocking Library**: MockK
- **Testing Framework**: Compose UI Test & JUnit4
- **Database**: SQLDelight (Backend)

## 📸 Dokumentasi Screenshot (Tugas 10)

Silakan masukkan hasil screenshot Anda ke dalam folder `screenshots/` dengan nama file yang sesuai agar muncul di tabel bawah ini:

| No | Nama File (Rekomendasi) | Deskripsi Screenshot | Preview |
|:--:|:---|:---|:--:|
| 1 | `ss_code_fix.png` | Perbaikan typo import MockK di `NotesScreenTest.kt` | ![Fix](screenshots/ss_code_fix.png) |
| 2 | `ss_build_gradle.png` | Penambahan dependency `mockk` di `build.gradle.kts` | ![Gradle](screenshots/ss_build_gradle.png) |
| 3 | `ss_test_results.png` | Bukti "10 tests passed" dan "BUILD SUCCESSFUL" | ![Results](screenshots/ss_test_results.png) |
| 4 | `ss_unit_test.png` | Implementasi Unit Test di `NoteRepositoryTest.kt` | ![Unit Test](screenshots/ss_unit_test.png) |

---
**Tugas 10 - Pengembangan Aplikasi Mobile**
**Oleh: Jihwi (NoteApp Project)**
