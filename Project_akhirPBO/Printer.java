public class Printer {

    // DIPERBAIKI: sekarang menerima UlasanService dan benar-benar mencetak datanya
    public void cetakData(UlasanService service) {
        System.out.println("====== CETAK SEMUA ULASAN ======");
        System.out.println(service.semuaUlasanText());
        System.out.println("================================");
    }
}