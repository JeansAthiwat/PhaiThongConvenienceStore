package utils;

import java.util.Scanner;

public class IO {
    private static Scanner sc = new Scanner(System.in);
    private static IO instance = new IO();

    public static IO getInstance() {
        if (instance == null) {
            instance = new IO();
        }
        return instance;
    }

    public IO() {
        home();
    }

    public static void home() {
        System.out.println("Welcome to PaiThong Store You are the Manager Here. Good Luck!!");
        System.out.println("=================================================================");
        System.out.println("<0> Add Items to stock");
        System.out.println("<1> Select Member to buy items");
        System.out.println("<2> Sign Up new member");
        System.out.println("<3> Manage member");
        int choice = choiceCheck(0, 3);
        switch (choice) {
            case 0:
                //addItemToStockFlow();
                break;
            case 1:
                //memberShoppingFlow();
                break;
            case 2:
                //signUpMemberFlow();
                break;
            case 3:
                //manageMemberFlow();
                break;
        }
    }

    private static int choiceCheck(int lowestChoice, int HighestChoice) {
        int choice = sc.nextInt();
        while (choice < lowestChoice || choice > HighestChoice) {
            choice = sc.nextInt();
        }
        return choice;
    }
}
