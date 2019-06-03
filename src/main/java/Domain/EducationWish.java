package Domain;

import javafx.beans.property.SimpleIntegerProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationWish {
    private final Integer educationWishID;
    private Education education;
    private Integer priority;

    public EducationWish(Integer educationWishID, Education education, Integer priority) {
        this.educationWishID = educationWishID;
        this.education = education;
        this.priority = priority;
    }

    public Integer getEducationWishID() {
      return educationWishID;
    }

    public Education getEducation() {
        return education;
    }

    public Integer getPriority() {
      return priority;
    }

    /**
     * checks if educationID isnt negative
     * @param educationWishID
     * @return
     */
    public static boolean isValidEducationWishID (Integer educationWishID)
    {
       return educationWishIDInvalidCause(educationWishID) == null;
    }

    /**
     * checks if educationID isnt NULL and negative
     * @param educationWishID
     * @return
     */
    public static boolean isValidEducationWishID (String educationWishID)
    {
        return educationWishIDInvalidCause(educationWishID) == null;
    }

    /**
     * Throws an error if:
     * educationWishID = Negative
     * @param educationWishID
     * @return
     */
    public static String educationWishIDInvalidCause (Integer educationWishID)
    {
        if (educationWishID != null && educationWishID < 0){
            return "EducationWishID cant be null";
        }
        return null;
    }

    /**
     * Throws an error if:
     * educationWishID = Negative
     * @param educationWishID
     * @return
     */
    public static String educationWishIDInvalidCause (String educationWishID) {
        try{
            return educationWishIDInvalidCause(Integer.parseInt(educationWishID));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }

    /**
     * checks if priority is within range and not null
     * @param priority
     * @return
     */
    public static boolean isValidPriority(Integer priority)
    {
       return priorityInvalidCause(priority)==null;
    }

    /**
     * checks if priority is within range and not null
     * @param priority
     * @return
     */
    public static boolean isValidPriority(String priority)
    {
        return priorityInvalidCause(priority)==null;
    }

    /**
     * Throws an error if:
     * priority = NULL
     * priority = not within range (0-3)
     * @param priority
     * @return
     */
    public static String priorityInvalidCause (Integer priority)
    {
        if (priority == null){
            return "Priority cant be null";
        }
        if (priority < 0 ){
            return "Priority cant be negative";
        }
        if (priority > 3){
            return "Priority cant be bigger than 3";
        }
        return null;
    }

    /**
     * Throws an error if:
     * priority = NULL
     * priority = not within range (0-3)
     * @param priority
     * @return
     */
    public static String priorityInvalidCause (String priority)
    {
        try{
            return priorityInvalidCause(Integer.parseInt(priority));
        }catch (NumberFormatException e){
            return "Must be a number!";
        }
    }
}
