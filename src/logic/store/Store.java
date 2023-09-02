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
            instance = new Store(8000);
        }
        return instance;
    }

    public Store(int storeMoney) {
        this.initializeStore(storeMoney);
    }

    public BasicMember getMemberByID(int memberID) {
        ArrayList<BasicMember> members = Store.getInstance().getMembers();
        for (BasicMember member : members) {
            if (member.getMemberID() == memberID) {
                return member;
            }
        }
        return null;
    }

    public void removeOutOfStockItems(){
        this.getStock().removeIf(item -> item.getAmount() == 0);
    }

    public Item takeRandomItemFromStock() {
        if(this.getStock().isEmpty()){
            return null;
        }
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
            if (member.equals(otherMember)) {
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

    private void initializeStore(int storeMoney) {
        this.getStock().add(new Item("JellyBeans", 10, 10000));
        this.getStock().add(new Item("Painkiller", 100, 500));
        this.getStock().add(new Item("Orange", 150, 10));
        this.getStock().add(new Item("CoconutMilk", 300, 30));
        this.setStoreMoney(storeMoney);

        this.getMembers().add(new BasicMember("Barbie", 11111));
        this.getMembers().get(0).getShoppingCart().add(new Item("LemonIceCream", 50, 1));

        this.getMembers().add(new FundamentalMintMember("Oppenheimer", 22222, 0, 1000));
        this.getMembers().get(1).getShoppingCart().add(new Item("LeNukeIceCream", 1, 25000));

        this.getMembers().add(new PhaiThongCasanovaMember("Ken", 33333,5000,9999999));
        this.getMembers().get(1).getShoppingCart().add(new Item("HorseIceCream", 9999, 30000));

        this.getMembers().add(new StarvingStudentMember("Alan", 44444,0,5));
    }

}
