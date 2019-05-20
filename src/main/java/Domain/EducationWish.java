package Domain;

import javafx.beans.property.SimpleIntegerProperty;

public class EducationWish {
    private SimpleIntegerProperty educationWishID;
    private Education education;
    private SimpleIntegerProperty priority;

    public EducationWish(Integer educationWishID, Education education, Integer priority) {
        this.educationWishID = new SimpleIntegerProperty(educationWishID);
        this.education = education;
        this.priority = new SimpleIntegerProperty(priority);
    }
}
