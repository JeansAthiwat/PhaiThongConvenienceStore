package store;

import customer.BasicMember;

import java.util.ArrayList;

public class Store {
    //TODO
    private final ArrayList<Item> stocks = new ArrayList<Item>();

    private final ArrayList<BasicMember> members  = new ArrayList<BasicMember>();

    private int storeMoney;

    public static Store instance = null;


    public static Store getInstance() {
        if(instance == null) {
            instance = new Store(100);
        }
        return instance;
    }

    public Store(int storeMoney) {
        //TODO : add each type of customer
        this.getMembers().add(new BasicMember("Jeans",512546));
        this.getMembers().add(new BasicMember("Faro",555445));
        this.getMembers().add(new BasicMember("Peet",123456));
        this.getMembers().add(new BasicMember("Beer",77777));
        this.storeMoney = storeMoney;
    }

    public ArrayList<Item> getStocks() {
        return stocks;
    }

    public ArrayList<BasicMember> getMembers() {
        return members;
    }

    public int getStoreMoney() {
        return storeMoney;
    }

    public void setStoreMoney(int storeMoney) {
        if(storeMoney < 0){
            storeMoney = 0;
        }
        this.storeMoney = storeMoney;
    }

}
