package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Company {

    private SimpleIntegerProperty companyID;
    private SimpleStringProperty cvrNr;
    private SimpleStringProperty companyName;
    private ArrayList<Consultation> consultations;
    private ArrayList<Education> educationList;

    public Company(Integer companyID, String cvrNr, String companyName, ArrayList<Consultation> consultations, ArrayList<Education> educationList) {
        this.companyID = new SimpleIntegerProperty(companyID);
        this.cvrNr = new SimpleStringProperty(cvrNr);
        this.companyName = new SimpleStringProperty(companyName);
        this.consultations = consultations;
        this.educationList = educationList;
    }

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public Company(ResultSet rs) throws SQLException {
        this.companyID = new SimpleIntegerProperty(rs.getInt("fld_CompanyID"));
        this.cvrNr = new SimpleStringProperty(rs.getString("fld_CvrNr"));
        this.companyName = new SimpleStringProperty("fld_CompanyName");
    }

}
