package Domain;

import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

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
         if (finishedEducationID != null && finishedEducationID <= 0) {
            return "FinishedEducationID must be positive!";
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
        if (finishedEducationID == null){
            return null;
        }
        if (finishedEducationID.trim().isEmpty()){
            return null;
        }
        try{
            return finishedEducationIDInvalidCause(Integer.parseInt(finishedEducationID));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof FinishedEducation)) {
            return false;
        }
        FinishedEducation other = (FinishedEducation) obj;
        if (this == other){
            return true;
        }
        //Test all fields
        if (!Objects.equals(this.finishedEducationID,other.finishedEducationID)){
            return false;
        }
        if (!Objects.equals(this.education,other.education)){
            return false;
        }
        return Objects.equals(this.dateFinished,other.dateFinished);
    }
}
