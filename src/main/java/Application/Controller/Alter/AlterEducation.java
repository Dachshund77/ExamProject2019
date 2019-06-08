package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EducationSub;
import Application.SearchContainer;
import Domain.DomainObjects.Education;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlterEducation extends AbstractController {
    @FXML
    private EducationSub educationSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;


    /**
     * initializes the confirmationbutton
     * it is disabled until the required
     * Textfields have valid content
     */
    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(educationSubController.isValid.not());
    }

    @Override
    public void initValues(SearchContainer searchContainer, Education education) {
        previousSearch = searchContainer;
        educationSubController.initValues(education); // Be careful that you implement the correct init values, else you will get unsupported Exception

    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){
            // go to findEducationTo change with initValue call
        } else {
            // goto main screen
        }
    }

    /**
     * When the user clicks "confirm"
     * an education object is created and send to the database
     * @param event sends an education object to the database
     */
    @FXML
    private void handleConfirmation(ActionEvent event) {
    }

    /**
     * @param event Resets the user required fields
     */
    @FXML
    private void handleReset(ActionEvent event) {
        educationSubController.resetForm();
    }
}
