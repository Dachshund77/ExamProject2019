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

    public boolean isValidInterviewID(){
        if (interviewID.get() < 0){
            return false;
        }

        return true;
    }

    public String interviewIDInvalidCause(){
        if (!isValidInterviewID()){
            String cause = "interview ID is not entered";
            return cause;
        }
        return interviewIDInvalidCause();
    }

    public boolean isValidInterviewName(){
        if (interviewName.get().equals("") || interviewName.get().length() >30){
            return false;
        }
        return true;
    }

    public String interviewNameInvalidCause(){
        if (!isValidInterviewName()){
            String cause = "Name is not entered or name is over 30 characters";
        }
        return interviewIDInvalidCause();
    }

    public boolean isValidProductUnderstanding(){
        if (productUnderstanding.get() < 0 || productUnderstanding.get() > 5){
            return false;
        }
        else if (productUnderstanding == null) {
            return true;
        }
        return true;
    }

    public String productUnderstandingInvalidCause(){
        if (!isValidProductUnderstanding()){
            String cause = "Product number is not between 1 and 5";
            return cause;
        }
        return
                productUnderstandingInvalidCause();
    }


    public boolean isValidProblemUnderstanding(){
        if (problemUnderstanding.get() < 0 || problemUnderstanding.get() > 5){
            return false;
        }
        else if (problemUnderstanding == null) {
            return true;
        }
        return true;
    }

    public String problemUnderstandingInvalidCause(){
        if (!isValidProblemUnderstanding()){
            String cause = "Problem number is not between 1 and 5";
            return cause;
        }
        return
                problemUnderstandingInvalidCause();
    }


    public boolean isValidFlexibility(){
        if (flexibility.get() < 0 || flexibility.get() > 5){
            return false;
        }
        else if (flexibility == null) {
            return true;
        }
        return true;
    }

    public String flexibilityInvalidCause(){
        if (!isValidFlexibility()){
            String cause = "Flexibility number is not between 1 and 5";
            return cause;
        }
        return
                flexibilityInvalidCause();
    }


    public boolean isValidQualityAwareness(){
        if (qualityAwareness.get() < 0 || qualityAwareness.get() > 5){
            return false;
        }
        else if (qualityAwareness == null) {
            return true;
        }

        return true;
    }

    public String qualityAwarenessInvalidCause(){
        if (!isValidQualityAwareness()){
            String cause = "Quality awareness number is not between 1 and 5";
            return cause;
        }
        return
                qualityAwarenessInvalidCause();
    }


    public boolean isValidCooperation(){
        if (cooperation.get() < 0 || cooperation.get() > 5){
            return false;
        }
        else if (cooperation == null) {
            return true;
        }
        return true;
    }

    public String cooperationInvalidCause(){
        if (!isValidCooperation()){
            String cause = "Cooperation number is not between 1 and 5";
            return cause;
        }
        return
                cooperationInvalidCause();
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
