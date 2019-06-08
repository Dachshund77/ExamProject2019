package Domain.DisplayObjects;

import java.util.Objects;

public class DisplayEmployee {

    private Integer employeeID;
    private String employeeFirstName;
    private String employeeLastName;
    private String cprNr;
    private String email;
    private String phoneNr;

    public DisplayEmployee(Integer employeeID, String employeeFirstName, String employeeLastName, String cprNr, String email, String phoneNr) {
        this.employeeID = employeeID;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.cprNr = cprNr;
        this.email = email;
        this.phoneNr = phoneNr;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getCprNr() {
        return cprNr;
    }

    public void setCprNr(String cprNr) {
        this.cprNr = cprNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof DisplayEmployee)) {
            return false;
        }
        DisplayEmployee other = (DisplayEmployee) obj;
        if (this == other){
            return true;
        }
        //Test all fields
        if (!Objects.equals(this.employeeID,other.employeeID)){
            return false;
        }
        if (!Objects.equals(this.employeeFirstName,other.employeeFirstName)){
            return false;
        }
        if (!Objects.equals(this.employeeLastName,other.employeeLastName)){
            return false;
        }
        if (!Objects.equals(this.cprNr,other.cprNr)){
            return false;
        }
        if (!Objects.equals(this.email,other.email)){
            return false;
        }
        return Objects.equals(this.phoneNr, other.phoneNr);
    }
}
