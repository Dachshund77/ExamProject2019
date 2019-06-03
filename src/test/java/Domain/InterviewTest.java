package Domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class InterviewTest {

    @Test
    public void isValidInterviewID() {
        //True
        assertTrue(Interview.isValidInterviewID(12)); //Normal number
        assertTrue(Interview.isValidInterviewID("12")); // normal number as string
        assertTrue(Interview.isValidInterviewID((String)null)); //May be null
        assertTrue(Interview.isValidInterviewID((Integer) null)); //May be null if new

        //False
        assertFalse(Interview.isValidInterviewID(0));
        assertFalse(Interview.isValidInterviewID("0"));
        assertFalse(Interview.isValidInterviewID("   ")); //may not be empty string
        assertFalse(Interview.isValidInterviewID(-12));
        assertFalse(Interview.isValidInterviewID("-12"));
        assertFalse(Interview.isValidInterviewID("12ab")); //may not contain numbers
    }

    @Test
    public void isValidInterviewName() {
        //True
        assertTrue(Interview.isValidInterviewName("A Consultation")); //valid name

        //False
        assertFalse(Interview.isValidInterviewName("   "));
        assertFalse(Interview.isValidInterviewName(null));
        //Way to long
        assertFalse(Interview.isValidInterviewName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }

    @Test
    public void isValidProductUnderstanding() {
        //True
        assertTrue(Interview.isValidProductUnderstanding(3)); //Normal number
        assertTrue(Interview.isValidProductUnderstanding("3")); // normal number as string
        assertTrue(Interview.isValidProductUnderstanding((String)null)); //May be null
        assertTrue(Interview.isValidProductUnderstanding((Integer) null)); //May be null if new

        //False
        assertFalse(Interview.isValidProductUnderstanding(0));
        assertFalse(Interview.isValidProductUnderstanding("0"));
        assertFalse(Interview.isValidProductUnderstanding(6));
        assertFalse(Interview.isValidProductUnderstanding("6"));
        assertFalse(Interview.isValidProductUnderstanding("   ")); //may not be empty string
        assertFalse(Interview.isValidProductUnderstanding(-12));
        assertFalse(Interview.isValidProductUnderstanding("-12"));
        assertFalse(Interview.isValidProductUnderstanding("12ab")); //may not contain numbers
    }


    @Test
    public void isValidProblemUnderstanding() {
        //True
        assertTrue(Interview.isValidProblemUnderstanding(3)); //Normal number
        assertTrue(Interview.isValidProblemUnderstanding("3")); // normal number as string
        assertTrue(Interview.isValidProblemUnderstanding((String)null)); //May be null
        assertTrue(Interview.isValidProblemUnderstanding((Integer) null)); //May be null if new

        //False
        assertFalse(Interview.isValidProblemUnderstanding(0));
        assertFalse(Interview.isValidProblemUnderstanding("0"));
        assertFalse(Interview.isValidProblemUnderstanding(6));
        assertFalse(Interview.isValidProblemUnderstanding("6"));
        assertFalse(Interview.isValidProblemUnderstanding("   ")); //may not be empty string
        assertFalse(Interview.isValidProblemUnderstanding(-12));
        assertFalse(Interview.isValidProblemUnderstanding("-12"));
        assertFalse(Interview.isValidProblemUnderstanding("12ab")); //may not contain numbers
    }



    @Test
    public void isValidFlexibility() {
        //True
        assertTrue(Interview.isValidFlexibility(3)); //Normal number
        assertTrue(Interview.isValidFlexibility("3")); // normal number as string
        assertTrue(Interview.isValidFlexibility((String)null)); //May be null
        assertTrue(Interview.isValidFlexibility((Integer) null)); //May be null if new

        //False
        assertFalse(Interview.isValidFlexibility(0));
        assertFalse(Interview.isValidFlexibility("0"));
        assertFalse(Interview.isValidFlexibility(6));
        assertFalse(Interview.isValidFlexibility("6"));
        assertFalse(Interview.isValidFlexibility("   ")); //may not be empty string
        assertFalse(Interview.isValidFlexibility(-12));
        assertFalse(Interview.isValidFlexibility("-12"));
        assertFalse(Interview.isValidFlexibility("12ab")); //may not contain numbers
    }


    @Test
    public void isValidQualityAwareness() {
        //True
        assertTrue(Interview.isValidQualityAwareness(3)); //Normal number
        assertTrue(Interview.isValidQualityAwareness("3")); // normal number as string
        assertTrue(Interview.isValidQualityAwareness((String)null)); //May be null
        assertTrue(Interview.isValidQualityAwareness((Integer) null)); //May be null if new

        //False
        assertFalse(Interview.isValidQualityAwareness(0));
        assertFalse(Interview.isValidQualityAwareness("0"));
        assertFalse(Interview.isValidQualityAwareness(6));
        assertFalse(Interview.isValidQualityAwareness("6"));
        assertFalse(Interview.isValidQualityAwareness("   ")); //may not be empty string
        assertFalse(Interview.isValidQualityAwareness(-12));
        assertFalse(Interview.isValidQualityAwareness("-12"));
        assertFalse(Interview.isValidQualityAwareness("12ab")); //may not contain numbers
    }

    @Test
    public void isValidCooperation() {
        //True
        assertTrue(Interview.isValidCooperation(3)); //Normal number
        assertTrue(Interview.isValidCooperation("3")); // normal number as string
        assertTrue(Interview.isValidCooperation((String)null)); //May be null
        assertTrue(Interview.isValidCooperation((Integer) null)); //May be null if new

        //False
        assertFalse(Interview.isValidCooperation(0));
        assertFalse(Interview.isValidCooperation("0"));
        assertFalse(Interview.isValidCooperation(6));
        assertFalse(Interview.isValidCooperation("6"));
        assertFalse(Interview.isValidCooperation("   ")); //may not be empty string
        assertFalse(Interview.isValidCooperation(-12));
        assertFalse(Interview.isValidCooperation("-12"));
        assertFalse(Interview.isValidCooperation("12ab")); //may not contain numbers
    }


}