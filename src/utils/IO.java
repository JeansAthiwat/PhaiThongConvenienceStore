package utils;

import logic.app.AppController;
import logic.member.BasicMember;
import logic.member.FundamentalMintMember;
import logic.store.Item;
import logic.store.Store;

import java.util.Scanner;

public class IO {
    private static final Scanner sc = new Scanner(System.in);
    private static IO instance = null;
    //private static final AppController appController = new AppController();
    private static AppController ac;


    public static IO getInstance() {
        if (instance == null) {
            instance = new IO();
        }
        return instance;
    }

    public IO() {
        ac = new AppController();
    }

    public void home() {
        System.out.println("=================================================================");
        System.out.println("<0> Add Items to stock");
        System.out.println("<1> Check stock");
        System.out.println("<2> Select Member to buy items");
        System.out.println("<3> Sign Up new member");
        System.out.println("<4> Manage member");
        int choice_home = choiceCheck(0, 4);
        System.out.println("=================================================================");
        switch (choice_home) { // send to do each flow in AppController
            case 0 -> {
                System.out.println("   ---===Creating New Item===---");
                System.out.println("Enter Item name : ");
                String name = sc.next();
                System.out.println("(if already exist. It will use the old value) \nEnter Item Price :");
                int price = Integer.parseInt(sc.next());
                System.out.println("Enter Amount to add :");
                int amount = Integer.parseInt(sc.next());
                Item addedItem = ac.addNewItemToStockFlow(name, price, amount);
                System.out.println("ADDED - " + addedItem.toString());
            }
            case 1 -> {
                System.out.println("   ---===Item(s) in stock===---");
                String out = ac.showStockFlow();
                System.out.println(out);
            }
            case 2 -> {
                //TODO
                System.out.println("   ---===Member to buy items===---");
                System.out.println("Input buyer's memberID :");
                int memberID = Integer.parseInt(sc.next());
                BasicMember member = ac.getMemberByID(memberID);
                if (member == null) {
                    System.out.println("This memberID doesn't exist!");
                    break;
                }
                System.out.println("Current customer: " + member.toString());
                System.out.println("In Shopping Cart: \n" + ItemUtils.showItemsInCart(member)); // ITemUtils or ac?
                int maxChoice = (member instanceof FundamentalMintMember) ? 2 : 3; // maxChoice will be 2 (FMM can pay with digitalMoney)
                System.out.println("<0> Add Items to Cart");
                System.out.println("<1> Remove Items from Cart");
                System.out.println("<2> CheckOut With Cash");
                if (maxChoice == 2) System.out.println("<3> CheckOut With Digital Money");
                int choice_member = choiceCheck(0, maxChoice);
                while (choice_member < 2) {
                    if (choice_member == 0) {
                        //let member pick item from stock to add to shoppingCart
                        System.out.println("Pick item to add to Cart : \n");
                        System.out.println(ac.showStockFlow());
                        int choice_stockItem = choiceCheck(0, Store.getInstance().getStock().size());
                        int amountToAdd = sc.nextInt();
                        String out = ac.addItemToShoppingCartFlow(member, choice_stockItem, amountToAdd);
                    }else if(choice_member ==1 ){

                    }
                    //TODO: break da loop
                    System.out.println("In Shopping Cart: \n" + ItemUtils.showItemsInCart(member));
                }


                //add item to member's cart
                //checkout
                // if - instance of fundamentalMintMember checkout with digitalMoney

                //after satisfied
                ac.memberShoppingFlow();
            }
            case 3 -> ac.signUpMemberFlow();
            case 4 -> ac.manageMemberFlow();
        }
    }

    public static void newItemInput() {


    }

    private static int choiceCheck(int lowestChoice, int HighestChoice) {
        int choice = sc.nextInt();
        while (choice < lowestChoice || choice > HighestChoice) {
            System.out.println("Invalid Input! (Try again) :");
            choice = sc.nextInt();
        }
        return choice;
    }
}
