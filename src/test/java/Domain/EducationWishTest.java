package Domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class EducationWishTest {

    @Test
    public void isValidEducationWishID() {
        //True
        assertTrue(EducationWish.isValidEducationWishID(12)); //Normal number
        assertTrue(EducationWish.isValidEducationWishID("12")); // normal number as string
        assertTrue(EducationWish.isValidEducationWishID((String)null)); //May be null
        assertTrue(EducationWish.isValidEducationWishID((Integer) null)); //May be null if new

        //False
        assertFalse(EducationWish.isValidEducationWishID(0));
        assertFalse(EducationWish.isValidEducationWishID("0"));
        assertFalse(EducationWish.isValidEducationWishID("   ")); //may not be empty string
        assertFalse(EducationWish.isValidEducationWishID(-12));
        assertFalse(EducationWish.isValidEducationWishID("-12"));
        assertFalse(EducationWish.isValidEducationWishID("12ab")); //may not contain numbers
    }


    @Test
    public void isValidPriority() {
        //True
        assertTrue(EducationWish.isValidPriority(2));
        assertTrue(EducationWish.isValidPriority("2"));

        //False
        assertFalse(EducationWish.isValidPriority(-1));
        assertFalse(EducationWish.isValidPriority(0));
        assertFalse(EducationWish.isValidPriority(5));
        assertFalse(EducationWish.isValidPriority("-1"));
        assertFalse(EducationWish.isValidPriority("0"));
        assertFalse(EducationWish.isValidPriority("5"));
        assertFalse(EducationWish.isValidPriority("2a"));
        assertFalse(EducationWish.isValidPriority("   "));
        assertFalse(EducationWish.isValidPriority((String)null));
        assertFalse(EducationWish.isValidPriority((Integer)null));
    }
}