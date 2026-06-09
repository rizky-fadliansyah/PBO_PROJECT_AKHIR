public class TokoGrosir extends Penjual {

    private String StatusToko;

    public TokoGrosir() {
    }

    public TokoGrosir(
            int idPenjual,
            String namaGrosir,
            String StatusToko
    ) {
        setIdPenjual(idPenjual);
        setNamaToko(namaGrosir);
        this.StatusToko = StatusToko;
    }

    public String getStatusToko() {
        return StatusToko;
    }

    public void setStatusToko(
            String StatusToko
    ) {
        this.StatusToko = StatusToko;
    }

    @Override
    public void tampilkanInfo() {

        System.out.println(
                "=== PENJUAL GROSIR ==="
        );

        System.out.println(
                "ID : "
                + getIdPenjual()
        );

        System.out.println(
                "Nama Grosir : "
                + getNamaToko()
        );

        System.out.println(
                "Status Toko : "
                + getStatusToko()
        );
    }

}