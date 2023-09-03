package test.grader;

import logic.members.StarvingStudentMember;
import logic.store.Item;
import logic.store.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StarvingStudentMemberTest {
    @Test
    void testConstructor() {
        StarvingStudentMember member = new StarvingStudentMember("LilWayne", 69420, 1000, 5);

        assertEquals("LilWayne", member.getName());
        assertEquals(69420, member.getMemberID());
        assertEquals(1000, member.getPoint());
        assertEquals(5, member.getDigitalMoney());
        assertEquals(0,member.getLoan());
        assertEquals(0.20, member.getDiscountPercent());
    }

    @Test
    void testBadConstructor() {
        StarvingStudentMember member = new StarvingStudentMember("   ", -55555, -1412, -600000);

        assertEquals("UnknownMember", member.getName());
        assertEquals(0, member.getMemberID());
        assertEquals(0, member.getPoint());
        assertEquals(0, member.getDigitalMoney());assertEquals(0,member.getLoan());
        assertEquals(0.20, member.getDiscountPercent());
    }

    @Test
    void testConvertPoint() {
        StarvingStudentMember member = new StarvingStudentMember("JohnnyDaKid", 12345, 1045, 500);
        member.convertPoint();

        assertEquals(70, member.getPoint());
        assertEquals(513, member.getDigitalMoney());
    }

    @Test
    void testToString() {
        StarvingStudentMember member = new StarvingStudentMember("JohnnyDaKid", 12345, 1045, 500);

        assertEquals("(StarvingStudent) 12345-JohnnyDaKid DMoney: 500 Pts: 1045 Loans: 0", member.toString());
    }

    @Test
    void testGetTierName() {
        StarvingStudentMember member = new StarvingStudentMember("JohnnyDaKid", 1, 0, 0);
        assertEquals("StarvingStudent", member.getTierName());
    }

    @Test
    void testTotalCartPrice() {
        StarvingStudentMember member1 = new StarvingStudentMember("LilJohn", 1, 0, 1000);
        StarvingStudentMember member2 = new StarvingStudentMember("LilWayne", 2, 10000, 2000);
        StarvingStudentMember member3 = new StarvingStudentMember("Biggie", 2, 10000, 2000);
        Item item1 = new Item("Item1", 200, 10);
        Item item2 = new Item("Item2", 100, 4);
        Item item3 = new Item("Item3", 1, 7);

        member2.getShoppingCart().add(item1);
        member2.getShoppingCart().add(item2);

        member3.getShoppingCart().add(item1);
        member3.getShoppingCart().add(item2);
        member3.getShoppingCart().add(item3);

        assertEquals(0, member1.totalCartPrice());
        assertEquals(1920, member2.totalCartPrice());
        assertEquals(1926, member3.totalCartPrice());
    }

    @Test
    void testGetDiscountPercent() {
        StarvingStudentMember member = new StarvingStudentMember("JohnnyDaKid", 8, 0, 1000);

        assertEquals(0.20, member.getDiscountPercent());
    }

    @Test
    void testSetLoan(){
        StarvingStudentMember member = new StarvingStudentMember("JohnnyDaKid", 8, 0, 1000);


        member.setLoan(-50);
        assertEquals(0,member.getLoan());
        member.setLoan(10000000);assertEquals(1920,member.getLoan());
    }

    @Test
    void testLoanMoney(){
        Store store = Store.getInstance();
        store.setStoreMoney(1000);

        StarvingStudentMember member1 = new StarvingStudentMember("JohnnyDaKid", 8, 0, 1000);
        StarvingStudentMember member2 = new StarvingStudentMember("JohnnyDaฺankruptedKid", 8, 0, 1000);
        member2.setLoan(1900);

        assertTrue(member1.loanMoney(500)); // Loan is within limit and store has enough money
        assertEquals(1500, member1.getDigitalMoney()); // Expected digital money after taking the loan
        assertEquals(500, member1.getLoan());
        assertEquals(500,store.getStoreMoney());

        assertFalse(member2.loanMoney(500)); // Loan is within limit and store has enough money
        assertEquals(1000, member2.getDigitalMoney()); // Expected digital money after taking the loan
        assertEquals(1900, member2.getLoan());
        assertEquals(500,store.getStoreMoney());

        assertFalse(member1.loanMoney(1000)); // Loan is within limit and store has enough money
        assertEquals(1500, member1.getDigitalMoney()); // Expected digital money after taking the loan
        assertEquals(500, member1.getLoan());
        assertEquals(500,store.getStoreMoney());
    }

    @Test
    void testReturnLoan() {
        Store store = Store.getInstance();
        store.setStoreMoney(0);
        StarvingStudentMember member1 = new StarvingStudentMember("JohnnyDaKid", 8, 0, 1000);
        StarvingStudentMember member2 = new StarvingStudentMember("JohnnyDaBankruptedKid", 8, 0, 1000);
        member1.setLoan(500);
        member2.setLoan(1500);

        assertTrue(member1.returnLoan(300)); // Returned amount is less than the loan
        assertEquals(700, member1.getDigitalMoney()); // Expected digital money after returning part of the loan
        assertEquals(200, member1.getLoan()); // Expected remaining loan amount
        assertEquals(300,store.getStoreMoney());

        assertTrue(member1.returnLoan(300)); // Returned amount is more than the totalLoan
        assertEquals(500, member1.getDigitalMoney()); // Expected digital money after returning part of the loan
        assertEquals(0, member1.getLoan()); // Expected remaining loan amount
        assertEquals(500,store.getStoreMoney());

        assertFalse(member2.returnLoan(1500));//member2 shouldn't be able to return cuz not enough
        assertEquals(1000,member2.getDigitalMoney());
        assertEquals(1500, member2.getLoan()); // Expected remaining loan amount
        assertEquals(500,store.getStoreMoney());
    }


}
