package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Provider {
    private SimpleIntegerProperty providerID = null;
    private SimpleStringProperty providerName;

    public Provider(Integer providerID, String providerName) {
        if (providerID != null) {
            this.providerID = new SimpleIntegerProperty(providerID);
        }
        this.providerName = new SimpleStringProperty(providerName);
    }

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public Provider(ResultSet rs) throws SQLException {
        this.providerID = new SimpleIntegerProperty(rs.getInt("fld_ProviderID"));
        this.providerName = new SimpleStringProperty(rs.getString("fld_ProviderName"));
    }

    public Integer getProviderID() {
        if (providerID == null){
            return null;
        }
        return providerID.get();
    }

    public SimpleIntegerProperty providerIDProperty() {
        return providerID;
    }

    public String getProviderName() {
        return providerName.get();
    }

    public SimpleStringProperty providerNameProperty() {
        return providerName;
    }
}
