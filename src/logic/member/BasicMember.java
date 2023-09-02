package logic.member;

import logic.store.Item;
import utils.ItemUtils;

import java.util.ArrayList;

public class BasicMember {
    private final ArrayList<Item> purchaseHistory = new ArrayList<Item>();
    private final ArrayList<Item> shoppingCart = new ArrayList<Item>();
    private String name;
    private int memberID;

    public BasicMember(String name, int memberID) {
        this.setName(name);
        this.setMemberID(memberID);
    }

    public int totalCartPrice() {
        return ItemUtils.calculateTotalPrice(this.getShoppingCart());
    }
    public void addToPurchaseHistory(Item item) {
        if (!getPurchaseHistory().isEmpty()) {
            for (Item historyItem : this.getPurchaseHistory()) {
                if (historyItem.equals(item)) {
                    historyItem.setAmount(historyItem.getAmount() + item.getAmount());
                    return;
                }
            }
        }
        this.getPurchaseHistory().add(item);
    }

    public void checkout() {
        for (Item item : this.getShoppingCart()) {
            this.addToPurchaseHistory(item);
        }
        this.getShoppingCart().clear();
    }




    @Override
    public String toString() {
        return "(Basic)" + " " + getMemberID() + "-" + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicMember)) return false;
        BasicMember that = (BasicMember) o;
        return getMemberID() == that.getMemberID();
    }

    public ArrayList<Item> getPurchaseHistory() {
        return purchaseHistory;
    }

    public ArrayList<Item> getShoppingCart() {
        return shoppingCart;
    }


    public String getTierName() {
        return "Basic";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()) {
            name = "UnknownMember";
        }
        this.name = name;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        if (memberID < 0) {
            memberID = 0;
        }
        this.memberID = memberID;
    }

}
