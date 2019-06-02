package Domain;

import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FinishedEducation {
    private SimpleIntegerProperty finishedEducationID;
    private Education education;
    private LocalDate dateFinished;

    public FinishedEducation(Integer finishedEducationID, Education education, LocalDate dateFinished) {
        if (finishedEducationID != null) {
            this.finishedEducationID = new SimpleIntegerProperty(finishedEducationID);
        } else {
            this.finishedEducationID = null;
        }
        this.education = education;
        this.dateFinished = dateFinished;
    }

    public Integer getFinishedEducationID() {
        if (finishedEducationID == null) {
            return null;
        }
        return finishedEducationID.get();
    }

    public String getFinishedEducationID(String finishedEducationID){
        return finishedEducationID;
    }


    /**
     * checks if finishedEducationID arent NULL and negative
     *
     * @param finishedEducationID
     * @return
     */
    public static boolean isValidFinishedEducationID(Integer finishedEducationID) {
        return finishedEducationID != null && finishedEducationID > 0;
    }

    /**
     * Throws an error if:
     * finishedEducationID = NULL
     * finshedEducationID = Negative
     *
     * @return
     */
    public static String finishedEducationIDInvalidCause(Integer finishedEducationID) {
        if (finishedEducationID == null) {
            return "FinishedEducationID cant be null";
        } else if (finishedEducationID < 0) {
            return "FinieshedEducationID cant be negative";
        }
        return null;
    }

    /**
     * checks if the AMU Nr is valid
     *
     * @return
     */
    public static boolean isValidEducation(Integer AmuNr) {
        return AmuNr != null && AmuNr > 0;
    }

    /**
     * Throws an error if:
     * AMU Nr = NULL
     * AMU Nr = Negative
     *
     * @param AmuNr
     * @return
     */
    public String invalidEducationCause(Integer AmuNr) {
        if (AmuNr == null) {
            return "AMU Nr cant be null";
        } else if (AmuNr < 0) {
            return "AMU Nr cant be negative";
        }
        return null;
    }


    public Education getEducation() {
        return education;
    }

    public LocalDate getDateFinished() {
        return dateFinished;
    }
}
