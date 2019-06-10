package Domain.DisplayObjects;

import java.util.Objects;

public class DisplayInterview {

    private Integer interviewID;
    private String interviewName;
    private Integer productUnderstanding;
    private Integer problemUnderstanding;
    private Integer flexibility;
    private Integer qualityAwareness;
    private Integer cooperation;

    public DisplayInterview(Integer interviewID, String interviewName, Integer productUnderstanding, Integer problemUnderstanding, Integer flexibility, Integer qualityAwareness, Integer cooperation) {
        this.interviewID = interviewID;
        this.interviewName = interviewName;
        this.productUnderstanding = productUnderstanding;
        this.problemUnderstanding = problemUnderstanding;
        this.flexibility = flexibility;
        this.qualityAwareness = qualityAwareness;
        this.cooperation = cooperation;
    }

    public Integer getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(Integer interviewID) {
        this.interviewID = interviewID;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public Integer getProductUnderstanding() {
        return productUnderstanding;
    }

    public void setProductUnderstanding(Integer productUnderstanding) {
        this.productUnderstanding = productUnderstanding;
    }

    public Integer getProblemUnderstanding() {
        return problemUnderstanding;
    }

    public void setProblemUnderstanding(Integer problemUnderstanding) {
        this.problemUnderstanding = problemUnderstanding;
    }

    public Integer getFlexibility() {
        return flexibility;
    }

    public void setFlexibility(Integer flexibility) {
        this.flexibility = flexibility;
    }

    public Integer getQualityAwareness() {
        return qualityAwareness;
    }

    public void setQualityAwareness(Integer qualityAwareness) {
        this.qualityAwareness = qualityAwareness;
    }

    public Integer getCooperation() {
        return cooperation;
    }

    public void setCooperation(Integer cooperation) {
        this.cooperation = cooperation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof DisplayInterview)) {
            return false;
        }
        DisplayInterview other = (DisplayInterview) obj;
        if (this == other){
            return true;
        }
        //Test all fields
        if (!Objects.equals(this.interviewID,other.interviewID)){
            return false;
        }
        if (!Objects.equals(this.interviewName,other.interviewName)){
            return false;
        }
        if (!Objects.equals(this.productUnderstanding,other.productUnderstanding)){
            return false;
        }
        if (!Objects.equals(this.problemUnderstanding,other.problemUnderstanding)){
            return false;
        }
        if (!Objects.equals(this.flexibility,other.flexibility)){
            return false;
        }
        if (!Objects.equals(this.qualityAwareness,other.qualityAwareness)){
            return false;
        }
        return Objects.equals(this.cooperation, other.cooperation);
    }
}
