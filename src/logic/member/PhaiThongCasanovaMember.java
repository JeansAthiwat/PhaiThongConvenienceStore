package logic.member;

import logic.store.Item;
import logic.store.Store;

public class PhaiThongCasanovaMember extends FundamentalMintMember {
    public PhaiThongCasanovaMember(String name, int memberID, int point, int digitalMoney) {
        super(name, memberID, point, digitalMoney);
        this.discountPercent = 0.10;
    }

    @Override
    public String toString() {
        return "(PhaiThongCasanova)" + " " + getMemberID() + "-" + getName() + " DMoney: "
                + getDigitalMoney() + " Pts: " + getPoint();
    }
    @Override
    public String getTierName() {
        return "PhaiThongCasanova";
    }

    @Override
    public void convertPoint() {
        int totalPoint = this.getPoint();
        int totalMoney = totalPoint/50;
        this.setPoint(this.getPoint()-totalMoney*50);
        this.setDigitalMoney(this.getDigitalMoney() + totalMoney);
    }

    public Item giveRandomItemFromStore(){ // randomly gives one item in the store to this guy for free
        Item item = Store.getInstance().takeRandomItemFromStock();
        this.addToPurchaseHistory(item);
        return item;
    }
//TODO
}