package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Interview {

    private SimpleIntegerProperty interviewID;
    private SimpleStringProperty interviewName;
    private SimpleIntegerProperty productUnderstanding;
    private SimpleIntegerProperty problemUnderstanding;
    private SimpleIntegerProperty flexibility;
    private SimpleIntegerProperty qualityAwarness;
    private SimpleIntegerProperty cooperation;
    private ArrayList<FinishedEducation> finishedEducations;
    private ArrayList<EducationWish> educationWishes;
    private Employee employee;

    public Interview(Integer interviewID, String interviewName, Integer productUnderstanding, Integer problemUnderstanding, Integer flexibility, Integer qualityAwareness, Integer cooperation, ArrayList<FinishedEducation> finishedEducations, ArrayList<EducationWish> educationWishes, Employee employee) {
        this.interviewID = new SimpleIntegerProperty(interviewID);
        this.interviewName = new SimpleStringProperty(interviewName);
        this.productUnderstanding = new SimpleIntegerProperty(productUnderstanding);
        this.problemUnderstanding = new SimpleIntegerProperty(problemUnderstanding);
        this.flexibility = new SimpleIntegerProperty(flexibility);
        this.qualityAwarness = new SimpleIntegerProperty(qualityAwareness);
        this.cooperation = new SimpleIntegerProperty(cooperation);
        this.finishedEducations = finishedEducations;
        this.educationWishes = educationWishes;
        this.employee = employee;
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
        this.qualityAwarness = new SimpleIntegerProperty(rs.getInt("fld_QualityAwareness"));
        this.cooperation = new SimpleIntegerProperty(rs.getInt("fld_Cooperation"));
    }
}
