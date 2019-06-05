package Domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void isValidEmployeeID() {
        //True
        assertTrue(Employee.isValidEmployeeID(12)); //Normal number
        assertTrue(Employee.isValidEmployeeID("12")); // normal number as string
        assertTrue(Employee.isValidEmployeeID((String)null)); //May be null
        assertTrue(Employee.isValidEmployeeID((Integer) null)); //May be null if new
        assertTrue(Employee.isValidEmployeeID("   ")); //If it may be null it may be empty

        //False
        assertFalse(Employee.isValidEmployeeID(0));
        assertFalse(Employee.isValidEmployeeID("0"));
        assertFalse(Employee.isValidEmployeeID(-12));
        assertFalse(Employee.isValidEmployeeID("-12"));
        assertFalse(Employee.isValidEmployeeID("12ab")); //may not contain numbers
    }

    @Test
    public void isValidEmployeeFirstName() {
        //True
        assertTrue(Employee.isValidEmployeeFirstName("Peter")); //valid name
        assertTrue(Employee.isValidEmployeeFirstName("   ")); //May be empty
        assertTrue(Employee.isValidEmployeeFirstName(null)); //may be null

        //False
        //Way to long
        assertFalse(Employee.isValidEmployeeFirstName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }

    @Test
    public void isValidEmployeeLastName() {
        //True
        assertTrue(Employee.isValidEmployeeLastName("Peter")); //valid name
        assertTrue(Employee.isValidEmployeeLastName("   ")); //May be empty
        assertTrue(Employee.isValidEmployeeLastName(null)); //may be null

        //False
        //Way to long
        assertFalse(Employee.isValidEmployeeLastName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }

    @Test
    public void isValidCprNr() {
        assertTrue(Employee.isValidCprNr("0707923865")); // right length

        assertFalse(Employee.isValidCprNr("07079238652")); // to long
        assertFalse(Employee.isValidCprNr("07078652")); // to short
        assertFalse(Employee.isValidCprNr("07079a3865")); // contain letters
        assertFalse(Employee.isValidCprNr("    "));
        assertFalse(Employee.isValidCprNr(null));
    }

    @Test
    public void isValidEmail() {
        assertTrue(Employee.isValidEmail("Sven@mail.com")); //Right and has @
        assertTrue(Employee.isValidEmail("    "));
        assertTrue(Employee.isValidEmail(null));

        //Way to long an contains @
        assertFalse(Employee.isValidEmail("abcdefg@hicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
        assertFalse(Employee.isValidEmail("svenmail.com")); //no @
    }

    @Test
    public void isValidPhoneNr() {
        assertTrue(Employee.isValidPhoneNr("123456")); //Valid length
        assertTrue(Employee.isValidPhoneNr("    "));
        assertTrue(Employee.isValidPhoneNr(null));

        //To long
        assertFalse(Employee.isValidPhoneNr("21451515121451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515155"));
        assertFalse(Employee.isValidPhoneNr("124a21")); //contain letters
        assertFalse(Employee.isValidPhoneNr("-12421")); //negative number
    }

    @Test
    public void equalsNullPointerTest() {
        Employee employee1 = new Employee(null,null,null,null,null,null,null);
        Employee employee2 = new Employee(null,null,null,null,null,null,null);

        assertEquals(employee1,employee2);
    }

    @Test
    public void equalsSameObjectTest(){

    }

    @Test
    public void equalsSameDomain(){

    }

    @Test
    public void equalsNotInstantOf(){

    }

    @Test
    public void equalsNullObjects(){

    }
}