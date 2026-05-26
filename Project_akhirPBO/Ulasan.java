public class Ulasan {
    private String komentar;
    private int rating;
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

    public void tampilkanUlasan() {
        System.out.println("Komentar : " + komentar);
        System.out.println("Rating : " + rating);
    }
}