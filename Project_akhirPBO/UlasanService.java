import java.util.ArrayList;

public class UlasanService {
    private ArrayList<Ulasan> daftarUlasan = new ArrayList<>();

    public void tambahUlasan(Ulasan ulasan) {
        daftarUlasan.add(ulasan);
        System.out.println("Ulasan ditambahkan");
    }

    public void hapusUlasan(int id) {
        for (Ulasan u : daftarUlasan) {
            if (u.getIdUlasan() == id) {
                daftarUlasan.remove(u);
                System.out.println("Ulasan dihapus");
                return;
            }
        }
        System.out.println("Ulasan tidak ditemukan");
    }

    public void lihatUlasan() {
        if (daftarUlasan.isEmpty()) {
            System.out.println("Belum ada ulasan");
            return;
        }

        for (Ulasan u : daftarUlasan) {
            u.menampilkanUlasan();
            System.out.println("====================");
        }
    }

    public void updateUlasan(int id, String komentarBaru, int ratingBaru) {
        for (Ulasan u : daftarUlasan) {
            if (u.getIdUlasan() == id) {
                u.setKomentar(komentarBaru);
                u.setRating(ratingBaru);
                System.out.println("Ulasan diupdate");
                return;
            }
        }
        System.out.println("Ulasan tidak ditemukan");
    }
}