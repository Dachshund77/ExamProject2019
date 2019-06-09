package Application.Controller.Record;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EducationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Education;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordEducation extends AbstractController {
    @FXML
    private EducationSub educationSubController;
    @FXML
    private Button returnButton;

    private SearchContainer previousSearchContainer = null;

    public void initialize(){
        educationSubController.setDisabled(true);
    }

    @Override
    public void initValues(SearchContainer searchContainer, Education education) {
        previousSearchContainer = searchContainer;
        educationSubController.initValues(education);
    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_EDUCATION_TO_RECORD.loadParent(previousSearchContainer));
    }
}
