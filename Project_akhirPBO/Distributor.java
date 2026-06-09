public class Distributor extends Penjual {
    private String wilayah;

    public Distributor() {
    }

    public Distributor(int idPenjual, String namaToko, String wilayah) {
        setIdPenjual(idPenjual);
        setNamaToko(namaToko);
        this.wilayah = wilayah;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("Distributor: " + getNamaToko());
        System.out.println("Wilayah distribusi: " + wilayah);
    }

    @Override
    public void gunakanSkill() {
        System.out.println("Distributor mengoptimalkan logistik dan pengiriman.");
    }
}
