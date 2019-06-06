package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EducationSub;
import Application.SearchContainer;
import Domain.Education;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteEducation extends AbstractController {
    @FXML
    private EducationSub educationSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){

    }

    @Override
    public void initValues(SearchContainer searchContainer, Education education){
        previousSearch = searchContainer;
        educationSubController.initValues(education);
    }


    public void handleConfirmation(ActionEvent actionEvent) {

    }
}
