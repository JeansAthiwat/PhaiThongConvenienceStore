package logic.app;

import logic.member.BasicMember;
import logic.member.FundamentalMintMember;
import logic.member.PhaiThongCasanovaMember;
import logic.member.StarvingStudentMember;
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

    public boolean signUpMemberFlow(int member_type, String memberName, int memberID, int memberDigitalMoney) {
        //TODO
        BasicMember member = null;
        switch (member_type) {
            case 0 -> member = new BasicMember(memberName, memberID);
            case 1 -> member = new FundamentalMintMember(memberName, memberID, 0, memberDigitalMoney);
            case 2 -> member = new PhaiThongCasanovaMember(memberName, memberID, 0, memberDigitalMoney);
            case 3 -> member = new StarvingStudentMember(memberName, memberID, 0, memberDigitalMoney);
        }
        Store store = Store.getInstance();
        if (member == null || store.isMember(member)) {
            return false;
        } else {
            store.getMembers().add(member);
            return true;
        }
    }

    public void manageMemberFlow(int choice_member) {
        //TODO
        BasicMember member = Store.getInstance().getMembers().get(choice_member);
        if(member.getTierName().equals("Basic")){

        }
    }

    public String showMemberList(){
        String out = "";
        ArrayList<BasicMember> members = Store.getInstance().getMembers();
        for(int i = 0; i< members.size();i++){
            out += "(" + i + ")" + members.get(i).toString() + "\n";
        }
        return out;
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
        if (totalCartPrice <= givenMoney) {
            member.checkout();
            int changeAmount = givenMoney - totalCartPrice;
            return "Paid for all item in Cart- Change to customer:" + changeAmount + "Baht";
        } else {
            return "Not enough Money";
        }
    }

    public String checkoutWithDigitalMoneyFlow(FundamentalMintMember member) { //todo STUDENT:
        int totalCartPrice = member.totalCartPrice();
        if (totalCartPrice <= member.getDigitalMoney()) {
            member.setDigitalMoney(member.getDigitalMoney() - totalCartPrice);
            member.checkout(); // remove item from member's Cart and put to HistoryArray
            return "Paid for all item in Cart. DigitalMoney Left :" + member.getDigitalMoney() + "Baht";
        } else {
            return "Not enough DigitalMoney in account";
        }
    }
}
