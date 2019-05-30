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

    public boolean isValidEmployeeID(Integer employeeID)
    {
        if(employeeID >= 1) {
            return true;
        }
        return false;
    }
    public String employeeIDInvalidCause(Integer employeeID)
    {
        if(!isValidEmployeeID(employeeID))
        {
            String InvalidCause = "";
            return InvalidCause;
        }
        return null;
    }
    public boolean isValidEmployeeFirstName(String employeeFirstName)
    {
        if(employeeFirstName == null || employeeFirstName.length() <= 30 && employeeFirstName.length() > 0)
        {
            return true;
        }
        return false;
    }
    public String employeeFirstNameInvalidCause (String employeeFirstName)
    {
        if(!isValidEmployeeFirstName(employeeFirstName))
        {
            String InvalidCause = "";
            return InvalidCause;
        }
            return null;
    }
    public boolean isValidEmployeeLastName(String employeeLastName)
    {
        if(employeeLastName == null || employeeLastName.length() <= 30 && employeeLastName.length() > 0)
        {
            return true;
        }
        return false;
    }
    public String employeeLastNameInvalidCause (String employeeLastName)
    {
        if(!isValidEmployeeLastName(employeeLastName))
        {
            String InvalidCause = "";
            return InvalidCause;
        }
        return null;
    }
    public boolean isValidCprNr(String CprNr)
    {
        if(CprNr.matches("[0-9]+") && CprNr.length() == 10)
        {
            return true;
        }
        return false;
    }
    public String cprNrInvalidCause(String CprNr)
    {
        if(!isValidCprNr(CprNr))
        {
            String InvalidCause = "";
            return InvalidCause;
        }
        return null;
    }
    public boolean isValidEmail(String email)
    {
        if(email == null || email.contains("@") && email.length() <= 30 && email.length() > 0)
        {
            return true;
        }
        return false;
    }
    public String eMailInvalidCause (String email)
    {
        if(!isValidEmail(email))
        {
            String InvalidCause = "";
            return InvalidCause;
        }
        return null;
    }
    public boolean isValidPhoneNr (String phoneNr)
    {
        if(phoneNr == null || phoneNr.matches("[0-9+]+") && phoneNr.length() <= 20)
        {
            return true;
        }
        return false;
    }
    public String PhoneNumberInvalidCause (String phoneNr)
    {
        if(!isValidPhoneNr(phoneNr))
        {
            String InvalidCause = "";
            return InvalidCause;
        }
        return null;
    }
}
