package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Interview {

    private final Integer interviewID;
    private String interviewName;
    private Integer productUnderstanding;
    private Integer problemUnderstanding;
    private Integer flexibility;
    private Integer qualityAwareness;
    private Integer cooperation;
    private ArrayList<FinishedEducation> finishedEducations;
    private ArrayList<EducationWish> educationWishes;

    public Interview(Integer interviewID, String interviewName, Integer productUnderstanding, Integer problemUnderstanding, Integer flexibility, Integer qualityAwareness, Integer cooperation, ArrayList<FinishedEducation> finishedEducations, ArrayList<EducationWish> educationWishes) {
        this.interviewID = interviewID;
        this.interviewName = interviewName;
        this.productUnderstanding = productUnderstanding;
        this.problemUnderstanding = problemUnderstanding;
        this.flexibility = flexibility;
        this.qualityAwareness = qualityAwareness;
        this.cooperation = cooperation;
        this.finishedEducations = Objects.requireNonNullElseGet(finishedEducations, ArrayList::new);
        this.educationWishes = Objects.requireNonNullElseGet(educationWishes, ArrayList::new);
    }

    public Integer getInterviewID() {
        return interviewID;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public Integer getProductUnderstanding() {
        return productUnderstanding;
    }

    public Integer getProblemUnderstanding() {
        return problemUnderstanding;
    }

    public Integer getFlexibility() {
        return flexibility;
    }

    public Integer getQualityAwareness() {
        return qualityAwareness;
    }

    public Integer getCooperation() {
        return cooperation;
    }

    public ArrayList<FinishedEducation> getFinishedEducations() {
        return finishedEducations;
    }

    public ArrayList<EducationWish> getEducationWishes() {
        return educationWishes;
    }

    /**
     * A valid interview ID may not be negative.
     *
     * @param interviewID Integer to tested.
     * @return True if Integer is a valid Id
     */
    public static boolean isValidInterviewID(Integer interviewID) {
        if (interviewID != null) {
            return interviewID > 0;
        }
        return true;
    }

    /**
     * Return first reason why an integer is not a valid interviewID
     * A valid interview ID may not be negative.
     *
     * @param interviewID Interger to be tested.
     * @return String with first found reason, null if Integer is valid.
     */
    public static String interviewIDInvalidCause(Integer interviewID) {
        if (interviewID != null && interviewID < 0) {
            return "Interview ID may not be negative!";
        }
        return null;
    }

    /**
     * A valid Interview Name may not be null, empty and must be 30 char or less.
     *
     * @param interviewName String to be tested.
     * @return True of String is valid Interview Name.
     */
    public static boolean isValidInterviewName(String interviewName) {
        return interviewName != null && !interviewName.equals("") && interviewName.length() <= 30;
    }

    /**
     * Return the first reason why an String is not a interview Name.
     * A valid Interview Name may not be null, empty and must be 30 char or less.
     *
     * @param interviewName String to be tested.
     * @return String of the first reason it is not valid, null if String is valid.
     */
    public static String interviewNameInvalidCause(String interviewName) {
        if (interviewName == null) {
            return "Interview Name may not be null!";
        } else if (interviewName.equals("")) {
            return "Interview Name may not be empty!";
        } else if (interviewName.length() > 30) {
            return "Interview Name must be 30 Characters or less!";
        }
        return null;
    }

    /**
     * A valid Product understanding may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidProductUnderstanding(Integer integer) { // FIXME: 30/05/2019 may actually be null but we would need to use exception ahndeling, minor thing but takes time
        return integer != null && integer >= 0 && integer <= 5;
    }

    /**
     * Return the first reason why an Integer is not a valid product understanding.
     * A valid Product understanding may not be null, negative or bigger than 5.
     *
     * @param integer Integer to be tested.
     * @return String with the first reason it is not valid, null if valid.
     */
    public static String productUnderstandingInvalidCause(Integer integer) {
        if (integer == null) {
            return "Product understanding may noy be null!";
        } else if (integer < 0) {
            return "Product understanding may not be negative!";
        } else if (integer > 5) {
            return "Product understanding must be 5 or less!";
        }
        return null;
    }


    /**
     * A valid Problem understanding may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidProblemUnderstanding(Integer integer) {
        return integer != null && integer >= 0 && integer <= 5;
    }

    /**
     * Return the first reason why an Integer is not a valid Problem understanding.
     * A valid Problem understanding may not be null, negative or bigger than 5.
     *
     * @param integer Integer to be tested.
     * @return String with the first reason it is not valid, null if valid.
     */
    public static String problemUnderstandingInvalidCause(Integer integer) {
        if (integer == null) {
            return "Problem understanding may noy be null!";
        } else if (integer < 0) {
            return "Problem understanding may not be negative!";
        } else if (integer > 5) {
            return "Problem understanding must be 5 or less!";
        }
        return null;
    }


    /**
     * A valid flexibility score may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidFlexibility(Integer integer) {
        return integer != null && integer >= 0 && integer <= 5;
    }

    /**
     * Return the first reason why an Integer is not a valid flexibility score.
     * A valid flexibility score may not be null, negative or bigger than 5.
     *
     * @param integer Integer to be tested.
     * @return String with the first reason it is not valid, null if valid.
     */
    public static String flexibilityInvalidCause(Integer integer) {
        if (integer == null) {
            return "Flexibility may noy be null!";
        } else if (integer < 0) {
            return "Flexibility may not be negative!";
        } else if (integer > 5) {
            return "Flexibility must be 5 or less!";
        }
        return null;
    }


    /**
     * A valid quality awareness score may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidQualityAwareness(Integer integer) {
        return integer != null && integer >= 0 && integer <= 5;
    }

    /**
     * Return the first reason why an Integer is not a valid quality awareness score.
     * A valid quality awareness score may not be null, negative or bigger than 5.
     *
     * @param integer Integer to be tested.
     * @return String with the first reason it is not valid, null if valid.
     */
    public static String qualityAwarenessInvalidCause(Integer integer) {
        if (integer == null) {
            return "Quality Awareness may noy be null!";
        } else if (integer < 0) {
            return "Quality Awareness  may not be negative!";
        } else if (integer > 5) {
            return "Quality Awareness  must be 5 or less!";
        }
        return null;
    }


    /**
     * A valid cooperation score may not be null, negative or bigger than 5.
     *
     * @param integer Cooperation score to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidCooperation(Integer integer) {
        return integer != null && integer >= 0 && integer <= 5;
    }

    /**
     * Return the first reason why an Integer is not a valid cooperation score.
     * A valid cooperation score may not be null, negative or bigger than 5.
     *
     * @param integer Integer to be tested.
     * @return String with the first reason it is not valid, null if valid.
     */
    public static String cooperationInvalidCause(Integer integer) {
        if (integer == null) {
            return "Cooperation may noy be null!";
        } else if (integer < 0) {
            return "Cooperation may not be negative!";
        } else if (integer > 5) {
            return "Cooperation must be 5 or less!";
        }
        return null;
    }


}
