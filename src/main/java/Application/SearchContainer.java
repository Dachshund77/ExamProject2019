package Application;

import Domain.*;
import Foundation.DB;
import Foundation.DbFacade;

import java.time.LocalDate;

/**
 * Helper class that is used to pass search queries to the Database.
 *
 * @see DbFacade
 */
public class SearchContainer {
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

    public String getCompanyIDasString(){
        if (companyID == null){
            return null;
        } else {
            return companyID.toString();
        }
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    /**
     * Converts String to Integer. If not able sets the field to null.
     * @param companyID new company ID
     */
    public void setCompanyID(String companyID) {
        if(companyID == null || companyID.trim().isEmpty()) {
            this.companyID = null;
            return;
        }
        try {
            this.companyID = Integer.parseInt(companyID);
        }catch (NumberFormatException e){
            this.companyID = null;
        }

    }

    public String getCvrNr() {
        return cvrNr;
    }

    /**
     * Convert empty Strings to null.
     * @param cvrNr new cvr Nr.
     */
    public void setCvrNr(String cvrNr) {
        if (cvrNr == null || cvrNr.trim().isEmpty()){
            this.cvrNr = null;
        }else {
            this.cvrNr = cvrNr;
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    /**
     * Converts empty Strings to null.
     * @param companyName new Company name
     */
    public void setCompanyName(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()){
            this.companyName = null;
        }else {
            this.companyName = companyName;
        }
    }

    public Integer getConsultationID() {
        return consultationID;
    }

    public String getConsultationIDasString(){
        if (consultationID == null){
            return null;
        } else {
            return consultationID.toString();
        }
    }

    public void setConsultationID(Integer consultationID) {
        this.consultationID = consultationID;
    }

    /**
     * Converts String to Integer. If not able sets the field to bull.
     * @param consultationID new Consultation ID
     */
    public void setConsultationID(String consultationID) {
        if (consultationID == null || consultationID.trim().isEmpty()){
            this.consultationID = null;
            return;
        }
        try {
            this.consultationID = Integer.parseInt(consultationID);
        }catch (NumberFormatException e){
            this.consultationID = null;
        }
    }

    public String getConsultationName() {
        return consultationName;
    }

    /**
     * Converts empty Strings to null.
     * @param consultationName new consultation name.
     */
    public void setConsultationName(String consultationName) {
        if (consultationName == null || consultationName.trim().isEmpty()){
            this.consultationName = null;
        } else {
            this.consultationName = consultationName;
        }
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

    public String getEmployeeIDasString(){
        if (employeeID == null){
            return null;
        } else {
            return employeeID.toString();
        }
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }


    /**
     * Converts String to Integer. If not able the field is set to null.
     * @param employeeID new employee ID.
     */
    public void setEmployeeID(String employeeID) {
        if (employeeID == null || employeeID.trim().isEmpty()){
            this.employeeID = null;
            return;
        }
        try {
            this.employeeID = Integer.parseInt(employeeID);
        }catch (NumberFormatException e){
            this.employeeID = null;
        }
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    /**
     * Converts empty Strings to null
     * @param employeeFirstName new employee First name.
     */
    public void setEmployeeFirstName(String employeeFirstName) {
        if (employeeFirstName == null || employeeFirstName.trim().isEmpty()){
            this.employeeFirstName = null;
        } else {
            this.employeeFirstName = employeeFirstName;
        }
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    /**
     * Converts empty Strings to null.
     * @param employeeLastName new employee last name.
     */
    public void setEmployeeLastName(String employeeLastName) {
        if (employeeLastName== null || employeeLastName.trim().isEmpty()){
            this.employeeLastName = null;
        } else {
            this.employeeLastName = employeeLastName;
        }
    }

    public String getCprNr() {
        return cprNr;
    }

    /**
     * Converts empty String to null.
     * @param cprNr new cpr nr.
     */
    public void setCprNr(String cprNr) {
        if (cprNr == null || cprNr.trim().isEmpty()){
            this.cprNr = null;
        }else {
            this.cprNr = cprNr;
        }
    }

    public String getEmail() {
        return email;
    }

    /**
     * Converts empty Strings to null.
     * @param email new Email.
     */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()){
            this.email = null;
        }else {
            this.email = email;
        }
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    /**
     * Converts empty Strings to null.
     * @param phoneNr new phone nr.
     */
    public void setPhoneNr(String phoneNr) {
        if (phoneNr == null || phoneNr.trim().isEmpty()){
            this.phoneNr = null;
        } else {
            this.phoneNr = phoneNr;
        }
    }

    public Integer getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(Integer interviewID) {
        this.interviewID = interviewID;
    }

    /**
     * Converts String to Integer. If not able to the field is set to null.
     * @param interviewID new interview ID.
     */
    public void setInterviewID(String interviewID) {
        if (interviewID == null || interviewID.trim().isEmpty()){
            this.interviewID = null;
            return;
        }
        try {
            this.interviewID = Integer.parseInt(interviewID);
        }catch (NumberFormatException e){
            this.interviewID = null;
        }
    }

    public String getInterviewName() {
        return interviewName;
    }

    public String getInterviewIDasString(){
        if (interviewID == null){
            return null;
        } else {
            return interviewID.toString();
        }
    }

    /**
     * Converts empty Strings to null.
     * @param interviewName new interview name.
     */
    public void setInterviewName(String interviewName) {
        if (interviewName == null || interviewName.trim().isEmpty()){
            this.interviewName = null;
        } else {
            this.interviewName = interviewName;
        }
    }

    public Integer getAmuNr() {
        return AmuNr;
    }

    public String getAmuNrAsString(){
        if (AmuNr == null){
            return null;
        } else {
            return AmuNr.toString();
        }
    }

    public void setAmuNr(Integer amuNr) {
        AmuNr = amuNr;
    }

    /**
     * Converts String to Integer. If not able to the field is net to null.
     * @param amuNr new amu nr.
     */
    public void setAmuNr(String amuNr) {
        if (amuNr == null || amuNr.trim().isEmpty()){
            this.AmuNr = null;
            return;
        }
        try {
            this.AmuNr = Integer.parseInt(amuNr);
        }catch (NumberFormatException e){
            this.AmuNr = null;
        }
    }

    public String getEducationName() {
        return educationName;
    }

    /**
     * Converts empty Strings to null.
     * @param educationName new education name
     */
    public void setEducationName(String educationName) {
        if (educationName == null || educationName.trim().isEmpty()){
            this.educationName = null;
        }else {
            this.educationName = educationName;
        }
    }

    public Integer getEducationNoOfDays() {
        return educationNoOfDays;
    }

    public String getEducationNoOfDaysAsString(){
        if (educationNoOfDays == null){
            return null;
        } else {
            return educationNoOfDays.toString();
        }
    }

    public void setEducationNoOfDays(Integer educationNoOfDays) {
        this.educationNoOfDays = educationNoOfDays;
    }

    /**
     * Converts String to Integer. If not able to the field is set to null.
     * @param educationNoOfDays new education number of days value.
     */
    public void setEducationNoOfDays(String educationNoOfDays) {
        if (educationNoOfDays == null || educationNoOfDays.trim().isEmpty()){
            this.educationNoOfDays = null;
            return;
        }
        try {
            this.educationNoOfDays = Integer.parseInt(educationNoOfDays);
        }catch (NumberFormatException e){
            this.educationNoOfDays = null;
        }
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

    public String getProviderIDasString(){
        if (providerID == null){
            return null;
        } else {
            return providerID.toString();
        }
    }

    public void setProviderID(Integer providerID) {
        this.providerID = providerID;
    }

    /**
     * Converts String to Integer. If not able to the field is set to null.
     * @param providerID new Provider ID
     */
    public void setProviderID(String providerID) {
        if (providerID == null || providerID.trim().isEmpty()){
            this.providerID = null;
            return;
        }
        try {
            this.providerID = Integer.parseInt(providerID);
        }catch (NumberFormatException e){
            this.providerID = null;
        }
    }

    public String getProviderName() {
        return providerName;
    }

    /**
     * Converts empty String to null.
     * @param providerName new Provider name.
     */
    public void setProviderName(String providerName) {
        if (providerName == null || providerName.trim().isEmpty()){
            this.providerName = null;
        } else {
            this.providerName = providerName;
        }
    }

    //Company validation

    /**
     * A valid ID may not be negative.
     *
     * @param companyID ID to be tested
     * @return True if valid
     */
    public static boolean isValidCompanyID(Integer companyID) {
        return companyIDInvalidCause(companyID) == null;
    }

    /**
     * A valid ID may not be negative.
     *
     * @param companyID ID to be tested
     * @return True if valid
     */
    public static boolean isValidCompanyID(String companyID) {
        return companyIDInvalidCause(companyID) == null;
    }

    /**
     * Return the first reason why the Integer is not valid ID.
     * A valid ID may not be negative.
     *
     * @param companyID ID that is invalid
     * @return String with reason, null if none are detected.
     */
    public static String companyIDInvalidCause(Integer companyID) {
        if (companyID != null && companyID <= 0) {
            return "Company ID must be positive!";
        }
        return null;
    }

    /**
     * Return the first reason why the String is not valid ID.
     * A valid ID may not be negative.
     *
     * @param companyID ID that is invalid
     * @return String with reason, null if none are detected.
     */
    public static String companyIDInvalidCause(String companyID) {
        if (companyID == null){
            return null;
        }
        if (companyID.trim().isEmpty()){
            return null;
        }
        try {
            return companyIDInvalidCause(Integer.parseInt(companyID));
        } catch (NumberFormatException e) {
            return "Must be a number!";
        }
    }

    /**
     * A valid cvrNr may not be null, empty String and must be Char long.
     *
     * @param cvrNr cvr To be tested
     * @return True if cvr is valid
     */
    public static boolean isValidCvrNr(String cvrNr) {
        return cvrNrInvalidCause(cvrNr) == null;
    }

    /**
     * Return the first reason why a cvr is not valid.
     * A valid cvrNr may not be null, empty String and must be 8 Char long.
     *
     * @param cvrNr cvr to be tested
     * @return String with first reason, null if non was detected.
     */
    public static String cvrNrInvalidCause(String cvrNr) {
        if (cvrNr == null) {
            return null;
        } else if (cvrNr.trim().isEmpty()) {
            return null;
        } else if (!cvrNr.matches("[0-9]+")) {
            return "CvrNr may not contain letters!";
        } else if(cvrNr.length() > Company.getCvrExactLength()){
            return "Cvr may not be longer than "+Company.getCvrExactLength()+" letters!";
        }
        return null;
    }

    /**
     * A valid Company name may not be null, empty String or Longer than 50 Chars.
     *
     * @param name company name to be tested.
     * @return True if name is valid.
     */
    public static boolean isValidCompanyName(String name) {
        return companyNameInvalidClause(name) == null;
    }

    /**
     * Return the first reason why a String is a invalid company Name.
     * A valid Company name may not be null, empty String or Longer than 50 Chars.
     *
     * @param name String that should be the Company name
     * @return String with first reason, null if non was detected.
     */
    public static String companyNameInvalidClause(String name) {
        if (name == null) {
            return null;
        } else if (name.trim().isEmpty()) {
            return null;
        } else if (name.length() > Company.getCompanyNameMaxLength()) {
            return "Company Name must be "+Company.getCompanyNameMaxLength()+" letters or less!";
        }
        return null;
    }

    //Consultation validation

    /**
     * Check if consultation ID is not null
     * consultationID should also be positive
     * @param consultationID Integer to be tested
     * @return True if valid
     */
    public static boolean isValidConsultationID(Integer consultationID){
        return consultationIDInvalidCause(consultationID) == null;
    }

    /**
     * Check if consultation ID is not null
     * consultationID should also be positive
     * @param consultationID String to be tested
     * @return True if valid
     */
    public static boolean isValidConsultationID(String consultationID){
        return consultationIDInvalidCause(consultationID) == null;
    }

    /**
     * returns the first reason for an error
     * if conditions are not met
     * Error = negative number
     * @param consultationID Integer to be tested
     * @return First invalid reason, null if valid
     */
    public static String consultationIDInvalidCause(Integer consultationID){
        if (consultationID != null && consultationID <= 0){
            return "Company is must be positive!";
        }
        return null;
    }

    /**
     * returns the first reason for an error
     * if conditions are not met
     * Error = negative number
     * @param consultationID String to be tested
     * @return First invalid reason, null if valid
     */
    public static String consultationIDInvalidCause(String consultationID){
        if (consultationID == null){
            return null;
        }
        if (consultationID.trim().isEmpty()){
            return null;
        }
        try{
            return consultationIDInvalidCause(Integer.parseInt(consultationID));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    /**
     * If consultationName is null, an empty string and over 50 chars
     * it results in an error
     * @param consultationName String to be tested
     * @return First invalid reason, null if valid
     */
    public static boolean isValidConsultationName(String consultationName){
        return consultationNameInvalidCause(consultationName)==null;
    }

    /**
     * this method will return all errors relating to consultation name
     * errors are:
     * consultationName = NULL
     * consultationName = Empty string
     * consultationName = More than 50 chars
     * @param consultationName String to be tested
     * @return String with first invalid reason, Null if valid
     */
    public static String consultationNameInvalidCause(String consultationName){
        if (consultationName == null){
            return null;
        }
        else if (consultationName.trim().isEmpty()){
            return null;
        }
        else if (consultationName.length() > Consultation.getConsultationNameMaxLength()){
            return "Consultation name have to be less than "+Consultation.getConsultationNameMaxLength()+" characters";
        }
        return null;
    }

    /**
     * checks if startDate is before endDate
     * @param startDate localDate at start
     * @param endDate localDate at end
     * @return True if valid combination
     */
    public static boolean isValidDate(LocalDate startDate, LocalDate endDate){
        return dateInvalidCause(startDate,endDate) == null;
    }

    /**
     * Throws an error if dates are NULL
     * also throws an error if end date is before start date
     * @param startDate LocalDate at start
     * @param endDate LocalDate at end
     * @return String with first invalid reason, null if valid.
     */
    public static String dateInvalidCause(LocalDate startDate, LocalDate endDate){
        if (startDate == null || endDate == null){
            return null;
        }
        else if(endDate.isBefore(startDate)){
            return "start date must be before end date";
        }
        return null;
    }

    //Education Validation

    /**
     * Amu nr may not be negative.
     * @param amuNr Integer to be tested.
     * @return True if valid.
     */
    public static boolean isValidAmuNr(Integer amuNr){
        return amuNrInvalidCause(amuNr)== null;
    }

    /**
     * Amu nr may not be negative.
     * @param amuNr String to be tested.
     * @return True if valid.
     */
    public static boolean isValidAmuNr(String amuNr){
        return amuNrInvalidCause(amuNr) == null;
    }

    /**
     * Return the first reason why an amuNr is invalid.
     * @param amuNr Integer to be tested.
     * @return String with first problem, null if valid.
     */
    public static String amuNrInvalidCause(Integer amuNr){
        if (amuNr != null && amuNr <= 0){
            return "Amu Nr must be positive!";
        }
        return null;
    }

    /**
     * Return the first reason why an amuNr is invalid.
     * @param amuNr String to be tested.
     * @return String with first problem, null if valid.
     */
    public static String amuNrInvalidCause(String amuNr){
        if (amuNr == null){
            return null;
        }
        if (amuNr.trim().isEmpty()){
            return null;
        }
        try{
            return amuNrInvalidCause(Integer.parseInt(amuNr));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    /**
     * checks if educationName is valid
     * @param educationName String to be tested.
     * @return True if valid.
     */
    public static boolean isValidEducationName(String educationName)
    {
        return educationNameInvalidCause(educationName) == null;
    }

    /**
     * throws an error if
     * educationName = NULL
     * educationName = Empty string
     * educationName = Bigger than 30 chars
     * @param educationName String to be tested.
     * @return String with first problem, null if valid.
     */
    public static String educationNameInvalidCause (String educationName)
    {
        if (educationName == null){
            return null;
        }
        else if (educationName.trim().isEmpty()){
            return null;
        }
        else if (educationName.length() > Education.getEducationNameMaxLength()){
            return "Education must consist of a name less than "+Education.getEducationNameMaxLength()+" characters";
        }
        return null;
    }

    /**
     * checks if NoOfDays arent null and within limit
     * @param noOfDays Integer to be tested
     * @return True if valid.
     */
    public static boolean isValidNoOfDays(Integer noOfDays)
    {
        return noOfDaysInvalidCause(noOfDays) == null;
    }

    /**
     * checks if NoOfDays arent null and within limit
     * @param noOfDays String to be tested.
     * @return True if valid.
     */
    public static boolean isValidNoOfDays(String noOfDays)
    {
        return noOfDaysInvalidCause(noOfDays) == null;
    }

    /**
     * Throws an error if:
     * NoOfDays = NULL
     * NoOfDays = Negative number
     * NoOfDays = Bigger than 15
     * @param NoOfDays Integer to be tested.
     * @return String with first found problem, null if valid.
     */
    public static String noOfDaysInvalidCause(Integer NoOfDays)
    {
        if (NoOfDays == null){
            return null;
        }
        else if (NoOfDays > Education.getNoOfDaysMaxNumber()){
            return "Number of days cant be more than "+Education.getNoOfDaysMaxNumber()+"!";
        }
        else if (NoOfDays <= 0){
            return "Number of days must be positive!";
        }
        return null;
    }

    /**
     * Throws an error if:
     * NoOfDays = NULL
     * NoOfDays = Negative number
     * NoOfDays = Bigger than 15
     * @param noOfDays String to be tested.
     * @return String with first found problem,null if valid.
     */
    public static String noOfDaysInvalidCause(String noOfDays) {
        if (noOfDays == null){
            return null;
        }if (noOfDays.trim().isEmpty()){
            return null;
        }
        try{
            return noOfDaysInvalidCause(Integer.parseInt(noOfDays));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    // Employee validation

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
            if (employeeFirstName.length() > Employee.getEmployeeFirstNameMaxLength()) {
                return "First Name must be "+Employee.getEmployeeFirstNameMaxLength()+" or less letters!";
            }
        }
        return null;
    }

    public static boolean isValidEmployeeLastName(String employeeLastName) {
        return employeeLastNameInvalidCause(employeeLastName) == null;
    }

    public static String employeeLastNameInvalidCause(String employeeLastName) {
        if (employeeLastName != null) {
            if (employeeLastName.length() > Employee.getEmployeeLastNameMaxLength()) {
                return "Last Name must be "+Employee.getEmployeeLastNameMaxLength()+" or less letters!";
            }
        }
        return null;
    }

    public static boolean isValidCprNr(String cprNr) {
        return cprNrInvalidCause(cprNr) == null;
    }

    public static String cprNrInvalidCause(String cprNr) {
        if (cprNr == null) {
            return null;
        }
        if (cprNr.trim().isEmpty()) {
            return null;
        }
        if (cprNr.length() > Employee.getCprExactLength()) {
            return "CprNr must be "+Employee.getCprExactLength()+" letters or less!";
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
            if (email.length() > Employee.getEmailMaxLength()) {
                return "Email must be "+Employee.getEmailMaxLength()+" or less letters!";
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
            if (phoneNr.length() > Employee.getPhoneNrMaxLength()) {
                return "Phone Number must be "+Employee.getPhoneNrMaxLength()+" or less letters!";
            }
        }
        return null;
    }

    //Interview validation

    /**
     * A valid interview ID may not be negative.
     *
     * @param interviewID Integer to tested.
     * @return True if Integer is a valid Id
     */
    public static boolean isValidInterviewID(Integer interviewID) {
        return interviewIDInvalidCause(interviewID) == null;
    }

    public static boolean isValidInterviewID(String interviewID) {
        return interviewIDInvalidCause(interviewID) == null;
    }

    /**
     * Return first reason why an integer is not a valid interviewID
     * A valid interview ID may not be negative.
     *
     * @param interviewID Interger to be tested.
     * @return String with first found reason, null if Integer is valid.
     */
    public static String interviewIDInvalidCause(Integer interviewID) {
        if (interviewID != null && interviewID <= 0) {
            return "Interview ID must be positive!";
        }
        return null;
    }

    /**
     * Return first reason why an integer is not a valid interviewID
     * A valid interview ID may not be negative.
     *
     * @param interviewID Interger to be tested.
     * @return String with first found reason, null if Integer is valid.
     */
    public static String interviewIDInvalidCause(String interviewID) {
        if (interviewID == null){
            return null;
        }
        if (interviewID.trim().isEmpty()){
            return null;
        }
        try{
            return interviewIDInvalidCause(Integer.parseInt(interviewID));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    /**
     * A valid Interview Name may not be null, empty and must be 30 char or less.
     *
     * @param interviewName String to be tested.
     * @return True of String is valid Interview Name.
     */
    public static boolean isValidInterviewName(String interviewName) {
        return interviewNameInvalidCause(interviewName) == null;
    }

    /**
     * Return the first reason why an String is not a interview Name.
     * A valid Interview Name may not be null, empty and must be 30 char or less.
     *
     * @param interviewName String to be tested.
     * @return String of the first reason it is not valid, null if String is valid.
     */
    public static String interviewNameInvalidCause(String interviewName) {
        if (interviewName == null) {
            return null;
        } else if (interviewName.trim().isEmpty()) {
            return null;
        } else if (interviewName.length() > Interview.getInterviewNameMaxLength()) {
            return "Interview Name must be "+ Interview.getInterviewNameMaxLength() +" Characters or less!";
        }
        return null;
    }

    //Provider validation

    /**
     * A valid Id my not be negative.
     *
     * @param id Integer to be tested.
     * @return True if Integer is valid id.
     */
    public static boolean isValidProviderID(Integer id) {
        return invalidProviderIDCause(id) == null;
    }

    public static boolean isValidProviderID(String id){
        return invalidProviderIDCause(id) == null;
    }

    /**
     * Returns the first reason why an Integer is not a valid Provider ID.
     * A valid Id my not be negative.
     *
     * @param id Integer to be tested.
     * @return String with first reason or null if valid.
     */
    public static String invalidProviderIDCause(Integer id) {
        if (id != null && id <= 0) {
            return "Provider ID must be positive!";
        }
        return null;
    }

    /**
     * Returns the first reason why an Integer is not a valid Provider ID.
     * A valid Id my not be negative.
     *
     * @param id Integer to be tested.
     * @return String with first reason or null if valid.
     */
    public static String invalidProviderIDCause(String id) {
        if (id == null){
            return null;
        }
        if (id.trim().isEmpty()){
            return null;
        }
        try{
            return invalidProviderIDCause(Integer.parseInt(id));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    /**
     * A valid Provider name may not be null, must not be empty and must be 30 or less characters.
     *
     * @param providerName String to be tested as Provider Name.
     * @return True if valid Provider Name
     */
    public static boolean isValidProviderName(String providerName) {
        return providerNameInvalidCause(providerName) == null;
    }

    /**
     * Return the first reason why an String is not a valid Provider Name.
     *
     * @param providerName String to be tested.
     * @return String with first reason or null if valid.
     */
    public static String providerNameInvalidCause(String providerName) {
        if (providerName == null) {
            return null;
        } else if (providerName.trim().isEmpty()) {
            return null;
        } else if (providerName.length() > Provider.getProviderNameMaxLength()) {
            return "Provider Name must be "+ Provider.getProviderNameMaxLength() +" letters or less";
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof SearchContainer)) {
            return false;
        }
        SearchContainer other = (SearchContainer) obj;
        if (this == other){
            return true;
        }
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
