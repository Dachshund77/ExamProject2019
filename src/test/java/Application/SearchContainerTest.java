package Application;

import Domain.*;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class SearchContainerTest {

    @Test
    public void isValidCompanyID() {
        //True
        assertTrue(SearchContainer.isValidCompanyID(12)); //Normal number
        assertTrue(SearchContainer.isValidCompanyID("12")); // normal number as string
        assertTrue(SearchContainer.isValidCompanyID((String)null)); //May be null
        assertTrue(SearchContainer.isValidCompanyID((Integer) null)); //May be null if new
        assertTrue(SearchContainer.isValidCompanyID("   ")); //If it may be null, it may also be empty

        //False
        assertFalse(SearchContainer.isValidCompanyID(0));
        assertFalse(SearchContainer.isValidCompanyID("0"));
        assertFalse(SearchContainer.isValidCompanyID(-12));
        assertFalse(SearchContainer.isValidCompanyID("-12"));
        assertFalse(SearchContainer.isValidCompanyID("12ab")); //may not contain numbers
    }

    @Test
    public void isValidCvrNr() {
        //True
        assertTrue(SearchContainer.isValidCvrNr("12345678")); //8 numbers
        assertTrue(SearchContainer.isValidCvrNr("123"));
        assertTrue(SearchContainer.isValidCvrNr(null));
        assertTrue(SearchContainer.isValidCvrNr("     "));

        //False
        assertFalse(SearchContainer.isValidCvrNr("123a1234")); //contain letters
        assertFalse(SearchContainer.isValidCvrNr("123412345125211")); //To long
    }

    @Test
    public void isValidCompanyName() {
        //True
        assertTrue(SearchContainer.isValidCompanyName("Math Inc")); //valid name
        assertTrue(SearchContainer.isValidCompanyName("   "));
        assertTrue(SearchContainer.isValidCompanyName(null));

        //False
        //Way to long
        assertFalse(SearchContainer.isValidCompanyName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }

    @Test
    public void isValidConsultationID() {
        //True
        assertTrue(SearchContainer.isValidConsultationID(12)); //Normal number
        assertTrue(SearchContainer.isValidConsultationID("12")); // normal number as string
        assertTrue(SearchContainer.isValidConsultationID((String)null)); //May be null
        assertTrue(SearchContainer.isValidConsultationID((Integer) null)); //May be null if new
        assertTrue(SearchContainer.isValidConsultationID("   ")); //if it may be null it may be empty

        //False
        assertFalse(SearchContainer.isValidConsultationID(0));
        assertFalse(SearchContainer.isValidConsultationID("0"));
        assertFalse(SearchContainer.isValidConsultationID(-12));
        assertFalse(SearchContainer.isValidConsultationID("-12"));
        assertFalse(SearchContainer.isValidConsultationID("12ab")); //may not contain numbers
    }

    @Test
    public void isValidConsultationName() {
        //True
        assertTrue(SearchContainer.isValidConsultationName("aafsaa")); //Normal name
        assertTrue(SearchContainer.isValidConsultationName(null));
        assertTrue(SearchContainer.isValidConsultationName("     "));

        //False
        //To long
        assertFalse(SearchContainer.isValidConsultationName("1234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as1")); //To long
    }

    @Test
    public void isValidDate() {
        LocalDate first = LocalDate.of(2000,10,10);
        LocalDate same = LocalDate.of(2000,10,10);
        LocalDate second = LocalDate.of(2010,10,10);

        //True
        assertTrue(SearchContainer.isValidDate(first,second));
        assertTrue(SearchContainer.isValidDate(first,same));
        assertTrue(SearchContainer.isValidDate(null,second));
        assertTrue(SearchContainer.isValidDate(first,null));
        assertTrue(SearchContainer.isValidDate(null,null));

        //False
        assertFalse(SearchContainer.isValidDate(second,first)); //inverted
    }

    @Test
    public void isValidAmuNr() {
        //True
        assertTrue(SearchContainer.isValidAmuNr(12)); //Normal number
        assertTrue(SearchContainer.isValidAmuNr("12")); // normal number as string
        assertTrue(SearchContainer.isValidAmuNr((String)null)); //May be null
        assertTrue(SearchContainer.isValidAmuNr((Integer) null)); //May be null if new
        assertTrue(SearchContainer.isValidAmuNr("   ")); //If it may be null it may be empty

        //False
        assertFalse(SearchContainer.isValidAmuNr(0));
        assertFalse(SearchContainer.isValidAmuNr("0"));
        assertFalse(SearchContainer.isValidAmuNr(-12));
        assertFalse(SearchContainer.isValidAmuNr("-12"));
        assertFalse(SearchContainer.isValidAmuNr("12ab")); //may not contain numbers
    }

    @Test
    public void isValidEducationName() {
        //True
        assertTrue(SearchContainer.isValidEducationName("Math Inc")); //valid name
        assertTrue(SearchContainer.isValidEducationName("   "));
        assertTrue(SearchContainer.isValidEducationName(null));

        //False
        //Way to long
        assertFalse(SearchContainer.isValidEducationName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }

    @Test
    public void isValidNoOfDays() {
        //True
        assertTrue(SearchContainer.isValidNoOfDays(12)); //Normal number
        assertTrue(SearchContainer.isValidNoOfDays("12")); // normal number as string
        assertTrue(SearchContainer.isValidNoOfDays((String)null)); //May not be null
        assertTrue(SearchContainer.isValidNoOfDays((Integer) null)); //May not be null if new
        assertTrue(SearchContainer.isValidNoOfDays("   ")); //If it may noy be null it may be empty

        //False
        assertFalse(SearchContainer.isValidNoOfDays(0));
        assertFalse(SearchContainer.isValidNoOfDays("0"));
        assertFalse(SearchContainer.isValidNoOfDays(-12));
        assertFalse(SearchContainer.isValidNoOfDays("-12"));
        assertFalse(SearchContainer.isValidNoOfDays("12ab")); //may not contain numbers
    }

    @Test
    public void isValidEmployeeID() {
        //True
        assertTrue(SearchContainer.isValidEmployeeID(12)); //Normal number
        assertTrue(SearchContainer.isValidEmployeeID("12")); // normal number as string
        assertTrue(SearchContainer.isValidEmployeeID((String)null)); //May be null
        assertTrue(SearchContainer.isValidEmployeeID((Integer) null)); //May be null if new
        assertTrue(SearchContainer.isValidEmployeeID("   ")); //If it may be null it may be empty

        //False
        assertFalse(SearchContainer.isValidEmployeeID(0));
        assertFalse(SearchContainer.isValidEmployeeID("0"));
        assertFalse(SearchContainer.isValidEmployeeID(-12));
        assertFalse(SearchContainer.isValidEmployeeID("-12"));
        assertFalse(SearchContainer.isValidEmployeeID("12ab")); //may not contain numbers
    }

    @Test
    public void isValidEmployeeFirstName() {
        //True
        assertTrue(SearchContainer.isValidEmployeeFirstName("Peter")); //valid name
        assertTrue(SearchContainer.isValidEmployeeFirstName("   ")); //May be empty
        assertTrue(SearchContainer.isValidEmployeeFirstName(null)); //may be null

        //False
        //Way to long
        assertFalse(SearchContainer.isValidEmployeeFirstName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }

    @Test
    public void isValidEmployeeLastName() {
        //True
        assertTrue(SearchContainer.isValidEmployeeLastName("Peter")); //valid name
        assertTrue(SearchContainer.isValidEmployeeLastName("   ")); //May be empty
        assertTrue(SearchContainer.isValidEmployeeLastName(null)); //may be null

        //False
        //Way to long
        assertFalse(SearchContainer.isValidEmployeeLastName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }

    @Test
    public void isValidCprNr() {
        assertTrue(SearchContainer.isValidCprNr("0707923865")); // right length
        assertTrue(SearchContainer.isValidCprNr("07078652")); // to short
        assertTrue(SearchContainer.isValidCprNr("    "));
        assertTrue(SearchContainer.isValidCprNr(null));

        assertFalse(SearchContainer.isValidCprNr("07079238652")); // to long
        assertFalse(SearchContainer.isValidCprNr("07079a3865")); // contain letters
    }

    @Test
    public void isValidEmail() {
        assertTrue(SearchContainer.isValidEmail("Sven@mail.com")); //Right and has @
        assertTrue(SearchContainer.isValidEmail("    "));
        assertTrue(SearchContainer.isValidEmail(null));
        assertTrue(SearchContainer.isValidEmail("svenmail.com")); //no @

        //Way to long an contains @
        assertFalse(SearchContainer.isValidEmail("abcdefg@hicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));

    }


    @Test
    public void isValidPhoneNr() {
        assertTrue(SearchContainer.isValidPhoneNr("123456")); //Valid length
        assertTrue(SearchContainer.isValidPhoneNr("    "));
        assertTrue(SearchContainer.isValidPhoneNr(null));

        //To long
        assertFalse(SearchContainer.isValidPhoneNr("21451515121451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515152145151515214515151521451515155"));
        assertFalse(SearchContainer.isValidPhoneNr("124a21")); //contain letters
        assertFalse(SearchContainer.isValidPhoneNr("-12421")); //negative number
    }

    @Test
    public void isValidInterviewID() {
        //True
        assertTrue(SearchContainer.isValidInterviewID(12)); //Normal number
        assertTrue(SearchContainer.isValidInterviewID("12")); // normal number as string
        assertTrue(SearchContainer.isValidInterviewID((String)null)); //May be null
        assertTrue(SearchContainer.isValidInterviewID((Integer) null)); //May be null if new
        assertTrue(SearchContainer.isValidInterviewID("   ")); //If it may be null it may be empty

        //False
        assertFalse(SearchContainer.isValidInterviewID(0));
        assertFalse(SearchContainer.isValidInterviewID("0"));
        assertFalse(SearchContainer.isValidInterviewID(-12));
        assertFalse(SearchContainer.isValidInterviewID("-12"));
        assertFalse(SearchContainer.isValidInterviewID("12ab")); //may not contain numbers
    }

    @Test
    public void isValidInterviewName() {
        //True
        assertTrue(SearchContainer.isValidInterviewName("A Consultation")); //valid name
        assertTrue(SearchContainer.isValidInterviewName("   "));
        assertTrue(SearchContainer.isValidInterviewName(null));

        //False
        //Way to long
        assertFalse(SearchContainer.isValidInterviewName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }


    @Test
    public void isValidProviderID() {
        //True
        assertTrue(SearchContainer.isValidProviderID(12)); //Normal number
        assertTrue(SearchContainer.isValidProviderID("12")); // normal number as string
        assertTrue(SearchContainer.isValidProviderID((String)null)); //May be null
        assertTrue(SearchContainer.isValidProviderID((Integer) null)); //May be null if new
        assertTrue(SearchContainer.isValidProviderID("   ")); //If it may be null it may be empty

        //False
        assertFalse(SearchContainer.isValidProviderID(0));
        assertFalse(SearchContainer.isValidProviderID("0"));
        assertFalse(SearchContainer.isValidProviderID(-12));
        assertFalse(SearchContainer.isValidProviderID("-12"));
        assertFalse(SearchContainer.isValidProviderID("12ab")); //may not contain numbers
    }

    @Test
    public void isValidProviderName() {
        //True
        assertTrue(SearchContainer.isValidProviderName("Providername")); //valid name
        assertTrue(SearchContainer.isValidProviderName("   "));
        assertTrue(SearchContainer.isValidProviderName(null));

        //False
        //Way to long
        assertFalse(SearchContainer.isValidProviderName("abcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghicabcdefghic"));
    }
}