package Domain;

import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FinishedEducation {
    private final Integer finishedEducationID;
    private Education education;
    private LocalDate dateFinished;

    public FinishedEducation(Integer finishedEducationID, Education education, LocalDate dateFinished) {
        this.finishedEducationID = finishedEducationID;
        this.education = education;
        this.dateFinished = dateFinished;
    }

    public Integer getFinishedEducationID() {
       return finishedEducationID;
    }

    public Education getEducation() {
        return education;
    }

    public LocalDate getDateFinished() {
        return dateFinished;
    }

    /**
     * checks if finishedEducationID arent NULL and negative
     *
     * @param finishedEducationID
     * @return
     */
    public static boolean isValidFinishedEducationID(Integer finishedEducationID) {
        return finishedEducationIDInvalidCause(finishedEducationID) == null;
    }

    public static boolean isValidFinishedEducationID(String finishedEducationID) {
        return finishedEducationIDInvalidCause(finishedEducationID) == null;
    }

    /**
     * Throws an error if:
     * finishedEducationID = NULL
     * finshedEducationID = Negative
     *
     * @return
     */
    public static String finishedEducationIDInvalidCause(Integer finishedEducationID) {
         if (finishedEducationID != null && finishedEducationID < 0) {
            return "FinishedEducationID cant be negative";
        }
        return null;
    }

    /**
     * Throws an error if:
     * finishedEducationID = NULL
     * finshedEducationID = Negative
     *
     * @return
     */
    public static String finishedEducationIDInvalidCause(String finishedEducationID) {
        try{
            return finishedEducationIDInvalidCause(Integer.parseInt(finishedEducationID));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }





}
