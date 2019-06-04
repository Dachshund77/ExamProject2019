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

    private static final int EMPLOYEE_FIRST_NAME_MAX_LENGTH = 30;
    private static final int EMPLOYEE_LAST_NAME_MAX_LENGTH = 30;
    private static final int CPR_EXACT_LENGTH = 10;
    private static final int EMAIL_MAX_LENGTH = 30;
    private static final int PHONE_NR_MAX_LENGTH = 20;

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

    public static int getEmployeeFirstNameMaxLength() {
        return EMPLOYEE_FIRST_NAME_MAX_LENGTH;
    }

    public static int getEmployeeLastNameMaxLength() {
        return EMPLOYEE_LAST_NAME_MAX_LENGTH;
    }

    public static int getCprExactLength() {
        return CPR_EXACT_LENGTH;
    }

    public static int getEmailMaxLength() {
        return EMAIL_MAX_LENGTH;
    }

    public static int getPhoneNrMaxLength() {
        return PHONE_NR_MAX_LENGTH;
    }

    /**
     * Converts empty String to null.
     *
     * @param employeeFirstName new employee first name.
     */
    public void setEmployeeFirstName(String employeeFirstName) {
        if (employeeFirstName==null ||employeeFirstName.trim().isEmpty()) {
            this.employeeFirstName = null;
        } else {
            this.employeeFirstName = employeeFirstName;
        }
    }

    /**
     * converts empty String to null.
     *
     * @param employeeLastName new employee last name.
     */
    public void setEmployeeLastName(String employeeLastName) {
        if (employeeLastName == null ||employeeLastName.trim().isEmpty()) {
            this.employeeLastName = null;
        } else {
            this.employeeLastName = employeeLastName;
        }
    }

    /**
     * Converts empty String to null.
     *
     * @param cprNr new cprNr
     */
    public void setCprNr(String cprNr) {
        if (cprNr == null || cprNr.trim().isEmpty()) {
            this.cprNr = null;
        } else {
            this.cprNr = cprNr;
        }
    }

    /**
     * Converts empty String to null.
     *
     * @param eMail new email.
     */
    public void seteMail(String eMail) {
        if (eMail == null || eMail.trim().isEmpty()) {
            this.eMail = null;
        } else {
            this.eMail = eMail;
        }
    }

    /**
     * Converts empty String to null.
     *
     * @param phoneNr new Phone nr.
     */
    public void setPhoneNr(String phoneNr) {
        if (phoneNr == null || phoneNr.trim().isEmpty()) {
            this.phoneNr = null;
        } else {
            this.phoneNr = phoneNr;
        }
    }

    public static boolean isValidEmployeeID(Integer employeeID) {
        return employeeIDInvalidCause(employeeID) == null;
    }

    public static boolean isValidEmployeeID(String employeeID) {
        return employeeIDInvalidCause(employeeID) == null;
    }

    public static String employeeIDInvalidCause(Integer employeeID) {
        if (employeeID != null && employeeID <= 0) {
            return "Employee ID must be positive!";
        }
        return null;
    }

    public static String employeeIDInvalidCause(String employeeID) {
        if (employeeID == null) {
            return null;
        }
        if (employeeID.trim().isEmpty()) {
            return null;
        }
        try {
            return employeeIDInvalidCause(Integer.parseInt(employeeID));
        } catch (NumberFormatException e) {
            return "Must be a number!";
        }
    }

    public static boolean isValidEmployeeFirstName(String employeeFirstName) {
        return employeeFirstNameInvalidCause(employeeFirstName) == null;
    }

    public static String employeeFirstNameInvalidCause(String employeeFirstName) {
        if (employeeFirstName != null) {
            if (employeeFirstName.length() > EMPLOYEE_FIRST_NAME_MAX_LENGTH) {
                return "First Name must be "+EMPLOYEE_FIRST_NAME_MAX_LENGTH+" or less letters!";
            }
        }
        return null;
    }

    public static boolean isValidEmployeeLastName(String employeeLastName) {
        return employeeLastNameInvalidCause(employeeLastName) == null;
    }

    public static String employeeLastNameInvalidCause(String employeeLastName) {
        if (employeeLastName != null) {
            if (employeeLastName.length() > EMPLOYEE_LAST_NAME_MAX_LENGTH) {
                return "Last Name must be "+EMPLOYEE_LAST_NAME_MAX_LENGTH+" or less letters!";
            }
        }
        return null;
    }

    public static boolean isValidCprNr(String cprNr) {
        return cprNrInvalidCause(cprNr) == null;
    }

    public static String cprNrInvalidCause(String cprNr) {
        if (cprNr == null) {
            return "CprNr may not be null!";
        }
        if (cprNr.trim().isEmpty()) {
            return "CprNr may not be empty!";
        }
        if (cprNr.length() != CPR_EXACT_LENGTH) {
            return "CprNr must be "+CPR_EXACT_LENGTH+" letters long";
        }
        if (!cprNr.matches("[0-9]+")) {
            return "CprNr may not contain letters!";
        }
        return null;
    }

    public static boolean isValidEmail(String email) {
        return eMailInvalidCause(email) == null;
    }

    public static String eMailInvalidCause(String email) {
        if (email != null) {
            if (email.trim().isEmpty()) {
                return null;
            }
            if (!email.contains("@")) {
                return "Email must contain an @";
            }
            if (email.length() > EMAIL_MAX_LENGTH) {
                return "Email must be "+EMAIL_MAX_LENGTH+" or less letters!";
            }
        }
        return null;
    }

    public static boolean isValidPhoneNr(String phoneNr) {
        return phoneNumberInvalidCause(phoneNr) == null;
    }

    public static String phoneNumberInvalidCause(String phoneNr) {
        if (phoneNr != null) {
            if (phoneNr.trim().isEmpty()) {
                return null;
            }
            if (!phoneNr.matches("[0-9]+")) {
                return "Phone Number may not Contain letters!";
            }
            if (phoneNr.length() > PHONE_NR_MAX_LENGTH) {
                return "Phone Number must be "+PHONE_NR_MAX_LENGTH+" or less letters!";
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) obj;
        if (this == other){
            return true;
        }
        //Test all fields
        if (!this.employeeID.equals(other.employeeID)){
            return false;
        }
        if (!this.employeeFirstName.equals(other.employeeFirstName)){
            return false;
        }
        if (!this.employeeLastName.equals(other.employeeLastName)){
            return false;
        }
        if (!this.cprNr.equals(other.cprNr)){
            return false;
        }
        if (!this.eMail.equals(other.eMail)){
            return false;
        }
        if (!this.phoneNr.equals(other.phoneNr)){
            return false;
        }
        return this.interviews.equals(other.interviews);
    }
}
