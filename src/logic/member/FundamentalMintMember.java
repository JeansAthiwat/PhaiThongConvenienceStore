package logic.member;

public class FundamentalMintMember extends BasicMember{
    //TODO:
    private int point;
    private final double DISCOUNT = 0.05;
    private int digitalMoney;

    public FundamentalMintMember(String name,int memberID){
        super(name,memberID);
        this.point = 0;
        this.digitalMoney = 0;
    }

}
