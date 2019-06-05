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
        assertTrue(Provider.isValidProviderID("   ")); //If it may be null it may be empty

        //False
        assertFalse(Provider.isValidProviderID(0));
        assertFalse(Provider.isValidProviderID("0"));
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

    @Test
    public void equalsNullPointerTest() {
        Provider provider1 = new Provider(null,null);
        Provider provider2 = new Provider(null,null);

        assertEquals(provider1,provider2);
    }

    @Test
    public void equalsSameObjectTest(){
        Provider provider1 = new Provider(4,"Test");

        assertEquals(provider1,provider1);
    }

    @Test
    public void equalsSameDomain(){
        Domain domain1 = new Provider(2,"Test");
        Domain domain2 = new Provider(2,"Test");
        Domain domain3 = new Provider(3,"Test");

        assertEquals(domain1,domain2);
        assertNotEquals(domain1,domain3);
    }

    @Test
    public void equalsNotInstantOf(){
        Domain domain1 = new Provider(2,"Test");
        Domain testCompany1 = new Company(1,"13","241",null,null);

        assertNotEquals(domain1,testCompany1);
    }

    @Test
    public void equalsNullObjects(){
        Provider provider1= null;
        Provider provider2 = new Provider(4,"Test");

        assertNotEquals(provider1,provider2);
        assertNotEquals(provider2,provider1);
    }
}