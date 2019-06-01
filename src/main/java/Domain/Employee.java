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
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.cprNr = cprNr;
        this.eMail = eMail;
        this.phoneNr = phoneNr;
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

    public static boolean isValidEmployeeID(Integer employeeID) {
        return employeeID == null || employeeID >= 1;
    }

    public static boolean isValidEmployeeID(String employeeID){ return employeeID == null || employeeID.equals("");}

    public static String employeeIDInvalidCause(Integer employeeID) {
        if ( employeeID != null && employeeID < 0)
        {
            return "Employee ID may no be negative!";
        }
        return null;
    }

    public static String employeeIDInvalidCause(String employeeID){
        if ( employeeID != null && employeeID.equals(""))
        {
            return "Employee ID may no be empty!";
        }
        return null;
    }

    public static boolean isValidEmployeeFirstName(String employeeFirstName) {
        return employeeFirstName == null || employeeFirstName.length() <= 30 && employeeFirstName.length() > 0;
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
        return employeeLastName == null || employeeLastName.length() <= 30 && employeeLastName.length() > 0;
    }

    public static String employeeLastNameInvalidCause(String employeeLastName) {
        if (employeeLastName != null) {
            if (employeeLastName.length() > 30){
                return "Last Name must be 30 or less letters!";
            }
        }
        return null;
    }

    public static boolean isValidCprNr(String CprNr) {
        return CprNr.matches("[0-9]+") && CprNr.length() == 10;
    }

    public static String cprNrInvalidCause(String CprNr) {
        if (CprNr != null) {
            if (CprNr.length() != 10){
                return "CprNr must be 10 letters long";
            } if (!CprNr.matches("[0-9]+")){
                return "CprNr may not contain letters!";
            }
        }
        return null;
    }

    public static boolean isValidEmail(String email) {
        return email == null || (email.contains("@") && email.length() <= 30 && email.length() > 0);
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
        return phoneNr == null || phoneNr.matches("[0-9+]+") && phoneNr.length() <= 20;
    }

    public static String PhoneNumberInvalidCause(String phoneNr) {
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
