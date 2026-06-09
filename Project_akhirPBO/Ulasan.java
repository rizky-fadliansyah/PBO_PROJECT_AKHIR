public class Ulasan {

    private int idUlasan;
    private String produk;
    private String komentar;
    private int rating;

    private String namaPembeli;
    private String statusPembeli;

    private String namaToko;
    private String statusPenjual;

    public Ulasan() {
    }

    public Ulasan(
            int idUlasan,
            String produk,
            String komentar,
            int rating,
            String namaPembeli,
            String statusPembeli,
            String namaToko,
            String statusPenjual
    ) {

        this.idUlasan = idUlasan;
        this.produk = produk;
        this.komentar = komentar;
        this.rating = rating;

        this.namaPembeli = namaPembeli;
        this.statusPembeli = statusPembeli;

        this.namaToko = namaToko;
        this.statusPenjual = statusPenjual;
    }

    public int getIdUlasan() {
        return idUlasan;
    }

    public void setIdUlasan(int idUlasan) {
        this.idUlasan = idUlasan;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getStatusPembeli() {
        return statusPembeli;
    }

    public void setStatusPembeli(String statusPembeli) {
        this.statusPembeli = statusPembeli;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getStatusPenjual() {
        return statusPenjual;
    }

    public void setStatusPenjual(String statusPenjual) {
        this.statusPenjual = statusPenjual;
    }

    public void tampilkanUlasan() {

        System.out.println(
                "ID Ulasan : "
                + idUlasan
        );

        System.out.println(
                "Produk : "
                + produk
        );

        System.out.println(
                "Pembeli : "
                + namaPembeli
        );

        System.out.println(
                "Status Pembeli : "
                + statusPembeli
        );

        System.out.println(
                "Toko : "
                + namaToko
        );

        System.out.println(
                "Status Penjual : "
                + statusPenjual
        );

        System.out.println(
                "Komentar : "
                + komentar
        );

        System.out.println(
                "Rating : "
                + rating
        );
    }
}