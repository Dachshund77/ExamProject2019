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

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     *
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public Education(ResultSet rs) throws SQLException {
        this.amuNr = new SimpleIntegerProperty(rs.getInt("fld_AmuNR"));
        this.educationName = new SimpleStringProperty(rs.getString("fld_EducationName"));
        this.description = new SimpleStringProperty(rs.getString("fld_Description"));
        this.noOfDays = new SimpleIntegerProperty(rs.getInt("fld_NoOfDays"));
        LocalDate localDate = rs.getDate("fld_Date").toLocalDate();
        this.dates = new ArrayList<>();
        this.dates.add(localDate);
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
}
