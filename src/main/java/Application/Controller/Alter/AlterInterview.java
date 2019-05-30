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
    private InterviewSub interviewSub;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private SearchContainer searchContainer;

    @FXML
    private void initialize(){
        // hook up the  button with subcontroller form correctness
    }

    @Override
    public void initValues(SearchContainer searchContainer, Interview interview) {
        //Save search container for returning
        //propergate Consultation to setup form
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Write to db
    }

    @FXML
    private void handleReset(ActionEvent event) {
        // call subcontroller reset
    }
}
