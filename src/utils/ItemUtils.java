package utils;

import logic.store.Item;

import java.util.ArrayList;

public class ItemUtils {
    public static int calculateTotalPrice(ArrayList<Item> shoppingCart){
        // calculate the totalPrice of Items without Discount. Use this to make sure totalCartPrice() are correct.
        int totalPrice = 0;
        for(Item item : shoppingCart){
            totalPrice += item.getPrice()*item.getAmount();
        }
        return totalPrice;
    }

    public static int calculateTotalPrice(ArrayList<Item> shoppingCart , double discountPercent){
        // calculate the totalPrice of Items WITH Discount. Use this to make sure totalCartPrice() are correct.
        int totalPrice = calculateTotalPrice(shoppingCart);
        return (int) Math.ceil(totalPrice*(1-discountPercent));
    }

}
