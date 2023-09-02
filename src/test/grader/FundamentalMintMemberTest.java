package test.grader;

import logic.member.BasicMember;
import logic.member.FundamentalMintMember;
import logic.store.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FundamentalMintMemberTest {

    @Test
    void testConstructor() {
        FundamentalMintMember member = new FundamentalMintMember("LilWayne", 69420, 1000, 5);

        assertEquals("LilWayne", member.getName());
        assertEquals(69420, member.getMemberID());
        assertEquals(1000, member.getPoint());
        assertEquals(5, member.getDigitalMoney());
        assertEquals(0.05, member.getDiscountPercent());
    }

    @Test
    void testBadConstructor() {
        FundamentalMintMember member = new FundamentalMintMember("   ", -55555, -1412, -600000);

        assertEquals("UnknownMember", member.getName());
        assertEquals(0, member.getMemberID());
        assertEquals(0, member.getPoint());
        assertEquals(0, member.getDigitalMoney());
        assertEquals(0.05, member.getDiscountPercent());
    }

    @Test
    void testConvertPoint() {
        FundamentalMintMember member = new FundamentalMintMember("LilJohnMint", 12345, 1045, 500);
        member.convertPoint();

        assertEquals(45, member.getPoint());
        assertEquals(510, member.getDigitalMoney());
    }

    @Test
    void testToString() {
        FundamentalMintMember member = new FundamentalMintMember("LilJohnMint", 12345, 1045, 500);

        assertEquals("(FundamentalMint) 12345-LilJohnMint DMoney: 500 Pts: 1045", member.toString());
    }

    @Test
    void testEquals() {

        FundamentalMintMember member1 = new FundamentalMintMember("Eminem", 1, 0, 100); // Same memberID as member1
        BasicMember member2 = new BasicMember("SlimShady", 1);
        BasicMember differentMember = new BasicMember("MachineGunKelly", 99999);

        assertTrue(member1.equals(member1));// Same object should be equal to itself
        assertTrue(member1.equals(member2));// Different objects with the same memberID should be equal
        assertFalse(member1.equals(differentMember)); // Different objects with different memberID should not be equal
        assertFalse(member1.equals(null)); // Should not be equal to null
    }

    @Test
    void testGetTierName() {
        FundamentalMintMember member = new FundamentalMintMember("LilJohnMint", 1,0,0);
        assertEquals("FundamentalMint", member.getTierName());
    }

    @Test
    void testTotalCartPrice() {
        FundamentalMintMember member1 = new FundamentalMintMember("LilJohn", 1, 0, 1000);
        FundamentalMintMember member2 = new FundamentalMintMember("LilWayne", 2, 10000, 2000);
        FundamentalMintMember member3 = new FundamentalMintMember("Biggie", 2, 10000, 2000);
        Item item1 = new Item("Item1", 200, 10);
        Item item2 = new Item("Item2", 100, 4);
        Item item3 = new Item("Item3", 1, 7);

        member2.getShoppingCart().add(item1);
        member2.getShoppingCart().add(item2);

        member3.getShoppingCart().add(item1);
        member3.getShoppingCart().add(item2);
        member3.getShoppingCart().add(item3);

        assertEquals(0, member1.totalCartPrice());
        assertEquals(2280, member2.totalCartPrice());
        assertEquals(2287, member3.totalCartPrice());
    }

    @Test
    void testCheckout() {
        FundamentalMintMember member = new FundamentalMintMember("LilJohn", 8, 0, 1000);
        Item item1 = new Item("Item1", 200, 10);
        Item item2 = new Item("Item2", 100, 4);
        member.getShoppingCart().add(item1);
        member.getShoppingCart().add(item2);

        int expectedPoints = member.getPoint() + item1.getPrice() + item2.getPrice();
        member.checkout();
        // Verify that the actual points match the expected points
        assertEquals(2280, member.getPoint());
    }

    @Test
    void testSetPoint() {
        FundamentalMintMember member = new FundamentalMintMember("LilJohn", 8, 0, 1000);
        member.setPoint(20);

        assertEquals(20,member.getPoint());
    }

    @Test
    void testGetDiscountPercent() {
        FundamentalMintMember member = new FundamentalMintMember("LilJohn", 8, 0, 1000);

        assertEquals(0.05,member.getDiscountPercent());
    }

    @Test
    void testSetDigitalMoney() {
        FundamentalMintMember member = new FundamentalMintMember("LilJohn", 8, 0, 1000);
        member.setDigitalMoney(800);

        assertEquals(800,member.getDigitalMoney());
    }



}
