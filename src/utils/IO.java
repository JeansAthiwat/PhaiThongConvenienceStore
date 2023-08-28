package utils;

import logic.app.AppController;
import logic.store.Item;

import java.util.Scanner;

public class IO {
    private static final Scanner sc = new Scanner(System.in);
    private static IO instance = new IO();
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
        home();
    }

    public static void home() {
        System.out.println("=================================================================");
        System.out.println("<0> Add Items to stock");
        System.out.println("<1> Select Member to buy items");
        System.out.println("<2> Sign Up new member");
        System.out.println("<3> Manage member");
        int choice = choiceCheck(0, 3);
        System.out.println("=================================================================");
        switch (choice) { // send to do each flow in AppController
            case 0 -> {
                System.out.println("Enter Item name : ");
                String name = sc.next();
                System.out.println("(if already exist. It will use the old value) \nEnter Item Price :");
                int price = Integer.parseInt(sc.next());
                System.out.println("Enter Amount to add :");
                int amount = Integer.parseInt(sc.next());
                Item addedItem = ac.addItemToStockFlow(ac.createNewItem(name, price, amount));
                System.out.println(addedItem.toString() + "ADDED");
            }
            case 1 -> ac.memberShoppingFlow();
            case 2 -> ac.signUpMemberFlow();
            case 3 -> ac.manageMemberFlow();
        }
    }

    public static void newItemInput() {


    }

    private static int choiceCheck(int lowestChoice, int HighestChoice) {
        int choice = sc.nextInt();
        while (choice < lowestChoice || choice > HighestChoice) {
            choice = sc.nextInt();
        }
        return choice;
    }
}
