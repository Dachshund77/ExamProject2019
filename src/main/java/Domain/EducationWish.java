package Domain;

import javafx.beans.property.SimpleIntegerProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationWish {
    private SimpleIntegerProperty educationWishID;
    private Education education;
    private SimpleIntegerProperty priority;

    public EducationWish(Integer educationWishID, Education education, Integer priority) {
        if (educationWishID != null) {
            this.educationWishID = new SimpleIntegerProperty(educationWishID);
        } else {
            this.educationWishID = null;
        }
        this.education = education;

        if (priority != null) {
            this.priority = new SimpleIntegerProperty(priority);
        } else {
            this.priority = new SimpleIntegerProperty(-1);
        }
    }

    public Integer getEducationWishID() {
        if (educationWishID == null){
            return null;
        }
        return educationWishID.get();
    }

    public Education getEducation() {
        return education;
    }

    public Integer getPriority() {
        if (priority == null) {
            return null;
        }
        return priority.get();
    }

    public SimpleIntegerProperty priorityProperty() {
        return priority;
    }

    /**
     * checks if educationID isnt NULL and negative
     * @param educationWishID
     * @return
     */
    public static boolean isValidEducationWishID (Integer educationWishID)
    {
       return educationWishID != null && educationWishID > 0;
    }

    /**
     * Throws an error if:
     * educationWishID = NULL
     * educationWishID = Negative
     * @param educationWishID
     * @return
     */
    public static String educationWishIDInvalidCause (Integer educationWishID)
    {
        if (educationWishID == null){
            return "EducationWishID cant be null";
        }
        if (educationWishID < 0){
            return "EducationWishID cant be negative";
        }
        return null;
    }

    /**
     * checks if the AMU No. is valid
     * @param education
     * @return
     */

    public static boolean isValidEducation (Education education) //TODO guessing its AMU No. else fix
    {
        return education.getAmuNr() != null && education.getAmuNr() > 0;
    }

    /**
     * Throws an error if:
     * AMU No. = NULL
     * AMU No. = negative
     * @param education
     * @return
     */
    public static String invalidEducationCause (Education education)
    {
        if (education.getAmuNr() == null){
            return "AMU No. cant be null";
        }
        if (education.getAmuNr() < 0){
            return "AMU No. cant be negative";
        }
        return null;
    }


    /**
     * checks if priority is within range and not null
     * @param priority
     * @return
     */
    public static boolean isValidPriority(Integer priority)
    {
       return priority != null && priority > 0 && priority < 3;
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
}
