package Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class Consultation {

    private final Integer consultationID;
    private String consultationName;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Employee> employees;

    public Consultation(Integer consultationID, String consultationName, LocalDate startDate, LocalDate endDate, ArrayList<Employee> employees) {
        this.consultationID = consultationID;
        this.consultationName = consultationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employees = Objects.requireNonNullElseGet(employees, ArrayList::new);
    }


    public Integer getConsultationID() {
        return consultationID;
    }

    public String getConsultationName() {
       return consultationName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

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
       if (consultationID < 0){
           return "Company is may not be negative";
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
            return "Consultation name may not be null";
        }
        else if (consultationName.trim().isEmpty()){
            return "A consultation name is required";
        }
        else if (consultationName.length() > 50){
            return "Consultation name have to be less than 50 characters";
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
        if (isValidDate(null, null)){
            return "Dates must not be null";
        }
        else if(endDate.isBefore(startDate)){
            return "start date must be before end date";
        }
        return null;
    }


}
