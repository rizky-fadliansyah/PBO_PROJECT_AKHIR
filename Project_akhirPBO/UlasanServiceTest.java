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
}
