public class UlasanPrioritas extends Ulasan {
    //enkapkulasi
    private boolean isMemberVip;
    
    //constructor
    public UlasanPrioritas() {
        super();
        this.isMemberVip = false;
    }

    //getter
    public boolean isMemberVip() {
        return isMemberVip;
    }

    //setter
    public void setMemberVip(boolean memberVip) {
        isMemberVip = memberVip;
    }

    //polimorfisme
    @Override
    public String tampilkanInfo() {
        String label = this.isMemberVip ? "VIP" : "Reguler";
        return "Ulasan Prioritas | Produk: " + this.getProduk() + ", Komentar: " + this.getKomentar() + ", Rating: " + this.getRating() + ", VIP: " + (isMemberVip ? "Ya" : "Tidak");
    }
}
