package Domain.DomainObjects;

import java.util.ArrayList;
import java.util.Objects;

public class Interview implements Domain{

    private final Integer interviewID;
    private String interviewName;
    private Integer productUnderstanding;
    private Integer problemUnderstanding;
    private Integer flexibility;
    private Integer qualityAwareness;
    private Integer cooperation;
    private ArrayList<FinishedEducation> finishedEducations;
    private ArrayList<EducationWish> educationWishes;

    private static final int INTERVIEW_NAME_MAX_LENGTH = 30;
    private static final int QUALITY_MAX_VALUE = 5;

    public Interview(Integer interviewID, String interviewName, Integer productUnderstanding, Integer problemUnderstanding, Integer flexibility, Integer qualityAwareness, Integer cooperation, ArrayList<FinishedEducation> finishedEducations, ArrayList<EducationWish> educationWishes) {
        this.interviewID = interviewID;
        setInterviewName(interviewName);
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

    public static int getInterviewNameMaxLength() {
        return INTERVIEW_NAME_MAX_LENGTH;
    }

    public static int getQualityMaxValue() {
        return QUALITY_MAX_VALUE;
    }

    /**
     * Converts empty String to null.
     * @param interviewName new interview Name.
     */
    public void setInterviewName(String interviewName) {
        if (interviewName == null || interviewName.trim().isEmpty()){
            this.interviewName = null;
        } else {
            this.interviewName = interviewName;
        }
    }

    public void setFinishedEducations(ArrayList<FinishedEducation> finishedEducations) {
        this.finishedEducations = finishedEducations;
    }

    public void setEducationWishes(ArrayList<EducationWish> educationWishes) {
        this.educationWishes = educationWishes;
    }

    /**
     * A valid interview ID may not be negative.
     *
     * @param interviewID Integer to tested.
     * @return True if Integer is a valid Id
     */
    public static boolean isValidInterviewID(Integer interviewID) {
        return interviewIDInvalidCause(interviewID) == null;
    }

    public static boolean isValidInterviewID(String interviewID) {
        return interviewIDInvalidCause(interviewID) == null;
    }

    /**
     * Return first reason why an integer is not a valid interviewID
     * A valid interview ID may not be negative.
     *
     * @param interviewID Interger to be tested.
     * @return String with first found reason, null if Integer is valid.
     */
    public static String interviewIDInvalidCause(Integer interviewID) {
        if (interviewID != null && interviewID <= 0) {
            return "Interview ID must be positive!";
        }
        return null;
    }

    /**
     * Return first reason why an integer is not a valid interviewID
     * A valid interview ID may not be negative.
     *
     * @param interviewID Interger to be tested.
     * @return String with first found reason, null if Integer is valid.
     */
    public static String interviewIDInvalidCause(String interviewID) {
        if (interviewID == null){
            return null;
        }
        if (interviewID.trim().isEmpty()){
            return null;
        }
        try{
            return interviewIDInvalidCause(Integer.parseInt(interviewID));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    /**
     * A valid Interview Name may not be null, empty and must be 30 char or less.
     *
     * @param interviewName String to be tested.
     * @return True of String is valid Interview Name.
     */
    public static boolean isValidInterviewName(String interviewName) {
        return interviewNameInvalidCause(interviewName) == null;
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
        } else if (interviewName.trim().isEmpty()) {
            return "Interview Name may not be empty!";
        } else if (interviewName.length() > INTERVIEW_NAME_MAX_LENGTH) {
            return "Interview Name must be "+INTERVIEW_NAME_MAX_LENGTH+" Characters or less!";
        }
        return null;
    }

    /**
     * A valid Product understanding may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidProductUnderstanding(Integer integer) {
        return productUnderstandingInvalidCause(integer) == null;
    }

    /**
     * A valid Product understanding may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidProductUnderstanding(String integer) {
        return productUnderstandingInvalidCause(integer) == null;
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
            return null;
        } else if (integer < 0) {
            return "Product understanding may not be negative!";
        } else if (integer > QUALITY_MAX_VALUE) {
            return "Product understanding must be "+QUALITY_MAX_VALUE+" or less!";
        }
        return null;
    }

    /**
     * Return the first reason why an Integer is not a valid product understanding.
     * A valid Product understanding may not be null, negative or bigger than 5.
     *
     * @param integer Integer to be tested.
     * @return String with the first reason it is not valid, null if valid.
     */
    public static String productUnderstandingInvalidCause(String integer) {
        if (integer == null){
            return null;
        }
        if (integer.trim().isEmpty()){
            return null;
        }
        try{
            return productUnderstandingInvalidCause(Integer.parseInt(integer));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    /**
     * A valid Problem understanding may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidProblemUnderstanding(Integer integer) {
        return problemUnderstandingInvalidCause(integer) == null;
    }

    /**
     * A valid Problem understanding may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidProblemUnderstanding(String integer) {
        return problemUnderstandingInvalidCause(integer) == null;
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
            return null;
        } else if (integer < 0) {
            return "Problem understanding may not be negative!";
        } else if (integer > QUALITY_MAX_VALUE) {
            return "Problem understanding must be "+QUALITY_MAX_VALUE+" or less!";
        }
        return null;
    }

    /**
     * Return the first reason why an Integer is not a valid Problem understanding.
     * A valid Problem understanding may not be null, negative or bigger than 5.
     *
     * @param integer Integer to be tested.
     * @return String with the first reason it is not valid, null if valid.
     */
    public static String problemUnderstandingInvalidCause(String integer) {
        if (integer == null){
            return null;
        }
        if (integer.trim().isEmpty()){
            return null;
        }
        try{
            return problemUnderstandingInvalidCause(Integer.parseInt(integer));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }


    /**
     * A valid flexibility score may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidFlexibility(Integer integer) {
        return flexibilityInvalidCause(integer) == null;
    }

    /**
     * A valid flexibility score may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidFlexibility(String integer) {
        return flexibilityInvalidCause(integer) == null;
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
            return null;
        } else if (integer < 0) {
            return "Flexibility may not be negative!";
        } else if (integer > QUALITY_MAX_VALUE) {
            return "Flexibility must be "+QUALITY_MAX_VALUE+" or less!";
        }
        return null;
    }

    /**
     * Return the first reason why an Integer is not a valid flexibility score.
     * A valid flexibility score may not be null, negative or bigger than 5.
     *
     * @param integer Integer to be tested.
     * @return String with the first reason it is not valid, null if valid.
     */
    public static String flexibilityInvalidCause(String integer) {
        if (integer == null){
            return null;
        }
        if (integer.trim().isEmpty()){
            return null;
        }
        try{
            return flexibilityInvalidCause(Integer.parseInt(integer));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }


    /**
     * A valid quality awareness score may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidQualityAwareness(Integer integer) {
        return qualityAwarenessInvalidCause(integer) == null;
    }

    /**
     * A valid quality awareness score may not be null, negative or bigger than 5.
     *
     * @param integer Product understanding to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidQualityAwareness(String integer) {
        return qualityAwarenessInvalidCause(integer) == null;
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
            return null;
        } else if (integer < 0) {
            return "Quality Awareness  may not be negative!";
        } else if (integer > QUALITY_MAX_VALUE) {
            return "Quality Awareness  must be "+QUALITY_MAX_VALUE+" or less!";
        }
        return null;
    }

    /**
     * Return the first reason why an Integer is not a valid quality awareness score.
     * A valid quality awareness score may not be null, negative or bigger than 5.
     *
     * @param integer Integer to be tested.
     * @return String with the first reason it is not valid, null if valid.
     */
    public static String qualityAwarenessInvalidCause(String integer) {
        if (integer == null){
            return null;
        }
        if (integer.trim().isEmpty()){
            return null;
        }
        try{
            return qualityAwarenessInvalidCause(Integer.parseInt(integer));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }


    /**
     * A valid cooperation score may not be null, negative or bigger than 5.
     *
     * @param integer Cooperation score to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidCooperation(Integer integer) {
        return cooperationInvalidCause(integer) == null;
    }

    /**
     * A valid cooperation score may not be null, negative or bigger than 5.
     *
     * @param integer Cooperation score to be tested.
     * @return True if Integer is valid
     */
    public static boolean isValidCooperation(String integer) {
        return cooperationInvalidCause(integer) == null;
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
            return null;
        } else if (integer < 0) {
            return "Cooperation may not be negative!";
        } else if (integer > QUALITY_MAX_VALUE) {
            return "Cooperation must be "+QUALITY_MAX_VALUE+" or less!";
        }
        return null;
    }

    /**
     * Return the first reason why an Integer is not a valid cooperation score.
     * A valid cooperation score may not be null, negative or bigger than 5.
     *
     * @param integer Integer to be tested.
     * @return String with the first reason it is not valid, null if valid.
     */
    public static String cooperationInvalidCause(String integer) {
        if (integer == null){
            return null;
        }
        if (integer.trim().isEmpty()){
            return null;
        }
        try{
            return cooperationInvalidCause(Integer.parseInt(integer));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Interview)) {
            return false;
        }
        Interview other = (Interview) obj;
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
        if (!Objects.equals(this.cooperation,other.cooperation)){
            return false;
        }
        if (!Objects.equals(this.finishedEducations,other.finishedEducations)){
            return false;
        }
        return Objects.equals(this.educationWishes,other.educationWishes);
    }
}
