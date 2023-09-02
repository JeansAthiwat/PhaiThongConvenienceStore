package test.grader;

import logic.app.AppController;
import logic.member.BasicMember;
import logic.member.FundamentalMintMember;
import logic.member.PhaiThongCasanovaMember;

import logic.store.Item;
import logic.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppControllerTest {
    Store store = Store.getInstance();
    BasicMember member1;
    FundamentalMintMember member2;
    PhaiThongCasanovaMember member3;

    Item item1;
    Item item2;
    Item item3;
    Item item4;
    Item item5;

    @BeforeEach
    public void setUp() {
        store.setStoreMoney(0);
        store.getStock().clear();
        store.getMembers().clear();

        item1 = new Item("JellyBeans", 1, 7);
        item2 = new Item("Painkiller", 100, 4);
        item3 = new Item("Orange", 10, 6);
        item4 = new Item("CoconutMilk", 1000, 3);
        item5 = new Item("CoconutMilk", 1000, 20);

        member1 = new BasicMember("member1", 1);
        member2 = new FundamentalMintMember("member2", 2, 1000, 5000);
        member3 = new PhaiThongCasanovaMember("member3", 3, 1000, 5000);

        member1.getShoppingCart().add(item1);
        member1.getShoppingCart().add(item2);
        member1.getShoppingCart().add(item3);
        member1.getShoppingCart().add(item4);

        member2.getShoppingCart().add(item1);
        member2.getShoppingCart().add(item2);
        member2.getShoppingCart().add(item3);
        member2.getShoppingCart().add(item4);

        member3.getShoppingCart().add(item5);

        store.getMembers().add(member1);
        store.getMembers().add(member2);
        store.getMembers().add(member3);
    }

    @Test
    void testCheckOutWithCashFlowSufficientCash() {
        AppController ac = AppController.getInstance();

        String out1 = ac.checkOutWithCashFlow(member1, 3467);

        assertTrue(member1.getShoppingCart().isEmpty());
        assertTrue(member1.getPurchaseHistory().contains(item1));
        assertTrue(member1.getPurchaseHistory().contains(item2));
        assertTrue(member1.getPurchaseHistory().contains(item3));
        assertTrue(member1.getPurchaseHistory().contains(item4));
        assertEquals(3467, store.getStoreMoney());
        assertEquals("Paid : 3467 Baht - Change to customer : 0 Baht", out1);

        String out2 = ac.checkOutWithCashFlow(member2, 3294);

        assertTrue(member2.getShoppingCart().isEmpty());
        assertTrue(member2.getPurchaseHistory().contains(item1));
        assertTrue(member2.getPurchaseHistory().contains(item2));
        assertTrue(member2.getPurchaseHistory().contains(item3));
        assertTrue(member2.getPurchaseHistory().contains(item4));
        assertEquals(6761, store.getStoreMoney());
        assertEquals("Paid : 3294 Baht - Change to customer : 0 Baht", out2);
    }

    @Test
    void testCheckOutWithCashFlowNotEnoughCash() {
        AppController ac = AppController.getInstance();

        String out3 = ac.checkOutWithCashFlow(member3, 17999);

        assertTrue(member3.getShoppingCart().contains(item5));
        assertTrue(member3.getPurchaseHistory().isEmpty());
        assertEquals(0, store.getStoreMoney());
        assertEquals("Not enough money!", out3);
    }


    @Test
    void testCheckOutDigitalMoneyFlowSufficientDMoney() {
        AppController ac = AppController.getInstance();

        String out2 = ac.checkoutWithDigitalMoneyFlow(member2);

        assertTrue(member2.getShoppingCart().isEmpty());
        assertTrue(member2.getPurchaseHistory().contains(item1));
        assertTrue(member2.getPurchaseHistory().contains(item2));
        assertTrue(member2.getPurchaseHistory().contains(item3));
        assertTrue(member2.getPurchaseHistory().contains(item4));
        assertEquals(3294, store.getStoreMoney());
        assertEquals(1706, member2.getDigitalMoney());
        assertEquals("Paid : 3294 Baht - DigitalMoney left : 1706 Baht", out2);

    }

    @Test
    void testCheckOutDigitalMoneyFlowNotEnoughDMoney() {
        AppController ac = AppController.getInstance();

        String out3 = ac.checkoutWithDigitalMoneyFlow(member3);

        assertTrue(member3.getShoppingCart().contains(item5));
        assertTrue(member3.getPurchaseHistory().isEmpty());
        assertEquals(0, store.getStoreMoney());
        assertEquals(5000, member3.getDigitalMoney());
        assertEquals("Not enough DigitalMoney in account", out3);

    }

}
