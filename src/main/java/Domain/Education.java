package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Education {
    private SimpleIntegerProperty amuNr;
    private SimpleStringProperty educationName;
    private SimpleStringProperty description;
    private SimpleIntegerProperty noOfDays;
    private ArrayList<Date> dates;
    private Provider provider;

    public Education(Integer amuNr, String educationName, String description, Integer noOfDays, ArrayList<Date> dates, Provider provider) {
        this.amuNr = new SimpleIntegerProperty(amuNr);
        this.educationName = new SimpleStringProperty(educationName);
        this.description = new SimpleStringProperty(description);
        this.noOfDays = new SimpleIntegerProperty(noOfDays);
        this.dates = dates;
        this.provider = provider;
    }

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public Education(ResultSet rs) throws SQLException {
        this.amuNr = new SimpleIntegerProperty(rs.getInt("fld_AmuNR"));
        this.educationName = new SimpleStringProperty(rs.getString("fld_EducationName"));
        this.description = new SimpleStringProperty(rs.getString("fld_Description"));
        this.noOfDays = new SimpleIntegerProperty(rs.getInt("fld_NoOfFays"));
    }
}
