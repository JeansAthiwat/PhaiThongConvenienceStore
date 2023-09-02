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

    public String addNewItemToStockFlow(String name, int price, int amount) {
        if (amount < 1) return "Incorrect Item Amount!";
        Item newItem = new Item(name, price, amount);
        Store.getInstance().addItemToStock(newItem);
        return "ADDED - " + newItem;
    }

    public String showStockFlow() {
        ArrayList<Item> stock = Store.getInstance().getStock();
        String out = "                  ---===Item(s) in stock===--- \n";
        for (int i = 0; i < stock.size(); i++) {
            Item currentItem = stock.get(i);
            out += "(" + i + ") " + currentItem + " (" + currentItem.getPrice() + " Baht/item)" + "\n";
        }
        return out;
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

    public String signUpMemberFlow(int member_type, String memberName, int memberID, int memberDigitalMoney) {
        if (memberID < 0) return "Invalid ID! (must be zero or positive int)";
        BasicMember member = null;
        switch (member_type) {
            case 0 -> member = new BasicMember(memberName, memberID);
            case 1 -> member = new FundamentalMintMember(memberName, memberID, 0, memberDigitalMoney);
            case 2 -> member = new PhaiThongCasanovaMember(memberName, memberID, 0, memberDigitalMoney);
            case 3 -> member = new StarvingStudentMember(memberName, memberID, 0, memberDigitalMoney);
        }
        Store store = Store.getInstance();
        if (member == null || store.isMember(member)) {
            return "Member Already Exist!!";
        } else {
            store.getMembers().add(member);
            return "New Member Created";
        }
    }

    public String deleteMemberFlow(BasicMember member) {
        Store.getInstance().getMembers().remove(member);
        return "Deleted: " + member;
    }

    public String convertPointFlow(FundamentalMintMember member) {
        int digitalMoneyBefore = member.getDigitalMoney();
        member.convertPoint();
        int convertedAmount = member.getDigitalMoney() - digitalMoneyBefore;
        return "All Points Converted To: " + convertedAmount + "Baht Of DigitalMoney";
    }

    public String showMemberList() {
        String out = "";
        ArrayList<BasicMember> members = Store.getInstance().getMembers();
        for (int i = 0; i < members.size(); i++) {
            out += "(" + i + ")" + members.get(i).toString() + "\n";
        }
        return out;
    }

    public String showItemsInCart(BasicMember member) {
        ArrayList<Item> shoppingCart = member.getShoppingCart();
        String out = "";
        for (int i = 0; i < shoppingCart.size(); i++) {
            Item currentItem = shoppingCart.get(i);
            out += "(" + i + ") x" + currentItem.getAmount() + "  :" + currentItem.getName() + " (" + currentItem.getPrice() + " Baht/item)" + "\n";
        }
        return out;
    }

    public String showPurchaseHistory(BasicMember member) {
        String out = "";
        ArrayList<Item> purchaseHistory = member.getPurchaseHistory();
        if (purchaseHistory.isEmpty()) {
            return "NO PURCHASE HISTORY";
        }
        for (int i = 0; i < purchaseHistory.size(); i++) {
            out += "(" + i + ")" + purchaseHistory.get(i).toString() + "\n";
        }
        return out;
    }

    public String addItemToShoppingCartFlow(BasicMember member, int itemStockIndex, int amount) {
        ArrayList<Item> stock = Store.getInstance().getStock();
        if (stock.get(itemStockIndex).getAmount() < amount) {
            return "Cannot add more than what the stock has.";
        } else if (amount <= 0) {
            return "Incorrect Amount!";
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

    public String topUpDigitalMoneyFlow(FundamentalMintMember member, int amount) {
        member.setDigitalMoney(member.getDigitalMoney() + amount);
        return "total DigitalMoney in account : " + member.getDigitalMoney() + "Baht";
    }

    public String gachaFlow(PhaiThongCasanovaMember member) {
        String out = "";
        if (member.getPoint() < 1000) {
            return "Not Enough Points";
        }
        Item item = member.giveRandomItemFromStore();
        if (item == null) {
            return "No item in Stock";
        }
        return member.getName() + " got the " + item.toString() + " for free!";
    }

    public String getLoanFlow(StarvingStudentMember member, int amount) {
        boolean isSuccessful = member.loanMoney(amount);
        if (isSuccessful) {
            return "Successfully Loaned " + amount + " Baht to " + member.getName()
                    + ". Store Money Left:" + Store.getInstance().getStoreMoney() + " Baht";
        }
        return "-Store don't have Enough Money to Loan \n *OR* \n-Loan Amount Exceed the Member's limit (maximum:" + StarvingStudentMember.MAX_LOAN + "Baht)";
    }

    public String returnLoanFlow(StarvingStudentMember member, int amount) {
        boolean isSuccessful = member.returnLoan(amount);
        if (isSuccessful) return "Successfully Return " + amount + " Baht of loan";
        return "Cannot return loan because the member doesn't have enough DigitalMoney in account";
    }

    public String checkOutWithCashFlow(BasicMember member, int givenMoney) { //todo STUDENT:
        int totalCartPrice = member.totalCartPrice();
        if (totalCartPrice <= givenMoney) {
            member.checkout();
            int changeAmount = givenMoney - totalCartPrice;
            Store.getInstance().setStoreMoney(Store.getInstance().getStoreMoney() + totalCartPrice);
            return "Paid for all item in cart - Change to customer: " + changeAmount + " Baht";
        } else {
            return "Not enough money!";
        }
    }

    public String checkoutWithDigitalMoneyFlow(FundamentalMintMember member) { //todo STUDENT:
        int totalCartPrice = member.totalCartPrice();
        if (totalCartPrice <= member.getDigitalMoney()) {
            member.setDigitalMoney(member.getDigitalMoney() - totalCartPrice);
            member.checkout(); // remove item from member's Cart and put to HistoryArray
            Store.getInstance().setStoreMoney(Store.getInstance().getStoreMoney() + totalCartPrice);
            return "Paid for all item in Cart. DigitalMoney Left :" + member.getDigitalMoney() + "Baht";
        } else {
            return "Not enough DigitalMoney in account";
        }
    }
}
