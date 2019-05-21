package Domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Employee { //TODO SIMPLE PROPERTY VS PROPERTY?
    private SimpleIntegerProperty employeeID;
    private SimpleStringProperty employeeFirstName;
    private SimpleStringProperty employeeLastName;
    private SimpleStringProperty cprNr;
    private SimpleStringProperty eMail;
    private SimpleStringProperty phoneNr;

    public Employee(Integer employeeID, String employeeFirstName, String employeeLastName, String cprNr, String eMail, SimpleStringProperty phoneNr) {
        this.employeeID = new SimpleIntegerProperty(employeeID);
        this.employeeFirstName = new SimpleStringProperty(employeeFirstName);
        this.employeeLastName = new SimpleStringProperty(employeeLastName);
        this.cprNr = new SimpleStringProperty(cprNr);
        this.eMail = new SimpleStringProperty(eMail);
        this.phoneNr = phoneNr;
    }
}
