package Domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class EducationTest {

    @Test
    public void isValidAmuNr() {
        //True
        assertTrue(Education.isValidAmuNr(12)); //Normal number
        assertTrue(Education.isValidAmuNr("12")); // normal number as string
        assertTrue(Education.isValidAmuNr((String)null)); //May be null
        assertTrue(Education.isValidAmuNr((Integer) null)); //May be null if new
        assertTrue(Education.isValidAmuNr("   ")); //If it may be null it may be empty

        //False
        assertFalse(Education.isValidAmuNr(0));
        assertFalse(Education.isValidAmuNr("0"));
        assertFalse(Education.isValidAmuNr(-12));
        assertFalse(Education.isValidAmuNr("-12"));
        assertFalse(Education.isValidAmuNr("12ab")); //may not contain numbers
    }

    @Test
    public void isValidEducationName() {
        //True
        assertTrue(Education.isValidEducationName("Math Inc")); //valid name

        //False
        assertFalse(Education.isValidEducationName("   "));
        assertFalse(Education.isValidEducationName(null));
        //Way to long
        assertFalse(Education.isValidEducationName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }

    @Test
    public void isValidDescription() {
        //True (may actually be empty)
        assertTrue(Education.isValidDescription("Hello")); //Normal dscription
        assertTrue(Education.isValidDescription("     "));
        assertTrue(Education.isValidDescription(null));
    }

    @Test
    public void isValidNoOfDays() {
        //True
        assertTrue(Education.isValidNoOfDays(12)); //Normal number
        assertTrue(Education.isValidNoOfDays("12")); // normal number as string

        //False
        assertFalse(Education.isValidNoOfDays(0));
        assertFalse(Education.isValidNoOfDays("0"));
        assertFalse(Education.isValidNoOfDays(-12));
        assertFalse(Education.isValidNoOfDays("-12"));
        assertFalse(Education.isValidNoOfDays("12ab")); //may not contain numbers
        assertFalse(Education.isValidNoOfDays((String)null)); //May not be null
        assertFalse(Education.isValidNoOfDays((Integer) null)); //May not be null if new
        assertFalse(Education.isValidNoOfDays("   ")); //If it may noy be null it may be empty
    }

}