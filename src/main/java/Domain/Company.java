package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Company {

    private SimpleIntegerProperty companyID;
    private SimpleStringProperty cvrNr;
    private SimpleStringProperty companyName;
    private ArrayList<Consultation> consultations;
    private EducationList educationList;

    public Company(Integer companyID, String cvrNr, String companyName, ArrayList<Consultation> consultations, EducationList educationList) {
        this.companyID = new SimpleIntegerProperty(companyID);
        this.cvrNr = new SimpleStringProperty(cvrNr);
        this.companyName = new SimpleStringProperty(companyName);
        this.consultations = consultations;
        this.educationList = educationList;
    }


}
