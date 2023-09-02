package test.grader;

import logic.member.BasicMember;
import logic.member.FundamentalMintMember;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FundamentalMintMemberTest {

    @Test
    void testConstructor() {
        FundamentalMintMember member = new FundamentalMintMember("LilWayne", 69420,1000,5);

        assertEquals("LilWayne", member.getName());
        assertEquals(69420, member.getMemberID());
        assertEquals(1000,member.getPoint());
        assertEquals(5,member.getDigitalMoney());
    }

    @Test
    void testBadConstructor() {
        FundamentalMintMember member = new FundamentalMintMember("   ", -55555,-1412,-600000);

        assertEquals("UnknownMember", member.getName());
        assertEquals(0, member.getMemberID());
        assertEquals(0,member.getPoint());
        assertEquals(0,member.getDigitalMoney());
    }



}
