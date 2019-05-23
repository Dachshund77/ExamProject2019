package Domain;

import javafx.beans.property.SimpleIntegerProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationWish {
    private SimpleIntegerProperty educationWishID;
    private Education education;
    private SimpleIntegerProperty priority;

    public EducationWish(Integer educationWishID, Education education, Integer priority) {
        this.educationWishID = new SimpleIntegerProperty(educationWishID);
        this.education = education;
        this.priority = new SimpleIntegerProperty(priority);
    }

    /**
     * Constructor that builds an object from ResultSet.
     * Note that no relation or Arrays for this object will created, this will be handled by {@link Persistance.DbFacade}.
     * @param rs ResultSet that will be used to build the object.
     * @throws SQLException Thrown when encoutered a fatal error.
     */
    public EducationWish(ResultSet rs) throws SQLException {
        this.educationWishID = new SimpleIntegerProperty(rs.getInt("fld_EducationWishID"));
        this.priority = new SimpleIntegerProperty(rs.getInt("fld_WishPriority"));
    }

    public int getEducationWishID() {
        return educationWishID.get();
    }

    public SimpleIntegerProperty educationWishIDProperty() {
        return educationWishID;
    }

    public Education getEducation() {
        return education;
    }

    public int getPriority() {
        return priority.get();
    }

    public SimpleIntegerProperty priorityProperty() {
        return priority;
    }
}
