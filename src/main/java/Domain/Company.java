package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Company {

    private final Integer companyID;
    private String cvrNr;
    private String companyName;
    private ArrayList<Consultation> consultations;
    private ArrayList<Education> educationList;


    public Company(Integer companyID, String cvrNr, String companyName, ArrayList<Consultation> consultations, ArrayList<Education> educationList) {
        this.companyID = companyID;
        this.cvrNr = cvrNr;
        this.companyName = companyName;
        this.consultations = Objects.requireNonNullElseGet(consultations, ArrayList::new);
        this.educationList = Objects.requireNonNullElseGet(educationList, ArrayList::new);
    }


    public Integer getCompanyID() {
        return companyID;
    }

    public String getCvrNr() {
        return cvrNr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ArrayList<Consultation> getConsultations() {
        return consultations;
    }

    public ArrayList<Education> getEducationList() {
        return educationList;
    }

    /**
     * A valid ID may not be negative.
     * @param companyID ID to be tested
     * @return True if valid
     */
    public static boolean isValidCompanyID(Integer companyID) {
        return companyIDInvalidCause(companyID) == null;
    }

    /**
     * A valid ID may not be negative.
     * @param companyID ID to be tested
     * @return True if valid
     */
    public static boolean isValidCompanyID(String companyID) {
        return companyIDInvalidCause(companyID) == null;
    }


    /**
     * Return the first reason why the Integer is not valid ID.
     * A valid ID may not be negative.
     * @param companyID ID that is invalid
     * @return String with reason, null if none are detected.
     */
    public static String companyIDInvalidCause(Integer companyID){
        if (companyID != null && companyID < 0){
            return  "Company ID may not be negative!";
        }
        return null;
    }

    /**
     * Return the first reason why the String is not valid ID.
     * A valid ID may not be negative.
     * @param companyID ID that is invalid
     * @return String with reason, null if none are detected.
     */
    public static String companyIDInvalidCause(String companyID){
        try{
            return companyIDInvalidCause(Integer.parseInt(companyID));
        }catch (NumberFormatException e){
            System.out.println("number");
            return "Must be a number!";
        }
    }

    /**
     * A valid cvrNr may not be null, empty String and must be 8 Char long.
     * @param cvrNr cvr To be tested
     * @return True if cvr is valid
     */
    public static boolean isValidCvrNr(String cvrNr) {
        return cvrNrInvalidCause(cvrNr) == null;
    }

    /**
     * Return the first reason why a cvr is not valid.
     * A valid cvrNr may not be null, empty String and must be 8 Char long.
     * @param cvrNr cvr to be tested
     * @return String with first reason, null if non was detected.
     */
    public static String cvrNrInvalidCause(String cvrNr){
        if (cvrNr == null) {
            System.out.println("may not null");
            return "CvrNr may not be Null!";
        }else if(!cvrNr.matches("[0-9]+")){
            System.out.println("cvr not contain letters");
            return "CvrNr may not contain letters!";
        } else if (cvrNr.trim().isEmpty()){
            System.out.println("is empty");
            return "CvrNr may not be empty!";
        } else if(cvrNr.length() != 8){
            System.out.println("not 8 chars");
            return "CvrNr must be 8 characters long!";
        }
        System.out.println("returning null");
        return null;
    }

    /**
     * A valid Company name may not be null, empty String or Longer than 50 Chars.
     * @param name company name to be tested.
     * @return True if name is valid.
     */
    public static boolean isValidCompanyName(String name) {
        return companyNameInvalidClause(name) == null;
    }

    /**
     * Return the first reason why a String is a invalid company Name.
     * A valid Company name may not be null, empty String or Longer than 50 Chars.
     * @param name String that should be the Company name
     * @return String with first reason, null if non was detected.
     */
    public static String companyNameInvalidClause(String name){
        if(name == null){
            return "Company Name may not be Null!";
        } else if(name.trim().isEmpty()){
            return "Company Name may not be empty!";
        } else if(name.length() > 50){
            return "Company Name must be 50 letters or less!";
        }
        return null;
    }

}
