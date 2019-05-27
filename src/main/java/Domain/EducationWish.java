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
}
