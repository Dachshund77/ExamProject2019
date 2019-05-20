package Domain;

import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Date;

public class FinishedEducation {
    private SimpleIntegerProperty finishedEducationID;
    private Education education;
    private Date dateFinished;

    public FinishedEducation(Integer finishedEducationID, Education education, Date dateFinished) {
        this.finishedEducationID = new SimpleIntegerProperty(finishedEducationID);
        this.education = education;
        this.dateFinished = dateFinished;
    }
}
