public abstract class Penjual {
    private int idPenjual;
    private String namaToko;

    public Penjual() {
    }
    public Penjual(int idPenjual, String namaToko) {
        this.idPenjual = idPenjual;
        this.namaToko = namaToko;
    }   

    public int getIdPenjual() {
        return idPenjual;
    }

    public void setIdPenjual(int idPenjual) {
        this.idPenjual = idPenjual;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;   
    }

    public abstract void tampilkanInfo();
}