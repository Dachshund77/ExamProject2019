package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Education {
    private final Integer amuNr;
    private String educationName;
    private String description;
    private Integer noOfDays;
    private ArrayList<LocalDate> dates;
    private Provider provider;

    public Education(Integer amuNr, String educationName, String description, Integer noOfDays, ArrayList<LocalDate> dates, Provider provider) {
        this.amuNr = amuNr;
        this.educationName = educationName;
        this.description = description;
        this.noOfDays = noOfDays;
        this.dates = Objects.requireNonNullElseGet(dates, ArrayList::new);
        this.provider = provider;
    }

    public Integer getAmuNr() {
        return amuNr;
    }

    public String getEducationName() {
        return educationName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public ArrayList<LocalDate> getDates() {
        return dates;
    }

    public Provider getProvider() {
        return provider;
    }


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
        if (amuNr != null && amuNr < 0){
            return "Amu Nr may not be negative!";
        }
        return null;
    }

    /**
     * Return the first reason why an amuNr is invalid.
     * @param amuNr String to be tested.
     * @return String with first problem, null if valid.
     */
    public static String amuNrInvalidCause(String amuNr){
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
            return "Education cant be null";
        }
        else if (educationName.trim().isEmpty()){
            return "Education must have a name";
        }
        else if (educationName.length() > 30){
            return "Education must consist of a name less than 30 characters";
        }
        return null;
    }

    /**
     * checks if description contains information about a course
     * @param description String to be tested.
     * @return True if valid.
     */
    public static boolean isValidDescription(String description)
    {
        return descriptionInvalidCause(description) == null;
    }

    /**
     * throws an error if:
     * description = NULL
     * description = Empty string
     * @param description string to be tested.
     * @return String with first problem, null if valid.
     */
    public static String descriptionInvalidCause (String description)
    {
        if (description == null){
            return "Description must not be null";
        }
        else if (description.trim().isEmpty()){
            return "Description must contain some information"; //TODO but dont we allow null values? -Sven
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
           return "Number of days cant be null";
       }
       else if (NoOfDays > 15){
           return "Number of days cant be more than 15";
       }
       else if (NoOfDays < 0){
           return "Number of days cant be negative";
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
        try{
            return noOfDaysInvalidCause(Integer.parseInt(noOfDays));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }
}
