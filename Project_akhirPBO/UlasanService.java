import java.util.ArrayList;
import java.util.List;

public class UlasanService {
    private ArrayList<Ulasan> daftarUlasan = new ArrayList<>();

    public void tambahUlasan(Ulasan ulasan) throws InputTidakValidException {
        if (ulasan == null) {
            throw new InputTidakValidException("Ulasan tidak boleh kosong.");
        }
        if (ulasan.getProduk() == null || ulasan.getProduk().trim().isEmpty()) {
            throw new InputTidakValidException("Produk harus diisi.");
        }
        if (ulasan.getKomentar() == null || ulasan.getKomentar().trim().isEmpty()) {
            throw new InputTidakValidException("Komentar harus diisi.");
        }
        if (ulasan.getRating() < 1 || ulasan.getRating() > 5) {
            throw new InputTidakValidException("Rating harus antara 1 sampai 5.");
        }
        daftarUlasan.add(ulasan);
        System.out.println("Ulasan ditambahkan");
    }

    public void hapusUlasan(int id) throws InputTidakValidException {
        for (Ulasan u : daftarUlasan) {
            if (u.getIdUlasan() == id) {
                daftarUlasan.remove(u);
                System.out.println("Ulasan dihapus");
                return;
            }
        }
        throw new InputTidakValidException("Ulasan dengan ID " + id + " tidak ditemukan.");
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

    public void updateUlasan(int id, String komentarBaru, int ratingBaru) throws InputTidakValidException {
        for (Ulasan u : daftarUlasan) {
            if (u.getIdUlasan() == id) {
                u.setKomentar(komentarBaru);
                u.setRating(ratingBaru);
                System.out.println("Ulasan diupdate");
                return;
            }
        }
        throw new InputTidakValidException("Ulasan dengan ID " + id + " tidak ditemukan.");
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