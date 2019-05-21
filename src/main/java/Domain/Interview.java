package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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
}
