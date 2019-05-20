package Domain;

import javafx.beans.property.SimpleStringProperty;

public class Provider {
    private SimpleStringProperty providerName;

    public Provider(String providerName) {
        this.providerName = new SimpleStringProperty(providerName);
    }
}
