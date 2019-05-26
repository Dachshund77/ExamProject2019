package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Company {

    private SimpleIntegerProperty companyID;
    private SimpleStringProperty cvrNr;
    private SimpleStringProperty companyName;
    private ArrayList<Consultation> consultations;
    private ArrayList<Education> educationList;

    public Company(Integer companyID, String cvrNr, String companyName, ArrayList<Consultation> consultations, ArrayList<Education> educationList) {
        if (companyID != null) {
            this.companyID = new SimpleIntegerProperty(companyID);
        } else {
            this.companyID = null;
        }

        this.cvrNr = new SimpleStringProperty(cvrNr);
        this.companyName = new SimpleStringProperty(companyName);

        this.consultations = Objects.requireNonNullElseGet(consultations, ArrayList::new);
        this.educationList = Objects.requireNonNullElseGet(educationList, ArrayList::new);
    }

    public Integer getCompanyID() {
        if (companyID == null) {
            return null;
        }
        return companyID.get();
    }

    public String getCvrNr() {
        if (cvrNr.get().equals("")) {
            return null;
        }
        return cvrNr.get();
    }

    public SimpleStringProperty cvrNrProperty() {
        return cvrNr;
    }

    public String getCompanyName() {
        if (companyName.get().equals("")){
            return null;
        }
        return companyName.get();
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public ArrayList<Consultation> getConsultations() {
        return consultations;
    }

    public ArrayList<Education> getEducationList() {
        return educationList;
    }
}
