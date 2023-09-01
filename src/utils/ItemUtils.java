package utils;

import logic.member.BasicMember;
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

    public static int calculateTotalPrice(ArrayList<Item> itemsToBuy , double discountPercent){
        int totalPrice = calculateTotalPrice(itemsToBuy);
        return (int) Math.ceil(totalPrice*(1-discountPercent));
    }

}
