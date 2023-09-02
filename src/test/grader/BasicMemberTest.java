package test.grader;


import static org.junit.jupiter.api.Assertions.*;

import logic.member.BasicMember;
import logic.store.Item;
import org.junit.jupiter.api.Test;


public class BasicMemberTest {

    @Test
    void testConstructor() {
        BasicMember member = new BasicMember("LilWayne", 69420);

        assertEquals("LilWayne", member.getName());
        assertEquals(69420, member.getMemberID());
    }

    @Test
    void testBadConstructor() {
        BasicMember member = new BasicMember("   ", -55555);

        assertEquals("UnknownMember", member.getName());
        assertEquals(0, member.getMemberID());
    }

    @Test
    void testSetName() {
        BasicMember member = new BasicMember("Soyjak", 11244);

        member.setName("Chad");
        assertEquals("Chad", member.getName());
    }

    @Test
    void testSetMemberID() {
        BasicMember member = new BasicMember("Soyjak", 11111);

        member.setMemberID(22222);
        assertEquals(22222, member.getMemberID());
    }

    @Test
    void testToString() {
        BasicMember member = new BasicMember("Jojo", 12345);

        assertEquals("(Basic) 12345-Jojo", member.toString());
    }

    @Test
    void testEquals() {
        BasicMember member1 = new BasicMember("Eminem", 1);
        BasicMember member2 = new BasicMember("SlimShady", 1); // Same memberID as member1
        BasicMember differentMember = new BasicMember("MachineGunKelly", 99999);

        assertTrue(member1.equals(member1));// Same object should be equal to itself
        assertTrue(member1.equals(member2));// Different objects with the same memberID should be equal
        assertFalse(member1.equals(differentMember)); // Different objects with different memberID should not be equal
        assertFalse(member1.equals(null)); // Should not be equal to null
    }

    @Test
    void testAddToPurchaseHistoryNewItem() {
        BasicMember member = new BasicMember("LilJohn", 1);
        assertTrue(member.getPurchaseHistory().isEmpty());
        Item newItem = new Item("New Item",100, 15);
        member.addToPurchaseHistory(newItem);

        assertTrue(member.getPurchaseHistory().contains(newItem));
    }

    @Test
    void testAddToPurchaseHistoryExistingItem() {
        BasicMember member = new BasicMember("LilJohn", 1);
        assertTrue(member.getPurchaseHistory().isEmpty());
        Item existingItem1 = new Item("ExistingItem1",300, 100);
        Item existingItem2 = new Item("ExistingItem2",300, 10);
        member.addToPurchaseHistory(existingItem1);
        member.addToPurchaseHistory(existingItem2);
        assertTrue(member.getPurchaseHistory().contains(existingItem1));
        assertTrue(member.getPurchaseHistory().contains(existingItem2));
        Item updatedItem = new Item("ExistingItem2",450, 1);
        member.addToPurchaseHistory(updatedItem);

        assertEquals(100, member.getPurchaseHistory().get(0).getAmount());
        assertEquals(11, member.getPurchaseHistory().get(1).getAmount());
    }

    @Test
    void testCheckOutEmptyCart() {
        BasicMember member = new BasicMember("LilJohn", 1);

        assertTrue(member.getShoppingCart().isEmpty());// Ensure the shopping cart is initially empty
        member.checkout();        // Call the checkout method
        assertTrue(member.getShoppingCart().isEmpty());        // Verify that the shopping cart remains empty after checkout
    }

    @Test
    void testCheckoutNonEmptyCart() {
        BasicMember member = new BasicMember("LilJohn", 1);
        Item item1 = new Item("IceCream", 100, 10);
        Item item2 = new Item("Cake", 300, 20);
        member.getShoppingCart().add(item1);
        member.getShoppingCart().add(item2);
        member.checkout();

        assertTrue(member.getShoppingCart().isEmpty());
        assertTrue(member.getPurchaseHistory().contains(item1));
        assertTrue(member.getPurchaseHistory().contains(item2));
    }

    @Test
    void testTotalCartPrice(){
        BasicMember member1 = new BasicMember("LilJohn", 1);
        assertTrue(member1.getShoppingCart().isEmpty());
        BasicMember member2 = new BasicMember("LilWayne", 2);
        assertTrue(member2.getShoppingCart().isEmpty());
        Item item1 = new Item("Item1",200, 10);
        Item item2 = new Item("Item2",100, 4);
        member2.getShoppingCart().add(item1);
        member2.getShoppingCart().add(item2);

        assertEquals(0, member1.totalCartPrice());
        assertEquals(2400, member2.totalCartPrice());
    }


}

