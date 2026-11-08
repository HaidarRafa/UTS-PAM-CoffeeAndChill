# Coffee and Chill (UTS PAM)
## NAMA : Haidar Rafa Radinka 
## NIM  : 1247050010

Proyek ini disusun untuk memenuhi **Tugas Ujian Tengah Semester (UTS) Mata Kuliah Pengembangan Aplikasi Mobile**.
Aplikasi pemesanan kopi dan snacks berbasis **Android Native (Kotlin)** yang didesain dengan antarmuka modern, estetik, responsif, dan fitur yang sesuai.

## Deskripsi Aplikasi

**Coffee and Chill** adalah aplikasi mobile untuk Android yang memudahkan pengguna untuk memesan berbagai varian kopi, minuman non-kopi, jus, makanan utama, hingga cemilan. Aplikasi ini dilengkapi dengan fitur keranjang belanja multi-item (*multi-select cart*), pengisian data profil & alamat otomatis, serta pencatatan riwayat pemesanan secara *real-time*. Tentunya aplikasi ini ramah digunakan oleh semua kalangan umur karena dibuat agar tidak merumitkan pengguna. Aplikasi ini mengusung tema **Warm Coffee Theme** (latte cream `#FAF5EF`, mocha `#E8D8C8`, dan espresso roast `#2D1E18`).

## Fitur-Fitur Utama

1. **Welcome Screen**
   - Tampilan pembuka yang hangat dengan logo pilihan saya, slogan menarik, serta latar belakang gradien hangat.
2. **Sign Up Landing**
   - Form pendaftaran pengguna baru untuk menyimpan data nama lengkap dan username.
3. **Login User**
   - Halaman masuk pengguna.
4. **Home Menu & Filter Kategori**
   - Menampilkan daftar menu menarik.
   - Fitur filter kategori instan: **All**, **Coffee**, **Non-coffee**, **Juice**, **Food**, dan **Snacks**.
   - Menentukan kuantitas per item yang memungkinkan pembelian beberapa menu sekaligus.
5. **Floating Cart Summary Bar**
   - Ringkasan keranjang belanja melayang (*floating bar*) di bagian bawah Home yang menampilkan total item & total harga secara real-time.
6. **Profile & Setting Alamat Utama**
   - Fitur untuk melihat dan mengelola Nama Lengkap, Alamat Utama Pengiriman, dan Detail Lokasi.
7. **Checkout & Order Form**
   - Ringkasan rincian produk yang dibeli beserta subtotal harganya.
   - Terhubung secara otomatis dengan data nama & alamat dari profil pengguna.
8. **Confirmation Screen**
   - Halaman konfirmasi pesanan sukses yang menyapa pengguna secara personal beserta ilustrasi pendaran espresso.
9. **Riwayat Pemesanan Real-Time**
   - Mencatat dan menampilkan setiap transaksi yang berhasil dilakukan secara real-time, lengkap dengan tanggal, rincian menu, subtotal harga per item, dan total bayar.
10. **Custom Floating Bottom Navbar**
    - Navigasi 3 tab minimalis dan indikator aktif lingkaran espresso.


## Teknologi & Library yang Digunakan

* **Bahasa Pemrograman**: Kotlin 100%
* **Min SDK**: API 27 (Android 8.1 Oreo)
* **Target SDK**: API 37 (Android 15+)
* **Arsitektur & Komponen**:
  - `ViewBinding` (Interaksi UI yang aman dan bebas null)
  - `Material Design 3 Components` (MaterialCardView, MaterialButton, ChipGroup, ShapeableImageView)
  - `SharedPreferences` (`UserSession` untuk penyimpanan sesi & alamat pengguna)
  - `RecyclerView` & `Adapters` (Daftar menu & riwayat transaksi dinamis)
  - `NestedScrollView` & `ConstraintLayout` (Tata letak responsif dan bebas terpotong keyboard)
  - `Native Vector Graphics XML` (Grafik gradien & ornamen biji kopi berukuran ringan)

## Cara Menjalankan Aplikasi

1. **Clone Repository GitHub**:
   ```bash
   git clone https://github.com/HaidarRafa/UTS-PAM-CoffeeAndChill.git
   ```
2. **Buka Proyek di Android Studio**:
   - Jalankan Android Studio (versi Ladybug / 2024.2+ direkomendasikan).
   - Pilih **File > Open**, lalu arahkan ke folder proyek ini.
3. **Sinkronisasi Gradle**:
   - Tunggu hingga Android Studio selesai melakukan **Gradle Sync**.
   - Jika tombol sync muncul, klik **Sync Project with Gradle Files**.
4. **Jalankan Aplikasi**:
   - Hubungkan Emulator Android (API 27+) atau HP Android via USB Debugging.
   - Tekan tombol **Run 'app'** (`Shift + F10`) pada Android Studio.

## Screenshot Tampilan Aplikasi

[Welcome Screen](image.png)
[Sign Up Landing](image-1.png)
[Sign Up Menu](image-2.png)
[Home Screen With Category](image-3.png)
[Home Screen With Order](image-4.png)
[Order Form](image-5.png)
[Confirmation Screen](image-6.png)
[Riwayat Pemesanan](image-7.png)
[Profile & Alamat Setting](image-8.png)