package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Date;
import java.util.ArrayList;


public class Consultation {

    private SimpleIntegerProperty consultationID;
    private SimpleStringProperty consultationName;
    private Date startDate;
    private Date endDate;
    private ArrayList<Employee> employees;

    public Consultation(Integer consultationID, String consultationName, Date startDate, Date endDate, ArrayList<Employee> employees) {
        this.consultationID = new SimpleIntegerProperty(consultationID);
        this.consultationName = new SimpleStringProperty(consultationName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.employees = employees;
    }
}
