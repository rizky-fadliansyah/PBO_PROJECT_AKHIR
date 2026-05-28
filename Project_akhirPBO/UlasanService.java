import java.util.ArrayList;
import java.util.List;

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
            u.tampilkanUlasan();
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

    public String semuaUlasanText() {
        if (daftarUlasan.isEmpty()) {
            return "Belum ada ulasan.";
        }

        StringBuilder builder = new StringBuilder();
        for (Ulasan u : daftarUlasan) {
            builder.append("ID Ulasan   : ").append(u.getIdUlasan()).append("\n");
            builder.append("Produk      : ").append(u.getProduk()).append("\n");
            builder.append("Komentar    : ").append(u.getKomentar()).append("\n");
            builder.append("Rating      : ").append(u.getRating()).append("\n");
            builder.append("---------------------------\n");
        }
        return builder.toString();
    }

    public List<Ulasan> getDaftarUlasan() {
        return new ArrayList<>(daftarUlasan);
    }
}