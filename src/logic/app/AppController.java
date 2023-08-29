package logic.app;

import logic.store.Item;
import logic.store.Store;

import java.util.ArrayList;

public class AppController {

    private static AppController instance = null;

    public AppController(){

    }

    public AppController getInstance(){
        if(instance == null){
            instance = new AppController();
        }
        return instance;
    }

    public Item addNewItemToStockFlow(String name,int price,int amount){
        //TODO
        Item newItem = new Item(name,price,amount);
        Store.getInstance().addItemToStock(newItem);
        return newItem;
    }

    public String checkStockFlow(){
        ArrayList<Item> stock = Store.getInstance().getStock();
        String out = "";
        for(int i=0; i< stock.size();i++){
            out += stock.get(i).getName() + ": x" + stock.get(i).getAmount() + "\n";
        }
        return out;
    }

    public void memberShoppingFlow(){
        //TODO
    }

    public void signUpMemberFlow(){
        //TODO
    }

    public void manageMemberFlow(){
        //TODO
    }



}
