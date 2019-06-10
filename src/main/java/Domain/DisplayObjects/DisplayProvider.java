package Domain.DisplayObjects;

import java.util.Objects;

public class DisplayProvider {

    private  Integer providerID;
    private String providerName;

    public DisplayProvider(Integer providerID, String providerName) {
        this.providerID = providerID;
        this.providerName = providerName;
    }

    public Integer getProviderID() {
        return providerID;
    }

    public void setProviderID(Integer providerID) {
        this.providerID = providerID;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof DisplayProvider)){
            return false;
        }
        DisplayProvider other = (DisplayProvider) obj;
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
