package Application;

import Foundation.DB;
import Foundation.DbFacade;

import java.time.LocalDate;

/**
 * Helper class that is used to pass search queries to the Database.
 *
 * @see DbFacade
 */
public class SearchContainer { //TODO those getters should return null when it is empty
    //Company related
    private Integer companyID;
    private String cvrNr;
    private String companyName;
    //Consultation related
    private Integer consultationID;
    private String consultationName;
    private LocalDate consultationMinDate;
    private LocalDate consultationMaxDate;
    //Employee related
    private Integer employeeID;
    private String employeeFirstName;
    private String employeeLastName;
    private String cprNr;
    private String email;
    private String phoneNr;
    //Interview related
    private Integer interviewID;
    private String interviewName;
    // Education related
    private Integer AmuNr;
    private String educationName;
    private Integer educationNoOfDays;
    private LocalDate educationMinDate;
    private LocalDate educationMaxDate;
    //Provider related
    private Integer providerID;
    private String providerName;

    /**
     * Constructs an empty searchContainer.
     */
    public SearchContainer() {
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getCvrNr() {
        return cvrNr;
    }

    public void setCvrNr(String cvrNr) {
        this.cvrNr = cvrNr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getConsultationID() {
        return consultationID;
    }

    public void setConsultationID(Integer consultationID) {
        this.consultationID = consultationID;
    }

    public String getConsultationName() {
        return consultationName;
    }

    public void setConsultationName(String consultationName) {
        this.consultationName = consultationName;
    }

    public LocalDate getConsultationMinDate() {
        return consultationMinDate;
    }

    public void setConsultationMinDate(LocalDate consultationMinDate) {
        this.consultationMinDate = consultationMinDate;
    }

    public LocalDate getConsultationMaxDate() {
        return consultationMaxDate;
    }

    public void setConsultationMaxDate(LocalDate consultationMaxDate) {
        this.consultationMaxDate = consultationMaxDate;
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

    public Integer getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(Integer interviewID) {
        this.interviewID = interviewID;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public Integer getAmuNr() {
        return AmuNr;
    }

    public void setAmuNr(Integer amuNr) {
        AmuNr = amuNr;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public Integer getEducationNoOfDays() {
        return educationNoOfDays;
    }

    public void setEducationNoOfDays(Integer educationNoOfDays) {
        this.educationNoOfDays = educationNoOfDays;
    }

    public LocalDate getEducationMinDate() {
        return educationMinDate;
    }

    public void setEducationMinDate(LocalDate educationMinDate) {
        this.educationMinDate = educationMinDate;
    }

    public LocalDate getEducationMaxDate() {
        return educationMaxDate;
    }

    public void setEducationMaxDate(LocalDate educationMaxDate) {
        this.educationMaxDate = educationMaxDate;
    }

    public Integer getProviderID() {
        return providerID;
    }

    public void setProviderID(Integer providerID) {
        this.providerID = providerID;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof SearchContainer)) {
            return false;
        }
        SearchContainer other = (SearchContainer) obj;
        //Test all fields
        if (!this.companyID.equals(other.companyID)) {
            return false;
        }
        if (!this.cvrNr.equals(other.cvrNr)) {
            return false;
        }
        if (!this.companyName.equals(other.companyName)) {
            return false;
        }
        if (!this.consultationID.equals(other.consultationID)) {
            return false;
        }
        if (!this.consultationName.equals(other.consultationName)) {
            return false;
        }
        if (!this.consultationMinDate.equals(other.consultationMinDate)) {
            return false;
        }
        if (!this.consultationMaxDate.equals(other.consultationMaxDate)) {
            return false;
        }
        if (!this.employeeID.equals(other.employeeID)) {
            return false;
        }
        if (!this.employeeFirstName.equals(other.employeeFirstName)) {
            return false;
        }
        if (!this.employeeLastName.equals(other.employeeLastName)) {
            return false;
        }
        if (!this.cprNr.equals(other.cprNr)) {
            return false;
        }
        if (!this.email.equals(other.email)) {
            return false;
        }
        if (!this.phoneNr.equals(other.phoneNr)) {
            return false;
        }
        if (!this.interviewID.equals(other.interviewID)) {
            return false;
        }
        if (!this.interviewName.equals(other.interviewName)) {
            return false;
        }
        if (!this.AmuNr.equals(other.AmuNr)) {
            return false;
        }
        if (!educationName.equals(other.educationName)) {
            return false;
        }
        if (!educationNoOfDays.equals(other.educationNoOfDays)) {
            return false;
        }
        if (!this.educationMinDate.equals(other.educationMinDate)) {
            return false;
        }
        if (!this.educationMaxDate.equals(other.educationMaxDate)) {
            return false;
        }
        if (!this.providerID.equals(other.providerID)) {
            return false;
        }
        return this.providerName.equals(other.providerName);
    }
}
