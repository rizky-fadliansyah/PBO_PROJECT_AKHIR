public abstract class Penjual {
    private int idPenjual;
    private String namaToko;

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

    public void tampilkanDetail() {
        System.out.println("Penjual: " + namaToko);
    }

    public void gunakanSkill() {
        System.out.println("Penjual menggunakan kemampuan dasar.");
    }
}