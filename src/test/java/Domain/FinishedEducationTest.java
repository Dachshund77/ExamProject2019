package Domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class FinishedEducationTest {

    @Test
    public void isValidFinishedEducationID() {
        assertTrue(FinishedEducation.isValidFinishedEducationID(12)); //Normal number
        assertTrue(FinishedEducation.isValidFinishedEducationID("12")); // normal number as string
        assertTrue(FinishedEducation.isValidFinishedEducationID((String)null)); //May be null
        assertTrue(FinishedEducation.isValidFinishedEducationID((Integer) null)); //May be null if new
        assertTrue(FinishedEducation.isValidFinishedEducationID("   ")); //If it may be null it may be empty

        //False
        assertFalse(FinishedEducation.isValidFinishedEducationID(0));
        assertFalse(FinishedEducation.isValidFinishedEducationID("0"));
        assertFalse(FinishedEducation.isValidFinishedEducationID(-12));
        assertFalse(FinishedEducation.isValidFinishedEducationID("-12"));
        assertFalse(FinishedEducation.isValidFinishedEducationID("12ab")); //may not contain numbers
    }


    @Test
    public void equalsNullPointerTest() {
        FinishedEducation finishedEducation1 = new FinishedEducation(null,null,null);
        FinishedEducation finishedEducation2 = new FinishedEducation(null,null,null);

        assertEquals(finishedEducation1,finishedEducation2);
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