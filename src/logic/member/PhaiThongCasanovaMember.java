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
        return "(PhaiThongCasanova)" + " " + this.getMemberID() + "-" + this.getName() + " DigitalMoney: " + this.getDigitalMoney() + " Points: " + getPoint();
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
        super.convertPoint();
    }

    public Item giveRandomItemFromStore(){ // randomly gives one item in the store to this guy for free
        Item item = Store.getInstance().giveRandomItemFromStock();
        this.addToPurchaseHistory(item);
        return item;
    }
//TODO
}