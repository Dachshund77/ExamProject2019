package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Interview {

    private SimpleIntegerProperty interviewID;
    private SimpleStringProperty interviewName;
    private SimpleIntegerProperty productUnderstanding;
    private SimpleIntegerProperty problemUnderstanding;
    private SimpleIntegerProperty flexibility;
    private SimpleIntegerProperty qualityAwareness;
    private SimpleIntegerProperty cooperation;
    private ArrayList<FinishedEducation> finishedEducations;
    private ArrayList<EducationWish> educationWishes;

    public Interview(Integer interviewID, String interviewName, Integer productUnderstanding, Integer problemUnderstanding, Integer flexibility, Integer qualityAwareness, Integer cooperation, ArrayList<FinishedEducation> finishedEducations, ArrayList<EducationWish> educationWishes) {
        if (interviewID != null) {
            this.interviewID = new SimpleIntegerProperty(interviewID);
        }else {
            this.interviewID = null;
        }

        this.interviewName = new SimpleStringProperty(interviewName);

        if (productUnderstanding != null) {
            this.productUnderstanding = new SimpleIntegerProperty(productUnderstanding);
        } else {
            this.productUnderstanding = new SimpleIntegerProperty(-1);
        }

        if (problemUnderstanding != null) {
            this.problemUnderstanding = new SimpleIntegerProperty(problemUnderstanding);
        } else {
            this.problemUnderstanding = new SimpleIntegerProperty(-1);
        }

        if (flexibility != null) {
            this.flexibility = new SimpleIntegerProperty(flexibility);
        } else {
            this.flexibility = new SimpleIntegerProperty(-1);
        }

        if (qualityAwareness != null) {
            this.qualityAwareness = new SimpleIntegerProperty(qualityAwareness);
        } else {
            this.qualityAwareness = new SimpleIntegerProperty(-1);
        }

        if (cooperation != null) {
            this.cooperation = new SimpleIntegerProperty(cooperation);
        } else {
            this.cooperation = new SimpleIntegerProperty(-1);
        }
        this.finishedEducations = Objects.requireNonNullElseGet(finishedEducations,ArrayList::new);
        this.educationWishes = Objects.requireNonNullElseGet(educationWishes,ArrayList::new);
    }

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public Interview(ResultSet rs) throws SQLException {
        this.interviewID = new SimpleIntegerProperty(rs.getInt("fld_InterviewID"));
        this.interviewName = new SimpleStringProperty(rs.getString("fld_InterviewName"));
        this.productUnderstanding = new SimpleIntegerProperty(rs.getInt("fld_ProductUnderstanding"));
        this.problemUnderstanding = new SimpleIntegerProperty(rs.getInt("fld_ProblemUnderstanding"));
        this.flexibility = new SimpleIntegerProperty(rs.getInt("fld_Flexibility"));
        this.qualityAwareness = new SimpleIntegerProperty(rs.getInt("fld_QualityAwareness"));
        this.cooperation = new SimpleIntegerProperty(rs.getInt("fld_Cooperation"));
    }

    public Integer getInterviewID() {
        if (interviewID == null){
            return null;
        }
        return interviewID.get();
    }

    public String getInterviewName() {
        if (interviewName.get().equals("")){
            return null;
        }
        return interviewName.get();
    }

    public SimpleStringProperty interviewNameProperty() {
        return interviewName;
    }

    public Integer getProductUnderstanding() {
        if (productUnderstanding.get() == -1){
            return null;
        }
        return productUnderstanding.get();
    }

    public SimpleIntegerProperty productUnderstandingProperty() {
        return productUnderstanding;
    }

    public Integer getProblemUnderstanding() {
        if (problemUnderstanding.get() == -1) {
            return null;
        }
        return problemUnderstanding.get();
    }

    public SimpleIntegerProperty problemUnderstandingProperty() {
        return problemUnderstanding;
    }

    public Integer getFlexibility() {
        if (flexibility.get() == -1){
            return null;
        }
        return flexibility.get();
    }

    public SimpleIntegerProperty flexibilityProperty() {
        return flexibility;
    }

    public Integer getQualityAwareness() {
        if (qualityAwareness.get() == -1){
            return null;
        }
        return qualityAwareness.get();
    }

    public SimpleIntegerProperty qualityAwarenessProperty() {
        return qualityAwareness;
    }

    public Integer getCooperation() {
        if (cooperation.get() == -1){
            return null;
        }
        return cooperation.get();
    }

    public SimpleIntegerProperty cooperationProperty() {
        return cooperation;
    }

    public ArrayList<FinishedEducation> getFinishedEducations() {
        return finishedEducations;
    }

    public ArrayList<EducationWish> getEducationWishes() {
        return educationWishes;
    }

}
