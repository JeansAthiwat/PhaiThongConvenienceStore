package logic.app;

import logic.member.BasicMember;
import logic.store.Item;
import logic.store.Store;

import java.util.ArrayList;

public class AppController {

    private static AppController instance = null;

    public AppController() {

    }

    public AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
    }

    public Item addNewItemToStockFlow(String name, int price, int amount) {
        //TODO
        Item newItem = new Item(name, price, amount);
        Store.getInstance().addItemToStock(newItem);
        return newItem;
    }

    public String showStockFlow() {
        ArrayList<Item> stock = Store.getInstance().getStock();
        String out = "";
        for (int i = 0; i < stock.size(); i++) {
            Item currentItem = stock.get(i);
            out += "(" + i + ") x" + currentItem.getAmount() + "  :" + currentItem.getName() + " (" + currentItem.getPrice() + " Baht/item)" + "\n";
        }
        return out;
    }

    public void memberShoppingFlow() {
        //TODO
    }

    public boolean memberExist(int memberID) {
        boolean isExist = false;
        ArrayList<BasicMember> members = Store.getInstance().getMembers();
        for (BasicMember member : members) {
            if (member.getMemberID() == memberID) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    public void signUpMemberFlow() {
        //TODO
    }

    public void manageMemberFlow() {
        //TODO
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
}
