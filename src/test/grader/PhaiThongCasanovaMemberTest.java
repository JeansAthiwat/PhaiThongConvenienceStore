package test.grader;

import logic.member.BasicMember;
import logic.member.FundamentalMintMember;
import logic.member.PhaiThongCasanovaMember;
import logic.store.Item;
import logic.store.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhaiThongCasanovaMemberTest {
    @Test
    void testConstructor() {
        PhaiThongCasanovaMember member = new PhaiThongCasanovaMember("LilWayne", 69420, 1000, 5);

        assertEquals("LilWayne", member.getName());
        assertEquals(69420, member.getMemberID());
        assertEquals(1000, member.getPoint());
        assertEquals(5, member.getDigitalMoney());
        assertEquals(0.10, member.getDiscountPercent());
    }

    @Test
    void testBadConstructor() {
        PhaiThongCasanovaMember member = new PhaiThongCasanovaMember("   ", -55555, -1412, -600000);

        assertEquals("UnknownMember", member.getName());
        assertEquals(0, member.getMemberID());
        assertEquals(0, member.getPoint());
        assertEquals(0, member.getDigitalMoney());
        assertEquals(0.10, member.getDiscountPercent());
    }

    @Test
    void testConvertPoint() {
        PhaiThongCasanovaMember member = new PhaiThongCasanovaMember("CasanovaJohn", 12345, 1065, 500);
        member.convertPoint();

        assertEquals(15, member.getPoint());
        assertEquals(521, member.getDigitalMoney());
    }

    @Test
    void testToString() {
        PhaiThongCasanovaMember member = new PhaiThongCasanovaMember("CasanovaJohn", 12345, 1045, 500);

        assertEquals("(PhaiThongCasanova) 12345-CasanovaJohn DMoney: 500 Pts: 1045", member.toString());
    }

    @Test
    void testGetTierName() {
        PhaiThongCasanovaMember member = new PhaiThongCasanovaMember("CasanovaJohn", 1, 0, 0);
        assertEquals("PhaiThongCasanova", member.getTierName());
    }

    @Test
    void testTotalCartPrice() {
        PhaiThongCasanovaMember member1 = new PhaiThongCasanovaMember("LilJohn", 1, 0, 1000);
        PhaiThongCasanovaMember member2 = new PhaiThongCasanovaMember("LilWayne", 2, 10000, 2000);
        PhaiThongCasanovaMember member3 = new PhaiThongCasanovaMember("Biggie", 2, 10000, 2000);
        Item item1 = new Item("Item1", 200, 10);
        Item item2 = new Item("Item2", 100, 4);
        Item item3 = new Item("Item3", 1, 7);

        member2.getShoppingCart().add(item1);
        member2.getShoppingCart().add(item2);

        member3.getShoppingCart().add(item1);
        member3.getShoppingCart().add(item2);
        member3.getShoppingCart().add(item3);

        assertEquals(0, member1.totalCartPrice());
        assertEquals(2160, member2.totalCartPrice());
        assertEquals(2167, member3.totalCartPrice());
    }

    @Test
    void testGetDiscountPercent() {
        PhaiThongCasanovaMember member = new PhaiThongCasanovaMember("LilJohn", 8, 0, 1000);

        assertEquals(0.10, member.getDiscountPercent());
    }

    @Test
    void testGiveRandomItemFromStore() {
        Store store = new Store(5000);

        Item item1 = new Item("Item1", 200, 1);
        Item item2 = new Item("Item2", 100, 1);
        Item item3 = new Item("Item3", 3, 1);
        Item item4 = new Item("Item4", 4, 1);


        PhaiThongCasanovaMember member1 = new PhaiThongCasanovaMember("LilWayne", 1, 10000, 2000);
        assertTrue(member1.getPurchaseHistory().isEmpty());
        PhaiThongCasanovaMember member2 = new PhaiThongCasanovaMember("LilPain", 2, 999, 2000);
        PhaiThongCasanovaMember member3 = new PhaiThongCasanovaMember("LilJohn", 3, 3333, 1000);

        System.out.println(store.getStoreMoney());
        member1.giveRandomItemFromStore();

        store.addItemToStock(item1);
        store.addItemToStock(item2);
        store.addItemToStock(item3);
        store.addItemToStock(item4);

        for(int i = 0; i < 4;i++){
            member2.giveRandomItemFromStore();
            member3.giveRandomItemFromStore();
        }
        System.out.println(member1.getPurchaseHistory());

        assertTrue(member1.getPurchaseHistory().isEmpty());
        assertEquals(3,member2.getPurchaseHistory().size());



    }
}
