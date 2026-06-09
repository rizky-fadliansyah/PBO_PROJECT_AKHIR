public class MemberVIP extends Pembeli {
    private int poinReward;

    public MemberVIP() {
    }
    public MemberVIP(int idPembeli, String nama, int poinReward) {
        super(idPembeli, nama);
        this.poinReward = poinReward;
    }
    public int getPoinReward() {
        return poinReward;
    }
    public void setPoinReward(int poinReward) {
        this.poinReward = poinReward;
    }
    
    @Override
    public void beriUlasan() {
        System.out.println("Member VIP " + getNama() + " memberikan ulasan." 
        + poinReward + " poin reward diperoleh.");
    }
    
}
