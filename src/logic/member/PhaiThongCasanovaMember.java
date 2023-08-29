package logic.member;

public class PhaiThongCasanovaMember extends FundamentalMintMember {
    public PhaiThongCasanovaMember(String name, int memberID, int point, int digitalMoney) {
        super(name, memberID, point, digitalMoney);
        this.discountPercent = 0.10;
    }

    public String randomReward(){ // randomly gives one item in the store to this guy for free;
        String out = "";
        return out;
    }
//TODO
}