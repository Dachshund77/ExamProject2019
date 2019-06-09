package Domain.DisplayObjects;

import java.util.Objects;

public class DisplayCompany {

    private Integer companyID;
    private String cvrNr;
    private String companyName;


    public DisplayCompany(Integer companyID, String cvrNr, String companyName) {
        this.companyID = companyID;
        this.cvrNr = cvrNr;
        this.companyName = companyName;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getCvrNr() {
        return cvrNr;
    }

    public void setCvrNr(String cvrNr) {
        this.cvrNr = cvrNr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof DisplayCompany)) {
            return false;
        }
        DisplayCompany other = (DisplayCompany) obj;
        if (this == other) {
            return true;
        }
        //Test all fields
        if (!Objects.equals(this.companyID, other.companyID)) {
            return false;
        }

        if (!Objects.equals(this.cvrNr, other.cvrNr)) {
            return false;
        }

        return Objects.equals(this.companyName, other.companyName);
    }
}
