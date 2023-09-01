package logic.store;

import logic.member.BasicMember;
import logic.member.FundamentalMintMember;
import logic.member.PhaiThongCasanovaMember;
import logic.member.StarvingStudentMember;

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
        this.initializeStore();
    }

    public void removeOutOfStockItems(){
        this.getStock().removeIf(item -> item.getAmount() == 0);
    }

    public Item takeRandomItemFromStock() {
        int randomIndex = (int) Math.floor(Math.random() * this.getStock().size());
        Item stockItem = this.getStock().get(randomIndex);
        Item item = new Item(stockItem, 1);
        stockItem.setAmount(stockItem.getAmount() - 1);
        removeOutOfStockItems();
        return item;
    }

    public Item addItemToShoppingCart(BasicMember member, int itemStockIndex, int amount) {
        Item stockItem = this.getStock().get(itemStockIndex);
        stockItem.setAmount(stockItem.getAmount() - amount);
        Item addingItem = new Item(stockItem, amount);
        member.getShoppingCart().add(addingItem);
        removeOutOfStockItems();
        return addingItem;
    }

    public boolean isInStock(Item otherItem) {
        for (Item item : this.getStock()) {
            if (item.equals(otherItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMember(BasicMember otherMember) {
        for (BasicMember member : this.getMembers()) {
            if (member.getMemberID() == otherMember.getMemberID()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getStock() {
        return stock;
    }

    public void addItemToStock(Item newItem) {
        if (isInStock(newItem)) {
            for (Item item : this.getStock()) {
                if (item.equals(newItem)) {
                    item.setAmount((item.getAmount() + newItem.getAmount()));
                    break;
                }
            }
        } else {
            this.getStock().add(newItem);
        }
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

    private void initializeStore() {
        //TODO : add each type of customer
        this.getMembers().add(new BasicMember("NormieJeans", 1));
        this.getMembers().get(0).getShoppingCart().add(new Item("GT 560", 50, 1));
        this.getMembers().add(new FundamentalMintMember("CHADJeans", 2, 0, 1000));
        this.getMembers().get(1).getShoppingCart().add(new Item("RTX4090", 5, 10));

        this.getMembers().add(new StarvingStudentMember("Faro", 555445,0,5));
        this.getMembers().add(new PhaiThongCasanovaMember("Peet", 123456,5000,9999999));
        this.getMembers().add(new PhaiThongCasanovaMember("Beer", 777775000,0,9999999));

        this.getStock().add(new Item("Jelly Beans", 10, 10000));
        this.getStock().add(new Item("Painkiller", 100, 500));
        this.getStock().add(new Item("Orange", 150, 10));
        this.getStock().add(new Item("Coconut Milk", 300, 30));
        this.setStoreMoney(storeMoney);
    }

}
