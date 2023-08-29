package logic.member;

import logic.store.Item;
import utils.ItemUtils;

import java.util.ArrayList;

public class FundamentalMintMember extends BasicMember {
    //TODO:
    private int point;
    private final double discountPercent = 0.05; //?
    private int digitalMoney;

    public FundamentalMintMember(String name, int memberID) {
        super(name, memberID);
        this.setPoint(0);
        this.setDigitalMoney(0);
    }

    public FundamentalMintMember(String name, int memberID, int point, int digitalMoney) {
        super(name, memberID);
        this.setPoint(point);
        this.setDigitalMoney(digitalMoney);
    }
    //TODO : override string and show digitalMoney left
    @Override
    public int payWithCash(int money) {
        // TODO:
        return ItemUtils.calculateTotalPrice(this.getShoppingCart(), discountPercent);
    }

    public int payWithCard(ArrayList<Item> shoppingCart){
        return ItemUtils.calculateTotalPrice(shoppingCart, discountPercent);
    }

    @Override
    public boolean hasEnoughMoney(int money) {
        int totalPrice = ItemUtils.calculateTotalPrice(this.getShoppingCart(),this.getDiscountPercent());
        boolean hasEnoughMoney = false;
        if (money >= totalPrice) {
            hasEnoughMoney = true;
        }
        return hasEnoughMoney;
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
