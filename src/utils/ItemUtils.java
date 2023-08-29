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

    public static String showItemsInCart(BasicMember member){
        ArrayList<Item> shoppingCart = member.getShoppingCart();
        String out = "";
        for (int i = 0; i < shoppingCart.size(); i++) {
            Item currentItem = shoppingCart.get(i);
            out += "(" + i + ") x" + currentItem.getAmount() + "  :" + currentItem.getName() + " (" + currentItem.getPrice() + " Baht/item)" + "\n";
        }
        return out;
    }

}
