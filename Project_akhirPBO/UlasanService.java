import java.util.ArrayList;
import java.util.List;

public class UlasanService {

    private ArrayList<Ulasan> daftarUlasan =
            new ArrayList<>();

    private ArrayList<Pembeli> daftarPembeli =
            new ArrayList<>();

    private ArrayList<Penjual> daftarPenjual =
            new ArrayList<>();

    public void tambahUlasan(
            Ulasan ulasan
    ) {

        daftarUlasan.add(ulasan);

        System.out.println(
                "Ulasan berhasil ditambahkan."
        );

    }

    public void lihatUlasan() {

        if (daftarUlasan.isEmpty()) {

            System.out.println(
                    "Belum ada ulasan."
            );

            return;

        }

        for (Ulasan u : daftarUlasan) {

            u.tampilkanUlasan();

            System.out.println(
                    "================================"
            );

        }

    }

    public void hapusUlasan(
            int id
    ) {

        for (int i = 0;
             i < daftarUlasan.size();
             i++) {

            if (daftarUlasan
                    .get(i)
                    .getIdUlasan() == id) {

                daftarUlasan.remove(i);

                System.out.println(
                        "Ulasan berhasil dihapus."
                );

                return;

            }

        }

        System.out.println(
                "Ulasan tidak ditemukan."
        );

    }

    public void updateUlasan(
            int id,
            String komentarBaru,
            int ratingBaru
    ) {

        for (Ulasan u :
                daftarUlasan) {

            if (u.getIdUlasan()
                    == id) {

                u.setKomentar(
                        komentarBaru
                );

                u.setRating(
                        ratingBaru
                );

                System.out.println(
                        "Ulasan berhasil diupdate."
                );

                return;

            }

        }

        System.out.println(
                "Ulasan tidak ditemukan."
        );

    }

    public boolean isUlasanAda(
            int id
    ) {

        for (Ulasan u :
                daftarUlasan) {

            if (u.getIdUlasan()
                    == id) {

                return true;

            }

        }

        return false;

    }

    public void tambahPembeli(
            Pembeli pembeli
    ) {

        daftarPembeli.add(
                pembeli
        );

    }

    public List<Pembeli> getDaftarPembeli() {

        return daftarPembeli;

    }
    public void tambahPenjual(
            Penjual penjual
    ) {

        daftarPenjual.add(
                penjual
        );

    }

    public List<Penjual> getDaftarPenjual() {

        return daftarPenjual;

    }

    public ArrayList<Pembeli>
    getMemberVIP() {

        ArrayList<Pembeli> vipList =
                new ArrayList<>();

        for (Pembeli p :
                daftarPembeli) {

            if (p instanceof MemberVIP) {

                vipList.add(p);

            }

        }

        return vipList;

    }

    public String semuaUlasanText() {

        if (daftarUlasan.isEmpty()) {

            return "Belum ada ulasan.";

        }

        StringBuilder builder =
                new StringBuilder();

        for (Ulasan u :
                daftarUlasan) {

            builder.append(
                    "ID Ulasan : "
            ).append(
                    u.getIdUlasan()
            ).append(
                    "\n"
            );

            builder.append(
                    "Produk : "
            ).append(
                    u.getProduk()
            ).append(
                    "\n"
            );

            builder.append(
                    "Pembeli : "
            ).append(
                    u.getNamaPembeli()
            ).append(
                    "\n"
            );

            builder.append(
                    "Status Pembeli : "
            ).append(
                    u.getStatusPembeli()
            ).append(
                    "\n"
            );

            builder.append(
                    "Toko : "
            ).append(
                    u.getNamaToko()
            ).append(
                    "\n"
            );

            builder.append(
                    "Status Penjual : "
            ).append(
                    u.getStatusPenjual()
            ).append(
                    "\n"
            );

            builder.append(
                    "Komentar : "
            ).append(
                    u.getKomentar()
            ).append(
                    "\n"
            );

            builder.append(
                    "Rating : "
            ).append(
                    u.getRating()
            ).append(
                    "\n"
            );

            builder.append(
                    "----------------------------\n"
            );

        }

        return builder.toString();

    }

    public List<Ulasan>
    getDaftarUlasan() {

        return new ArrayList<>(
                daftarUlasan
        );

    }

}