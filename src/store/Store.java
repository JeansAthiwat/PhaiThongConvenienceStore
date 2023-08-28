package store;

import customer.BasicMember;

import java.util.ArrayList;

public class Store {
    //TODO
    private final ArrayList<Item> stock = new ArrayList<Item>();

    private final ArrayList<BasicMember> members = new ArrayList<BasicMember>();

    private int storeMoney;

    public static Store instance = null;


    public static Store getInstance() {
        if (instance == null) {
            instance = new Store(100);
        }
        return instance;
    }

    public Store(int storeMoney) {
        //TODO : add each type of customer
        this.getMembers().add(new BasicMember("Jeans", 512546));
        this.getMembers().add(new BasicMember("Faro", 555445));
        this.getMembers().add(new BasicMember("Peet", 123456));
        this.getMembers().add(new BasicMember("Beer", 77777));

        this.getStock().add(new Item("Jelly Beans",100, 50));
        this.getStock().add(new Item("Painkiller",200, 5));
        this.getStock().add(new Item("Orange",150, 10));
        this.getStock().add(new Item("Coconut Milk",300, 30));
        this.setStoreMoney(storeMoney);
    }

    public void checkOut(BasicMember member) {
        //TODO:

    }

    public void addItemToShoppingCart(BasicMember member, Item item) {
        this.getStock().remove(item);
        member.getShoppingCart().add(item);
    }

    public boolean isInStock(Item OtherItem) {
        for (Item item : this.getStock()) {
            if (OtherItem == item) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getStock() {
        return stock;
    }

    public ArrayList<BasicMember> getMembers() {
        return members;
    }

    public int getStoreMoney() {
        return storeMoney;
    }

    public void setStoreMoney(int storeMoney) {
        if (storeMoney < 0) {
            storeMoney = 0;
        }
        this.storeMoney = storeMoney;
    }

}
