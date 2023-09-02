package logic.member;

import logic.store.Item;
import utils.ItemUtils;

public class FundamentalMintMember extends BasicMember {
    private int point;
    protected double discountPercent; //?
    private int digitalMoney;

    public FundamentalMintMember(String name, int memberID) {
        super(name, memberID);
        this.setPoint(0);
        this.setDigitalMoney(0);
        this.discountPercent = 0.05;
    }

    public FundamentalMintMember(String name, int memberID, int point, int digitalMoney) {
        super(name, memberID);
        this.setPoint(point);
        this.setDigitalMoney(digitalMoney);
        this.discountPercent = 0.05;
    }

    @Override
    public String toString() {
        return "(FundamentalMint)" + " " + getMemberID() + "-" + getName() + " DMoney: "
                + getDigitalMoney() + " Pts: " + getPoint();
    }

    @Override
    public int totalCartPrice() {
        return ItemUtils.calculateTotalPrice(this.getShoppingCart(), this.getDiscountPercent());
    }

    @Override
    public String getTierName() {
        return "FundamentalMint";
    }

    @Override
    public void checkout() {
        int totalCartPrice = this.totalCartPrice();
        this.setPoint(this.getPoint() + totalCartPrice);
        super.checkout();
    }

    public void convertPoint() {
        int totalPoint = this.getPoint();
        int totalMoney = totalPoint / 100;
        this.setPoint(this.getPoint() - totalMoney * 100);
        this.setDigitalMoney(this.getDigitalMoney() + totalMoney);
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
