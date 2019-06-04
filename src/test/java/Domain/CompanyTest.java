package Domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyTest {

    @Test
    public void isValidCompanyID() {
        //True
        assertTrue(Company.isValidCompanyID(12)); //Normal number
        assertTrue(Company.isValidCompanyID("12")); // normal number as string
        assertTrue(Company.isValidCompanyID((String)null)); //May be null
        assertTrue(Company.isValidCompanyID((Integer) null)); //May be null if new
        assertTrue(Company.isValidCompanyID("   ")); //If it may be null, it may also be empty

        //False
        assertFalse(Company.isValidCompanyID(0));
        assertFalse(Company.isValidCompanyID("0"));
        assertFalse(Company.isValidCompanyID(-12));
        assertFalse(Company.isValidCompanyID("-12"));
        assertFalse(Company.isValidCompanyID("12ab")); //may not contain numbers
    }

    @Test
    public void isValidCvrNr() {
        //True
        assertTrue(Company.isValidCvrNr("12345678")); //8 numbers

        //False
        assertFalse(Company.isValidCvrNr("123")); //to short
        assertFalse(Company.isValidCvrNr("123a1234")); //contain letters
        assertFalse(Company.isValidCvrNr("123412345125211")); //To long
        assertFalse(Company.isValidCvrNr(null));
        assertFalse(Company.isValidCvrNr("     "));
    }

    @Test
    public void isValidCompanyName() {
        //True
        assertTrue(Company.isValidCompanyName("Math Inc")); //valid name

        //False
        assertFalse(Company.isValidCompanyName("   "));
        assertFalse(Company.isValidCompanyName(null));
        //Way to long
        assertFalse(Company.isValidCompanyName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }
}