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
        if (finishedEducationID == null){
            return null;
        }
        return finishedEducationID.get();
    }


    public boolean isValidFinishedEducationID(){
        if (finishedEducationID.get() < 0 ){
            return false;
        }
        return true;
    }

    public String finishedEducationIDInvalidCause(){
        if (!isValidFinishedEducationID()){
            String cause = "ID is less than 0";
            return cause;
        }
        return
                finishedEducationIDInvalidCause();
    }

    public boolean isValidEducation(){
        if (education.getAmuNr() < 0 ){
            return false;
        }
        return true;
    }

    public String invalidEducationCause(){
        if (!isValidEducation()){
            String cause = "AMU number is less than 0";
            return cause;
        }
        return
                invalidEducationCause();
    }
    


    public Education getEducation() {
        return education;
    }

    public LocalDate getDateFinished() {
        return dateFinished;
    }
}
