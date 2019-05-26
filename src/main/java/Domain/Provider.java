package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Provider {
    private SimpleIntegerProperty providerID;
    private SimpleStringProperty providerName;

    public Provider(Integer providerID, String providerName) {
        if (providerID != null) {
            this.providerID = new SimpleIntegerProperty(providerID);
        } else {
            this.providerID = null;
        }
        this.providerName = new SimpleStringProperty(providerName);
    }

    public Integer getProviderID() {
        if (providerID == null){
            return null;
        }
        return providerID.get();
    }



    /**
     * Helper method that will return null if the value is an empty String
     * @return String value of providerName
     */
    public String getProviderName() {
        if (providerName.get().equals("")){
            return null;
        }
        return providerName.get();
    }

    public SimpleStringProperty providerNameProperty() {
        return providerName;
    }
}
