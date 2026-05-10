# MyProfile & Notes App - Kotlin Multiplatform

Aplikasi pencatat modern lintas platform yang dibangun dengan Compose Multiplatform, berfokus pada implementasi Pengujian Unit dan Pengujian UI yang tangguh untuk tugas ke-10.

## Testing Implementation 

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

## Troubleshooting: Fixing Unresolved Reference 'io'

Selama pengerjaan, ditemukan error `Unresolved reference 'io'` yang telah diperbaiki dengan langkah berikut:

1.  **Perbaikan Typo Import**: Mengubah `import us io.mockk.every` menjadi `import io.mockk.every` di file `NotesScreenTest.kt`.
2.  **Konfigurasi Dependencies**: Menambahkan `androidTestImplementation(libs.mockk)` ke dalam file `composeApp/build.gradle.kts` agar library MockK tersedia di lingkungan testing Android.

## Tech Stack
- **UI Framework**: Compose Multiplatform
- **Language**: Kotlin
- **Mocking Library**: MockK
- **Testing Framework**: Compose UI Test & JUnit4
- **Database**: SQLDelight (Backend)

## Dokumentasi Visual

Silakan masukkan hasil screenshot Anda ke dalam folder `screenshots/` dengan nama file yang sesuai agar muncul di tabel bawah ini:

| No | Deskripsi Screenshot | Preview |
|:--:|:---:|:--:|
| 1 |Perbaikan typo import MockK di `NotesScreenTest.kt` |<img width="1009" height="950" alt="WhatsApp Image 2026-05-11 at 02 07 52" src="https://github.com/user-attachments/assets/38ac283b-ea42-4669-bb33-eb7069c4353f" /> |
| 2 |Penambahan dependency `mockk` di `build.gradle.kts` |<img width="1010" height="950" alt="WhatsApp Image 2026-05-11 at 02 07 52 (1)" src="https://github.com/user-attachments/assets/b60d503a-6763-4013-9c63-db32a36a8ba6" /> |
| 3 |Bukti "10 tests passed" dan "BUILD SUCCESSFUL" dan Implementasi Unit Test di `NoteRepositoryTest.kt` |<img width="1371" height="408" alt="WhatsApp Image 2026-05-11 at 02 07 52 (2)" src="https://github.com/user-attachments/assets/e4cd67c3-1d6e-47f0-b5f8-97c8daf3db3c" /> |

## Video Demo
Video demo fitur aplikasi dapat diakses melalui tautan berikut :

---
