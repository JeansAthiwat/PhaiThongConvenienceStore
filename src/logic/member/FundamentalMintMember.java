package logic.member;

import logic.store.Item;
import utils.ItemUtils;

import java.util.ArrayList;

public class FundamentalMintMember extends BasicMember {
    //TODO:
    private int point;
    private double discountPercent; //?
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

    //TODO : override string and show digitalMoney left
    @Override
    public String toString() {
        return "(" + this.getClass().getSimpleName() + ")" + " ID:" + this.getMemberID() + " Name:" + this.getName() + "DigitalMoney In account : " + this.getDigitalMoney(); // might error
    }

    @Override
    public int totalCartPrice() {
        return ItemUtils.calculateTotalPrice(this.getShoppingCart(), this.getDiscountPercent());
    }

    @Override
    public int payWithCash(int money) {
        int totalPrice = super.payWithCash(money);
        this.setPoint(this.getPoint() + totalPrice);
        return totalPrice;
    }

    public int payWithCard() {
        int totalPrice = this.totalCartPrice();
        for (Item item : this.getShoppingCart()) {
            this.addToPurchaseHistory(item);
        }
        this.getShoppingCart().clear();
        this.setDigitalMoney(this.getDigitalMoney() - totalPrice);

        return this.get
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
