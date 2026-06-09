public class Pembeli {

    private int idPembeli;
    private String nama;

    public Pembeli() {
    }
    
    public Pembeli(int idPembeli, String nama) {
        this.idPembeli = idPembeli;
        this.nama = nama;
    }

    public int getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(int idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void beriUlasan() {
        System.out.println(
                nama + " memberikan ulasan."
        );
    }
}