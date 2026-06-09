public class Ulasan {
    private String komentar;
    private int rating;
    private String produk;
    private int idUlasan;

    public int getIdUlasan() {
        return idUlasan;
    }

    public void setIdUlasan(int idUlasan) {
        this.idUlasan = idUlasan;
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

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public void tampilkanUlasan() {
        System.out.println("Produk   : " + produk);
        System.out.println("Komentar : " + komentar);
        System.out.println("Rating   : " + rating);
    }

    public String tampilkanInfo(){
        return "Ulasan Reguler | Produk: " + this.produk + ", Komentar: " + this.komentar + ", Rating: " + this.rating;
    }
}