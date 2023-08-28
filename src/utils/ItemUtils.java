package utils;

import logic.store.Item;

import java.util.ArrayList;

public class ItemUtils {
    public static int calculateTotalPrice(ArrayList<Item> itemsToBuy){
        int totalPrice = 0;
        for(Item item : itemsToBuy){
            totalPrice += item.getPrice()*item.getAmount();
        }
        return totalPrice;
    }

}
