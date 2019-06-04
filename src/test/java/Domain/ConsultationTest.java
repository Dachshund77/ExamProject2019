package Domain;

import jdk.vm.ci.meta.Local;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ConsultationTest {

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
}