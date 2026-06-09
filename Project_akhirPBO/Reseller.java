public class Reseller extends Penjual {

    private String Statustoko;

    public Reseller() {
    }

    public Reseller(
            int idPenjual,
            String namaToko,
            String Statustoko
    ) {
        super(idPenjual, namaToko);
        this.Statustoko =
                Statustoko;
    }

    public String getStatustoko() {
        return Statustoko;
    }

    public void setStatustoko(
            String Statustoko
    ) {
        this.Statustoko =
                Statustoko;
    }

    @Override
    public void tampilkanInfo() {

        System.out.println(
                "=== PENJUAL RESELLER ==="
        );

        System.out.println(
                "ID Penjual : "
                + getIdPenjual()
        );

        System.out.println(
                "Nama Toko : "
                + getNamaToko()
        );

        System.out.println(
                "Status Toko : "
                + getStatustoko()
        );

    }
}