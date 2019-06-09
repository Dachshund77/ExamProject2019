package Domain.DisplayObjects;

import java.util.Objects;

public class DisplayEducation {
    private  Integer amuNr;
    private String educationName;
    private String description;
    private Integer noOfDays;
    private String providerName;

    public DisplayEducation(Integer amuNr, String educationName, String description, Integer noOfDays, String providerName) {
        this.amuNr = amuNr;
        this.educationName = educationName;
        this.description = description;
        this.noOfDays = noOfDays;
        this.providerName = providerName;
    }

    public Integer getAmuNr() {
        return amuNr;
    }

    public void setAmuNr(Integer amuNr) {
        this.amuNr = amuNr;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof DisplayEducation)) {
            return false;
        }
        DisplayEducation other = (DisplayEducation) obj;
        if (this == other){
            return true;
        }
        //Test all fields
        if (!Objects.equals(this.amuNr,other.amuNr)){
            return false;
        }
        if (!Objects.equals(this.educationName,other.educationName)){
            return false;
        }
        if (!Objects.equals(this.description,other.description)){
            return false;
        }
        if (!Objects.equals(this.noOfDays,other.noOfDays)){
            return false;
        }
        return Objects.equals(this.providerName, other.providerName);

    }
}
