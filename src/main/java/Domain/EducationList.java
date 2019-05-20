package Domain;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class EducationList {
    private SimpleStringProperty educationListName;
    private ArrayList<Education> education;

    public EducationList(SimpleStringProperty educationListName, ArrayList<Education> education) {
        this.educationListName = educationListName;
        this.education = education;
    }
}
