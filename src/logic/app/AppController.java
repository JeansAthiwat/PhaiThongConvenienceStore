package logic.app;

import logic.store.Item;
import logic.store.Store;

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

    public Item addItemToStockFlow(Item newItem){
        //TODO
        Store.getInstance().addItemToStock(newItem);
        return newItem;
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

    public Item createNewItem(String name,int price,int amount){

        return new Item(name,price,amount);
    }

}
