package logic.members;

import utils.ItemUtils;

public class FundamentalMintMember extends BasicMember { //todo STUDENT: create this class from scratch
    private int point;
    protected double discountPercent;
    private int digitalMoney;

    public FundamentalMintMember(String name, int memberID, int point, int digitalMoney) {
        super(name, memberID);
        this.setPoint(point);
        this.setDigitalMoney(digitalMoney);
        this.discountPercent = 0.05;
    }

    public void convertPoint() {
        int totalPoint = this.getPoint();
        int totalMoney = totalPoint / 100;
        this.setPoint(this.getPoint() - totalMoney * 100);
        this.setDigitalMoney(this.getDigitalMoney() + totalMoney);
    }

    @Override
    public String toString() {
        return "(" + getTierName() + ")" + " " + getMemberID() + "-" + getName() + " DMoney: "
                + getDigitalMoney() + " Pts: " + getPoint();
    }

    @Override
    public String getTierName() {
        return "FundamentalMint";
    }

    @Override
    public int totalCartPrice() {
        return ItemUtils.calculateTotalPrice(this.getShoppingCart(), this.getDiscountPercent());
    }

    @Override
    public void checkout() {
        int totalCartPrice = this.totalCartPrice();
        this.setPoint(this.getPoint() + totalCartPrice);
        super.checkout();
    }


    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        if (point < 0) {
            point = 0;
        }
        this.point = point;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public int getDigitalMoney() {
        return digitalMoney;
    }

    public void setDigitalMoney(int digitalMoney) {
        if (digitalMoney < 0) {
            digitalMoney = 0;
        }
        this.digitalMoney = digitalMoney;
    }
}
