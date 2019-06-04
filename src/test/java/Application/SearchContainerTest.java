package Application;

import Domain.*;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class SearchContainerTest {

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

    @Test
    public void isValidConsultationID() {
        //True
        assertTrue(Consultation.isValidConsultationID(12)); //Normal number
        assertTrue(Consultation.isValidConsultationID("12")); // normal number as string
        assertTrue(Consultation.isValidConsultationID((String)null)); //May be null
        assertTrue(Consultation.isValidConsultationID((Integer) null)); //May be null if new
        assertTrue(Consultation.isValidConsultationID("   ")); //if it may be null it may be empty

        //False
        assertFalse(Consultation.isValidConsultationID(0));
        assertFalse(Consultation.isValidConsultationID("0"));
        assertFalse(Consultation.isValidConsultationID(-12));
        assertFalse(Consultation.isValidConsultationID("-12"));
        assertFalse(Consultation.isValidConsultationID("12ab")); //may not contain numbers
    }

    @Test
    public void isValidConsultationName() {
        //True
        assertTrue(Consultation.isValidConsultationName("aafsaa")); //Normal name

        //False
        //To long
        assertFalse(Consultation.isValidConsultationName("1234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as11234fsasfaf1234512521as1")); //To long
        assertFalse(Consultation.isValidConsultationName(null));
        assertFalse(Consultation.isValidConsultationName("     "));
    }

    @Test
    public void isValidDate() {
        LocalDate first = LocalDate.of(2000,10,10);
        LocalDate same = LocalDate.of(2000,10,10);
        LocalDate second = LocalDate.of(2010,10,10);

        //True
        assertTrue(Consultation.isValidDate(first,second));
        assertTrue(Consultation.isValidDate(first,same));

        //False
        assertFalse(Consultation.isValidDate(null,second));
        assertFalse(Consultation.isValidDate(first,null));
        assertFalse(Consultation.isValidDate(null,null));
        assertFalse(Consultation.isValidDate(second,first)); //inverted
    }

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
    public void isValidInterviewID() {
        //True
        assertTrue(Interview.isValidInterviewID(12)); //Normal number
        assertTrue(Interview.isValidInterviewID("12")); // normal number as string
        assertTrue(Interview.isValidInterviewID((String)null)); //May be null
        assertTrue(Interview.isValidInterviewID((Integer) null)); //May be null if new
        assertTrue(Interview.isValidInterviewID("   ")); //If it may be null it may be empty

        //False
        assertFalse(Interview.isValidInterviewID(0));
        assertFalse(Interview.isValidInterviewID("0"));
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
}