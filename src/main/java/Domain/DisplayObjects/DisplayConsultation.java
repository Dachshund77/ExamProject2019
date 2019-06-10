package Domain.DisplayObjects;

import java.time.LocalDate;
import java.util.Objects;

public class DisplayConsultation {
    private Integer consultationID;
    private String consultationName;
    private LocalDate startDate;
    private LocalDate endDate;

    public DisplayConsultation(Integer consultationID, String consultationName, LocalDate startDate, LocalDate endDate) {
        this.consultationID = consultationID;
        this.consultationName = consultationName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getConsultationID() {
        return consultationID;
    }

    public void setConsultationID(Integer consultationID) {
        this.consultationID = consultationID;
    }

    public String getConsultationName() {
        return consultationName;
    }

    public void setConsultationName(String consultationName) {
        this.consultationName = consultationName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof DisplayConsultation)) {
            return false;
        }
        DisplayConsultation other = (DisplayConsultation) obj;
        if (this == other){
            return true;
        }
        //Test all fields
        if (!Objects.equals(this.consultationID, other.consultationID)){
            return false;
        }
        if (!Objects.equals(this.consultationName,other.consultationName)){
            return false;
        }
        if (!Objects.equals(this.startDate,other.startDate)){
            return false;
        }
        return Objects.equals(this.endDate, other.endDate);
    }
}
