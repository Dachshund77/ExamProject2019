package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EducationList {
    private SimpleIntegerProperty educationListName;
    private ArrayList<Education> education;

    public EducationList(Integer educationListName, ArrayList<Education> education) {
        this.educationListName = new SimpleIntegerProperty(educationListName);
        this.education = education;
    }

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public EducationList(ResultSet rs) throws SQLException {
        this.educationListName = new SimpleIntegerProperty(rs.getInt("fld_EducationListID"));
    }
}
