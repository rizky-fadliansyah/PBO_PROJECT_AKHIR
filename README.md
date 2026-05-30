## Aplikasi Ulasan Pembeli

Aplikasi desktop berbasis Java Swing untuk mengelola ulasan pembeli pada sebuah toko agar mempermudah komentar atau komunikasi antar penjual dan pembeli. Pengguna dapat menambah, memperbarui, menghapus, dan melihat daftar ulasan produk melalui antarmuka grafis (GUI).

## Kebutuhan

- **Java Development Kit (JDK) 17** atau lebih baru  
  Download: https://www.oracle.com/java/technologies/downloads/

Tidak memerlukan library eksternal. Semua dependensi sudah tersedia di dalam JDK standar (Java Swing).

## Struktur File

- Main.java           # Entry point + GUI (JFrame)
- Ulasan.java         # Model data ulasan
- UlasanService.java  # Logika bisnis CRUD ulasan
- Pembeli.java        # Model data pembeli
- Penjual.java        # Abstract class penjual
- Reseller.java       # Subclass Penjual (reseller)
- Grosir.java         # Subclass Penjual (grosir)
- Printer.java        # Utility cetak data

## Installation & Setup

### 1. Clone atau download repository ini
Ketik di terminal

> git clone https://github.com/rizky-fadliansyah/PBO_PROJECT_AKHIR.git

> cd PBO_PROJECT_AKHIR

> Atau download ZIP lalu ekstrak ke folder pilihan kamu.

### 2. Pastikan Java sudah terinstall

Ketik di terminal
> java -version

Output yang diharapkan (contoh):
> java version "17.0.x" ...

## How to Run

### Kompilasi semua file Java

Ketik di terminal seperti ini
> javac *.java

### Jalankan aplikasi

Jalankan di terminal 
> java Main


Jendela aplikasi GUI akan terbuka secara otomatis.


## Cara Penggunaan Aplikasi

|        Aksi        |                                     Cara                                                     |
|--------------------|----------------------------------------------------------------------------------------------|
| **Tambah Ulasan**  | Isi semua field (Nama Pembeli, Nama Toko, Produk, Rating, Komentar) → klik **Tambah Ulasan** |
| **Update Ulasan**  | Isi ID Ulasan yang ingin diubah + Komentar baru + Rating baru → klik **Update Ulasan**       |
| **Hapus Ulasan**   | Isi ID Ulasan yang ingin dihapus → klik **Hapus Ulasan**                                     |
| **Refresh Tabel**  | Klik **Refresh Daftar** untuk memuat ulang tampilan tabel                                    |
| **Bersihkan Form** | Klik **Bersihkan Form** untuk mengosongkan semua input field                                 |

> **Catatan:** Rating harus berupa angka antara **1 sampai 5**.

## Contributors

- Rizky Fadliansyah — 254311013/Role1
- Tegar Prasetyo — 254311021/Role2
- Daffa Sebastian H. — 254311019/Role3

thanks to my team and ai
