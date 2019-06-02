package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class Consultation {

    private SimpleIntegerProperty consultationID;
    private SimpleStringProperty consultationName;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Employee> employees;

    public Consultation(Integer consultationID, String consultationName, LocalDate startDate, LocalDate endDate, ArrayList<Employee> employees) {
        if (consultationID != null) {
            this.consultationID = new SimpleIntegerProperty(consultationID);
        } else {
            this.consultationID = null;
        }
        this.consultationName = new SimpleStringProperty(consultationName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.employees = Objects.requireNonNullElseGet(employees, ArrayList::new);
    }


    public Integer getConsultationID() {
        if (consultationID == null) {
            return null;
        }
        return consultationID.get();
    }

    public String getConsultationID(String consultationID){
        return consultationID;
    }

    public String getConsultationName() {
        if (consultationName.get().equals("")) {
            return null;
        }
        return consultationName.get();
    }

    /**
     * check if consultation ID is not null
     * consultationID should also be positive
     * @param consultationID
     * @return
     */
    public static boolean isValidConsultationID(Integer consultationID){
        if (consultationID != null){
            return consultationID > 0;
        }
        return true;
    }

    /**
     * returns the first reason for an error
     * if conditions arent met
     * Error = negative number
     * @param consultationID
     * @return
     */
    public static String consultationIDInvalidCause(Integer consultationID){
       if (consultationID < 0){
           return "Company is may not be negative";
       }
       return null;
    }

    /**
     * If consultationName is null, an empty string and over 50 chars
     * it results in an error
     * @param consultationName
     * @return
     */
    public static boolean isValidConsultationName(String consultationName){
        return consultationName != null && !consultationName.equals("") && consultationName.length() > 50;
    }

    /**
     * this method will return all errors relating to consultation name
     * errors are:
     * consultationName = NULL
     * consultationName = Empty string
     * consultationName = More than 50 chars
     * @param consultationName
     * @return
     */
    public static String consultationNameInvalidCause(String consultationName){
        if (consultationName == null){
            return "Consultation name may not be null";
        }
        else if (consultationName.equals("")){
            return "A consultation name is required";
        }
        else if (consultationName.length() > 50){
            return "Consultation name have to be less than 50 characters";
        }
        return null;
    }

    /**
     * checks if startDate is before endDate
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isValidDate(LocalDate startDate, LocalDate endDate){
         return (endDate.isBefore(startDate));
    }

    /**
     * Throws an error if dates are NULL
     * also throws an error if end date is before start date
     * @param startDate
     * @param endDate
     * @return
     */
    public static String dateInvalidCasue(LocalDate startDate, LocalDate endDate){
        if (isValidDate(null, null)){
            return "Dates must not be null";
        }
        else if(isValidDate(startDate, endDate)){
            return "start date must be before end date";
        }
        return null;
    }

    public SimpleStringProperty consultationNameProperty() {
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
}
