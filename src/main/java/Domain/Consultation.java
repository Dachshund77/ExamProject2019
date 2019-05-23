package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Consultation {

    private SimpleIntegerProperty consultationID;
    private SimpleStringProperty consultationName;
    private Date startDate;
    private Date endDate;
    private ArrayList<Employee> employees;

    public Consultation(Integer consultationID, String consultationName, Date startDate, Date endDate, ArrayList<Employee> employees) {
        this.consultationID = new SimpleIntegerProperty(consultationID);
        this.consultationName = new SimpleStringProperty(consultationName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.employees = employees;
    }

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public Consultation(ResultSet rs) throws SQLException {
        this.consultationID = new SimpleIntegerProperty(rs.getInt("fld_ConsultationID"));
        this.consultationName = new SimpleStringProperty(rs.getString("fld_ConsultationName"));
        this.startDate = rs.getDate("fld_StartDate");
        this.endDate = rs.getDate("fld_EndDate");
    }

    public int getConsultationID() {
        return consultationID.get();
    }

    public SimpleIntegerProperty consultationIDProperty() {
        return consultationID;
    }

    public String getConsultationName() {
        return consultationName.get();
    }

    public SimpleStringProperty consultationNameProperty() {
        return consultationName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
