package Domain;

import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinishedEducation {
    private SimpleIntegerProperty finishedEducationID;
    private Education education;
    private Date dateFinished;

    public FinishedEducation(Integer finishedEducationID, Education education, Date dateFinished) {
        if (finishedEducationID != null) {
            this.finishedEducationID = new SimpleIntegerProperty(finishedEducationID);
        } else {
            this.finishedEducationID = null;
        }
        this.education = education;
        this.dateFinished = dateFinished;
    }

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public FinishedEducation(ResultSet rs) throws SQLException {
        this.finishedEducationID = new SimpleIntegerProperty(rs.getInt("fld_FinishedEducationID"));
        this.dateFinished = rs.getDate("fld_FinishedDate");
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

    public Date getDateFinished() {
        return dateFinished;
    }
}
