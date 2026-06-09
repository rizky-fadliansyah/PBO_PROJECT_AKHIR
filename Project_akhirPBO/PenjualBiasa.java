public class PenjualBiasa extends Penjual {

    public PenjualBiasa() {
    }

    public PenjualBiasa(
            int idPenjual,
            String namaToko
    ) {
        super(idPenjual, namaToko);
    }

    @Override
    public void tampilkanInfo() {

        System.out.println(
                "=== PENJUAL BIASA ==="
        );

        System.out.println(
                "ID : "
                + getIdPenjual()
        );

        System.out.println(
                "Nama Toko : "
                + getNamaToko()
        );
    }
}
