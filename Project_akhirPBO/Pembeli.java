public class Pembeli {
    private int idPembeli;
    private String nama;

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
        System.out.println("Pembeli memberi ulasan");
    }
}