package Domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Employee {
    private SimpleIntegerProperty employeeID;
    private SimpleStringProperty employeeFirstName;
    private SimpleStringProperty employeeLastName;
    private SimpleStringProperty cprNr;
    private SimpleStringProperty eMail;
    private SimpleStringProperty phoneNr;
    private ArrayList<Interview> interviews;

    public Employee(Integer employeeID, String employeeFirstName, String employeeLastName, String cprNr, String eMail, String phoneNr, ArrayList<Interview> interviews) {
        if(employeeID != null)
        this.employeeID = new SimpleIntegerProperty(employeeID);
        this.employeeFirstName = new SimpleStringProperty(employeeFirstName);
        this.employeeLastName = new SimpleStringProperty(employeeLastName);
        this.cprNr = new SimpleStringProperty(cprNr);
        this.eMail = new SimpleStringProperty(eMail);
        this.phoneNr = new SimpleStringProperty(phoneNr);
        this.interviews = interviews;
    }

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public Employee(ResultSet rs) throws SQLException {
        this.employeeID = new SimpleIntegerProperty(rs.getInt("fld_EmployeeID"));
        this.employeeFirstName = new SimpleStringProperty(rs.getString("fld_EmployeeFirstName"));
        this.employeeLastName = new SimpleStringProperty(rs.getString("fld_EmployeeLastName"));
        this.cprNr = new SimpleStringProperty(rs.getString("fld_CprNr"));
        this.eMail = new SimpleStringProperty(rs.getString("fld_EMail"));
        this.phoneNr = new SimpleStringProperty(rs.getString("fld_PhoneNr"));
    }

    public Integer getEmployeeId()
    {
        if(employeeID == null)
        return null;

        return employeeID.get();
    }

    public int getEmployeeID() {
        return employeeID.get();
    }

    public SimpleIntegerProperty employeeIDProperty() {
        return employeeID;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName.get();
    }

    public SimpleStringProperty employeeFirstNameProperty() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName.get();
    }

    public SimpleStringProperty employeeLastNameProperty() {
        return employeeLastName;
    }

    public String getCprNr() {
        return cprNr.get();
    }

    public SimpleStringProperty cprNrProperty() {
        return cprNr;
    }

    public String geteMail() {
        return eMail.get();
    }

    public SimpleStringProperty eMailProperty() {
        return eMail;
    }

    public String getPhoneNr() {
        return phoneNr.get();
    }

    public SimpleStringProperty phoneNrProperty() {
        return phoneNr;
    }

    public ArrayList<Interview> getInterviews() {
        return interviews;
    }
}
