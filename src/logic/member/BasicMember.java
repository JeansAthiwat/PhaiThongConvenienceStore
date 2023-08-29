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

    public int payWithCash(int money) {
        int totalPrice = this.totalCartPrice();
        for (Item item : this.getShoppingCart()) {
            this.addToPurchaseHistory(item);
        }
        this.getShoppingCart().clear();

        return totalPrice;
    }

    public void addToPurchaseHistory(Item item) {
        for (Item historyItem : this.getPurchaseHistory()) {
            if (historyItem.equals(item)) {
                historyItem.setAmount(historyItem.getAmount() + item.getAmount());
            }
            else{
                this.getPurchaseHistory().add(item);
            }
        }
    }


    public boolean hasEnoughMoney(int money) { // TODO broken
        int totalPrice = this.totalCartPrice();
        boolean hasEnoughMoney = false;
        if (money >= totalPrice) {
            hasEnoughMoney = true;
        }
        return hasEnoughMoney;
    }

    public int totalCartPrice(){
        return ItemUtils.calculateTotalPrice(this.getShoppingCart());
    }
    public ArrayList<Item> getPurchaseHistory() {
        return purchaseHistory;
    }

    public ArrayList<Item> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public String toString() {
        return "(" + this.getClass().getSimpleName() + ")" + " ID:" + this.getMemberID() + " Name:" + this.getName(); // might error
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()) {
            name = "UnknownCustomer";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicMember that = (BasicMember) o;
        return getMemberID() == that.getMemberID();
    }

}
