package logic.member;

import logic.store.Item;
import logic.store.Store;

public class PhaiThongCasanovaMember extends FundamentalMintMember { //todo STUDENT: create this class from scratch
    public PhaiThongCasanovaMember(String name, int memberID, int point, int digitalMoney) {
        super(name, memberID, point, digitalMoney);
        this.discountPercent = 0.10;
    }

    @Override
    public void convertPoint() {
        int totalPoint = this.getPoint();
        int totalMoney = totalPoint / 50;
        this.setPoint(this.getPoint() - totalMoney * 50);
        this.setDigitalMoney(this.getDigitalMoney() + totalMoney);
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


    public Item giveRandomItemFromStore() { // randomly gives one item in the store to this guy for free
        if(this.getPoint() < 1000){
            return null;
        }
        Item item = Store.getInstance().takeRandomItemFromStock();
        if(item == null){
            return null;
        }
        this.setPoint(this.getPoint()-1000);
        this.addToPurchaseHistory(item);
        return item;
    }
}