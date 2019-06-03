package Domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProviderTest {

    @Test
    public void isValidProviderID() {
        //True
        assertTrue(Provider.isValidProviderID(12)); //Normal number
        assertTrue(Provider.isValidProviderID("12")); // normal number as string
        assertTrue(Provider.isValidProviderID((String)null)); //May be null
        assertTrue(Provider.isValidProviderID((Integer) null)); //May be null if new

        //False
        assertFalse(Provider.isValidProviderID(0));
        assertFalse(Provider.isValidProviderID("0"));
        assertFalse(Provider.isValidProviderID("   ")); //may not be empty string
        assertFalse(Provider.isValidProviderID(-12));
        assertFalse(Provider.isValidProviderID("-12"));
        assertFalse(Provider.isValidProviderID("12ab")); //may not contain numbers
    }

    @Test
    public void isValidProviderName() {
        //True
        assertTrue(Provider.isValidProviderName("Providername")); //valid name

        //False
        assertFalse(Provider.isValidProviderName("   "));
        assertFalse(Provider.isValidProviderName(null));
        //Way to long
        assertFalse(Provider.isValidProviderName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }
}