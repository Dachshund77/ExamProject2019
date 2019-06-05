package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.InterviewSub;
import Application.SearchContainer;
import Domain.Interview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlterInterview extends AbstractController {

    @FXML
    private InterviewSub interviewSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(interviewSubController.isValid.not());
    }

    @Override
    public void initValues(SearchContainer searchContainer, Interview interview) {
        //Save search container for returning
        previousSearch = searchContainer;
        //propergate Consultation to setup form
        interviewSubController.initValues(interview);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){

        } else {

        }
    }

    /**
     * When the user clicks "confirm"
     * an interview object is created and send to the database
     * @param event sends an interview object to the database
     */
    @FXML
    private void handleConfirmation(ActionEvent event) {
    }

    /**
     * @param event Resets the user required fields
     */
    @FXML
    private void handleReset(ActionEvent event) {
        interviewSubController.resetForm();
    }
}
