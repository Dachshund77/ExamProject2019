package Domain;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Provider implements Domain{
    private final Integer providerID;
    private String providerName;

    private static final int PROVIDER_NAME_MAX_LENGTH = 30;

    public Provider(Integer providerID, String providerName) {
        this.providerID = providerID;
        setProviderName(providerName);
    }

    public Integer getProviderID() {
        return providerID;
    }

    public String getProviderName() {
        return providerName;
    }

    public static int getProviderNameMaxLength() {
        return PROVIDER_NAME_MAX_LENGTH;
    }

    /**
     * Converts empty String to null.
     * @param providerName new provider name.
     */
    public void setProviderName(String providerName) {
        if (providerName == null || providerName.trim().isEmpty()){
            this.providerName = null;
        } else {
            this.providerName = providerName;
        }
    }

    /**
     * A valid Id my not be negative.
     *
     * @param id Integer to be tested.
     * @return True if Integer is valid id.
     */
    public static boolean isValidProviderID(Integer id) {
        return invalidProviderIDCause(id) == null;
    }

    public static boolean isValidProviderID(String id){
        return invalidProviderIDCause(id) == null;
    }

    /**
     * Returns the first reason why an Integer is not a valid Provider ID.
     * A valid Id my not be negative.
     *
     * @param id Integer to be tested.
     * @return String with first reason or null if valid.
     */
    public static String invalidProviderIDCause(Integer id) {
        if (id != null && id <= 0) {
            return "Provider ID must be positive!";
        }
        return null;
    }

    /**
     * Returns the first reason why an Integer is not a valid Provider ID.
     * A valid Id my not be negative.
     *
     * @param id Integer to be tested.
     * @return String with first reason or null if valid.
     */
    public static String invalidProviderIDCause(String id) {
        if (id == null){
            return null;
        }
        if (id.trim().isEmpty()){
            return null;
        }
        try{
            return invalidProviderIDCause(Integer.parseInt(id));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    /**
     * A valid Provider name may not be null, must not be empty and must be 30 or less characters.
     *
     * @param providerName String to be tested as Provider Name.
     * @return True if valid Provider Name
     */
    public static boolean isValidProviderName(String providerName) {
        return providerNameInvalidCause(providerName) == null;
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
        } else if (providerName.trim().isEmpty()) {
            return "ProviderName may not be empty!";
        } else if (providerName.length() > PROVIDER_NAME_MAX_LENGTH) {
            return "Provider Name must be "+PROVIDER_NAME_MAX_LENGTH+" letters or less";
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof Provider)){
            return false;
        }
        Provider other = (Provider) obj;
        if (this == other){
            return true;
        }
        //Test all fields
        if (!Objects.equals(this.providerID,other.providerID)){
            return false;
        }
        return Objects.equals(this.providerName,other.providerName);
    }
}
