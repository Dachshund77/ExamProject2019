package Domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Employee {
    private SimpleIntegerProperty employeeID;
    private SimpleStringProperty employeeFirstName;
    private SimpleStringProperty employeeLastName;
    private SimpleStringProperty cprNr;
    private SimpleStringProperty eMail;
    private SimpleStringProperty phoneNr;
    private ArrayList<Interview> interviews;

    public Employee(Integer employeeID, String employeeFirstName, String employeeLastName, String cprNr, String eMail, String phoneNr, ArrayList<Interview> interviews) {
        //employee ID
        if (employeeID != null) {
            this.employeeID = new SimpleIntegerProperty(employeeID);
        } else {
            this.employeeID = null;
        }

        this.employeeFirstName = new SimpleStringProperty(employeeFirstName);
        this.employeeLastName = new SimpleStringProperty(employeeLastName);
        this.cprNr = new SimpleStringProperty(cprNr);
        this.eMail = new SimpleStringProperty(eMail);
        this.phoneNr = new SimpleStringProperty(phoneNr);
        this.interviews = Objects.requireNonNullElseGet(interviews,ArrayList::new);
    }

    public Integer getEmployeeId() {
        if (employeeID == null)
            return null;
        return employeeID.get();
    }

    public String getEmployeeFirstName() {
        if (employeeFirstName.get().equals("")){
            return null;
        }
        return employeeFirstName.get();
    }

    public SimpleStringProperty employeeFirstNameProperty() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        if(employeeLastName.get().equals("")){
            return null;
        }
        return employeeLastName.get();
    }

    public SimpleStringProperty employeeLastNameProperty() {
        return employeeLastName;
    }

    public String getCprNr() {
        if (cprNr.get().equals("")){
            return null;
        }
        return cprNr.get();
    }

    public SimpleStringProperty cprNrProperty() {
        return cprNr;
    }

    public String geteMail() {
        if (eMail.get().equals("")){
            return null;
        }
        return eMail.get();
    }

    public SimpleStringProperty eMailProperty() {
        return eMail;
    }

    public String getPhoneNr() {
        if (phoneNr.get().equals("")){
            return null;
        }
        return phoneNr.get();
    }

    public SimpleStringProperty phoneNrProperty() {
        return phoneNr;
    }

    public ArrayList<Interview> getInterviews() {
        return interviews;
    }
}
