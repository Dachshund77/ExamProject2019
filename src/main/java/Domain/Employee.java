package Domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Employee {
    private final Integer employeeID;
    private String employeeFirstName;
    private String employeeLastName;
    private String cprNr;
    private String eMail;
    private String phoneNr;
    private ArrayList<Interview> interviews;

    public Employee(Integer employeeID, String employeeFirstName, String employeeLastName, String cprNr, String eMail, String phoneNr, ArrayList<Interview> interviews) {
        //employee ID
        this.employeeID = employeeID;
        setEmployeeFirstName(employeeFirstName);
        setEmployeeLastName(employeeLastName);
        setCprNr(cprNr);
        seteMail(eMail);
        setPhoneNr(phoneNr);
        this.interviews = Objects.requireNonNullElseGet(interviews, ArrayList::new);
    }


    public Integer getEmployeeId() {
        return employeeID;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public String getCprNr() {
        return cprNr;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public ArrayList<Interview> getInterviews() {
        return interviews;
    }

    /**
     * Converts empty String to null.
     * @param employeeFirstName new employee first name.
     */
    public void setEmployeeFirstName(String employeeFirstName) {
        if (employeeFirstName.trim().isEmpty()){
            this.employeeFirstName = null;
        }else {
            this.employeeFirstName = employeeFirstName;
        }
    }

    /**
     * converts empty String to null.
     * @param employeeLastName new employee last name.
     */
    public void setEmployeeLastName(String employeeLastName) {
        if (employeeLastName.trim().isEmpty()){
            this.employeeLastName = null;
        }else {
            this.employeeLastName = employeeLastName;
        }
    }

    /**
     * Converts empty String to null.
     * @param cprNr new cprNr
     */
    public void setCprNr(String cprNr) {
        if (cprNr.trim().isEmpty()){
            this.cprNr = null;
        } else {
            this.cprNr = cprNr;
        }
    }

    /**
     * Converts empty String to null.
     * @param eMail new email.
     */
    public void seteMail(String eMail) {
        if (eMail.trim().isEmpty()){
            this.eMail = null;
        } else {
            this.eMail = eMail;
        }
    }

    /**
     * Converts empty String to null.
     * @param phoneNr new Phone nr.
     */
    public void setPhoneNr(String phoneNr) {
        if (phoneNr.trim().isEmpty()){
            this.phoneNr = null;
        } else {
            this.phoneNr = phoneNr;
        }
    }

    public static boolean isValidEmployeeID(Integer employeeID) {
        return employeeIDInvalidCause(employeeID) == null;
    }

    public static boolean isValidEmployeeID(String employeeID){ return employeeIDInvalidCause(employeeID)==null;}

    public static String employeeIDInvalidCause(Integer employeeID) {
        if ( employeeID != null && employeeID < 0)
        {
            return "Employee ID may no be negative!";
        }
        return null;
    }

    public static String employeeIDInvalidCause(String employeeID){
        try{
            return employeeIDInvalidCause(Integer.parseInt(employeeID));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    public static boolean isValidEmployeeFirstName(String employeeFirstName) {
        return employeeFirstNameInvalidCause(employeeFirstName)==null;
    }

    public static String employeeFirstNameInvalidCause(String employeeFirstName) {
        if (employeeFirstName != null) {
            if (employeeFirstName.length() > 30) {
                return "First Name must be 30 or less letters!";
            }
        }
        return null;
    }

    public static boolean isValidEmployeeLastName(String employeeLastName) {
        return employeeLastNameInvalidCause(employeeLastName) == null;
    }

    public static String employeeLastNameInvalidCause(String employeeLastName) {
        if (employeeLastName != null) {
            if (employeeLastName.length() > 30){
                return "Last Name must be 30 or less letters!";
            }
        }
        return null;
    }

    public static boolean isValidCprNr(String cprNr) {
        return cprNrInvalidCause(cprNr) ==null;
    }

    public static String cprNrInvalidCause(String cprNr) {
        if (cprNr != null) {
            if (cprNr.length() != 10){
                return "CprNr must be 10 letters long";
            } if (!cprNr.matches("[0-9]+")){
                return "CprNr may not contain letters!";
            }
        }
        return null;
    }

    public static boolean isValidEmail(String email) {
        return eMailInvalidCause(email) == null;
    }

    public static String eMailInvalidCause(String email) {
        if (email != null) {
            if (!email.contains("@")){
                return "Email must contain an @";
            } if (email.length() > 30){
                return "Email must be 30 or less letters!";
            }
        }
        return null;
    }

    public static boolean isValidPhoneNr(String phoneNr) {
        return phoneNumberInvalidCause(phoneNr) == null;
    }

    public static String phoneNumberInvalidCause(String phoneNr) {
        if (phoneNr != null) {
            if (!phoneNr.matches("[0-9]+")){
                return "Phone Number may not Contain letters!";
            } if (phoneNr.length() > 20){
                return "Phone Number must be 20 or less letters!";
            }
        }
        return null;
    }
}
