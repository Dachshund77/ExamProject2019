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

    public String getConsultationName() {
        if (consultationName.get().equals("")) {
            return null;
        }
        return consultationName.get();
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
