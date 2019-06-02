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
    private final SimpleIntegerProperty amuNr; //TODO MAKE FINAL INT
    private SimpleStringProperty educationName;
    private SimpleStringProperty description;
    private SimpleIntegerProperty noOfDays;
    private ArrayList<LocalDate> dates;
    private SimpleObjectProperty<Provider> provider;

    public Education(Integer amuNr, String educationName, String description, Integer noOfDays, ArrayList<LocalDate> dates, Provider provider) {
        //Init amuNr
        if (amuNr != null) {
            this.amuNr = new SimpleIntegerProperty(amuNr);
        } else {
            this.amuNr = null;
        }
        //init educationName
        this.educationName = new SimpleStringProperty(educationName);
        //Init description
        this.description = new SimpleStringProperty(description);
        //Init noOfDays
        if (noOfDays != null) {
            this.noOfDays = new SimpleIntegerProperty(noOfDays);
        } else {
            this.noOfDays = new SimpleIntegerProperty(-1);
        }
        //init dates
        this.dates = Objects.requireNonNullElseGet(dates, ArrayList::new);
        // init provider
        this.provider = new SimpleObjectProperty<>(provider);
    }

    public Integer getAmuNr() {
        if (amuNr == null) {
            return null;
        }
        return amuNr.get();
    }

    public String getEducationName() {
        if (educationName.get().equals("")){
            return null;
        }
        return educationName.get();
    }

    public SimpleStringProperty educationNameProperty() {
        return educationName;
    }

    public String getDescription() {
        if (description.get().equals("")){
            return null;
        }
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public Integer getNoOfDays() {
        if (noOfDays.get() == -1) {
            return null;
        }
        return noOfDays.get();
    }

    public SimpleIntegerProperty noOfDaysProperty() {
        return noOfDays;
    }

    public ArrayList<LocalDate> getDates() {
        return dates;
    }

    public Provider getProvider() {
        return provider.get();
    }

    public SimpleObjectProperty<Provider> providerProperty() {
        return provider;
    }


    /**
     * checks if educationName is valid
     * @param educationName
     * @return
     */
    public static boolean isValidEducationName(String educationName)
    {
      return educationName != null && !educationName.equals("") && educationName.length() < 30;
    }

    /**
     * throws an error if
     * educationName = NULL
     * educationName = Empty string
     * educationName = Bigger than 30 chars
     * @param educationName
     * @return
     */
    public static String educationNameInvalidCause (String educationName)
    {
        if (educationName == null){
            return "Education cant be null";
        }
        else if (educationName.equals("")){
            return "Education must have a name";
        }
        else if (educationName.length() > 30){
            return "Education must consist of a name less than 30 characters";
        }
        return null;
    }

    /**
     * checks if description contains information about a course
     * @param description
     * @return
     */
    public static boolean isValidDescription(String description)
    {
        return description != null && !description.equals("");
    }

    /**
     * throws an error if:
     * description = NULL
     * description = Empty string
     * @param description
     * @return
     */
    public static String descriptionInvalidCause (String description)
    {
        if (description == null){
            return "Description must not be null";
        }
        else if (description.equals("")){
            return "Description must contain some information";
        }
        return null;
    }


    /**
     * checks if NoOfDays arent null and within limit
     * @param NoOfDays
     * @return
     */
    public static boolean isValidNoOfDays(Integer NoOfDays)
    {
        return NoOfDays != null && NoOfDays > 0 && NoOfDays <=15;
    }

    /**
     * Throws an error if:
     * NoOfDays = NULL
     * NoOfDays = Negative number
     * NoOfDays = Bigger than 15
     * @param NoOfDays
     * @return
     */
    public String noOfDaysInvalidCause(Integer NoOfDays)
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
    
}
