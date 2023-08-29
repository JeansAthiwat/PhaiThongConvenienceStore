package logic.app;

import logic.member.BasicMember;
import logic.member.FundamentalMintMember;
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
        String out = "   ---===Item(s) in stock===--- \n";
        for (int i = 0; i < stock.size(); i++) {
            Item currentItem = stock.get(i);
            out += "(" + i + ") " + currentItem + " (" + currentItem.getPrice() + " Baht/item)" + "\n";
            //out += "(" + i + ") x" + currentItem.getAmount() + "  :" + currentItem.getName() + " (" + currentItem.getPrice() + " Baht/item)" + "\n";
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

    public String addItemToShoppingCartFlow(BasicMember member, int itemStockIndex, int amount) {
        ArrayList<Item> stock = Store.getInstance().getStock();
        if (stock.get(itemStockIndex).getAmount() < amount) {
            return "Cannot add more than what the stock has.";
        } else {
            return "Added to Cart :" + Store.getInstance().addItemToShoppingCart(member, itemStockIndex, amount);
        }

    }

    public String removeItemFromShoppingCartFlow(BasicMember member, int itemCartIndex) {
        Item item = member.getShoppingCart().get(itemCartIndex);
        member.getShoppingCart().remove(itemCartIndex);
        Store.getInstance().addItemToStock(item);
        return "Removed from Cart :" + item;
    }

    public String checkOutWithCashFlow(BasicMember member, int givenMoney) { //todo STUDENT:
        int totalCartPrice = member.totalCartPrice();
        if(totalCartPrice <= givenMoney){
            member.checkout();
            int changeAmount = givenMoney - totalCartPrice;
            return "Paid for all item in Cart- Change to customer:" + changeAmount + "Baht";
        }
        else{
            return "Not enough Money";
        }
    }

    public String checkoutWithDigitalMoneyFlow(FundamentalMintMember member) { //todo STUDENT:
        int totalCartPrice = member.totalCartPrice();
        if(totalCartPrice <= member.getDigitalMoney()){
            member.setDigitalMoney(member.getDigitalMoney()-totalCartPrice);
            member.checkout(); // remove item from member's Cart and put to HistoryArray
            return "Paid for all item in Cart. DigitalMoney Left :" + member.getDigitalMoney() + "Baht";
        }else {
            return "Not enough DigitalMoney in account";
        }
    }
}
