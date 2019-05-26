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

    public Education getEducation() {
        return education;
    }

    public LocalDate getDateFinished() {
        return dateFinished;
    }
}
