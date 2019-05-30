package Domain;

import javafx.beans.property.SimpleStringProperty;

public class Provider {
    private final Integer providerID;
    private String providerName;

    public Provider(Integer providerID, String providerName) {
        this.providerID = providerID;
        this.providerName = providerName;
    }

    public Integer getProviderID() {
        return providerID;
    }

    public String getProviderName() {
        return providerName;
    }

    /**
     * A valid Id my not be negative.
     *
     * @param id Integer to be tested.
     * @return True if Integer is valid id.
     */
    public static boolean isValidProviderID(Integer id) {
        if (id != null) {
            return id > 0;
        }
        return true;
    }

    /**
     * Returns the first reason why an Integer is not a valid Provider ID.
     * A valid Id my not be negative.
     *
     * @param id Integer to be tested.
     * @return String with first reason or null if valid.
     */
    public static String invalidProviderIDCause(Integer id) {
        if (id != null && id < 0) {
            return "Provider ID may not be negative";
        }
        return null;
    }

    /**
     * A valid Provider name may not be null, must not be empty and must be 30 or less characters.
     *
     * @param providerName String to be tested as Provider Name.
     * @return True if valid Provider Name
     */
    public static boolean isValidProviderName(String providerName) {
        return providerName != null && !providerName.equals("") && providerName.length() <= 30;
    }

    /**
     * Return the first reason why an String is not a valid Provider Name.
     *
     * @param providerName String to be tested.
     * @return String with first reason or null if valid.
     */
    public static String providerNameInvalidCause(String providerName) {
        if (providerName == null) {
            return "Provider Name may not be Null!";
        } else if (providerName.equals("")) {
            return "ProviderName may not be empty!";
        } else if (providerName.length() > 30) {
            return "Provider Name must be 30 letters or less";
        }
        return null;
    }
}
