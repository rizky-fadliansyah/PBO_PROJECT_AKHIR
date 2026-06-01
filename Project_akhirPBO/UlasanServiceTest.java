import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UlasanServiceTest {

    private UlasanService ulasanService;

    @BeforeEach
    public void setUp() {
        // Method ini akan dijalankan sebelum setiap test
        ulasanService = new UlasanService();
    }

    // tambah Ulasan
    @Test
    public void testTambahUlasan() {
        Ulasan ulasan = new Ulasan();
        ulasan.setIdUlasan(1);
        ulasan.setProduk("Laptop");
        ulasan.setKomentar("Bagus sekali");
        ulasan.setRating(5);

        ulasanService.tambahUlasan(ulasan);

        List<Ulasan> daftar = ulasanService.getDaftarUlasan();
        assertEquals(1, daftar.size(), "Ukuran daftar ulasan harus 1");
        assertEquals("Laptop", daftar.get(0).getProduk(), "Produk harus 'Laptop'");
    }

    // hapus ulasan
    @Test
    public void testHapusUlasan() {
        Ulasan ulasan = new Ulasan();
        ulasan.setIdUlasan(1);
        ulasanService.tambahUlasan(ulasan);

        // Pastikan ukuran daftar adalah 1
        assertEquals(1, ulasanService.getDaftarUlasan().size());

        // Hapus ulasan
        ulasanService.hapusUlasan(1);

        List<Ulasan> daftar = ulasanService.getDaftarUlasan();
        assertTrue(daftar.isEmpty(), "Daftar ulasan harus kosong setelah dihapus");
    }

    // update ulasan
    @Test
    public void testUpdateUlasan() {
        Ulasan ulasan = new Ulasan();
        ulasan.setIdUlasan(1);
        ulasan.setKomentar("Lama");
        ulasan.setRating(3);
        ulasanService.tambahUlasan(ulasan);

        ulasanService.updateUlasan(1, "Baru", 5);

        List<Ulasan> daftar = ulasanService.getDaftarUlasan();
        assertEquals("Baru", daftar.get(0).getKomentar(), "Komentar harus terupdate menjadi 'Baru'");
        assertEquals(5, daftar.get(0).getRating(), "Rating harus terupdate menjadi 5");
    }

    // read
    @Test
    public void testSemuaUlasanTextKosong() {
        String hasil = ulasanService.semuaUlasanText();
        assertEquals("Belum ada ulasan.", hasil, "Pesan harus mengindikasikan belum ada ulasan");
    }

    @Test
    public void testHapusUlasanTidakDitemukan() {
        Ulasan ulasan = new Ulasan();
        ulasan.setIdUlasan(1);
        ulasanService.tambahUlasan(ulasan);
        ulasanService.hapusUlasan(99);
        assertEquals(1, ulasanService.getDaftarUlasan().size(), "Ukuran harus tetap 1 karena ID 99 tidak ada");
    }

    @Test
    public void testUpdateUlasanTidakDitemukan() {
        Ulasan ulasan = new Ulasan();
        ulasan.setIdUlasan(1);
        ulasan.setKomentar("Bagus");
        ulasan.setRating(5);
        ulasanService.tambahUlasan(ulasan);

        ulasanService.updateUlasan(99, "Jelek", 1);

        List<Ulasan> daftar = ulasanService.getDaftarUlasan();
        assertEquals("Bagus", daftar.get(0).getKomentar(), "Komentar tidak boleh berubah");
        assertEquals(5, daftar.get(0).getRating(), "Rating tidak boleh berubah");
    }
}
