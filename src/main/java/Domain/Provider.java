package Domain;

import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Provider {
    private SimpleStringProperty providerName;

    public Provider(String providerName) {
        this.providerName = new SimpleStringProperty(providerName);
    }

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public Provider(ResultSet rs) throws SQLException {
        this.providerName = new SimpleStringProperty(rs.getString("fld_ProviderName"));
    }
}
