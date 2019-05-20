package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.util.ArrayList;

public class Education {
    private SimpleIntegerProperty amuNr;
    private SimpleStringProperty educationName;
    private SimpleStringProperty description;
    private SimpleIntegerProperty noOfDays;
    private ArrayList<Date> dates;
    private Provider provider;

    public Education(Integer amuNr, String educationName, String description, Integer noOfDays, ArrayList<Date> dates, Provider provider) {
        this.amuNr = new SimpleIntegerProperty(amuNr);
        this.educationName = new SimpleStringProperty(educationName);
        this.description = new SimpleStringProperty(description);
        this.noOfDays = new SimpleIntegerProperty(noOfDays);
        this.dates = dates;
        this.provider = provider;
    }
}
