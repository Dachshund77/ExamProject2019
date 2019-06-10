package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.InterviewSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Interview;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class AlterInterview extends AbstractController {


    @FXML
    private InterviewSub interviewSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private SearchContainer previousSearch;

    @FXML
    private void initialize() {
        confirmationButton.disableProperty().bind(interviewSubController.isValid.not());
        interviewSubController.resetForm();
    }


    @Override
    public void initValues(SearchContainer searchContainer, Interview interview) {
        //Save search container for returning
        previousSearch = searchContainer;
        //propergate Consultation to setup form
        interviewSubController.initValues(interview);
    }

    @FXML
    private void handleCancel(ActionEvent event) { //TODO needs implementation
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null) {
            Parent root = cancelButton.getScene().getRoot();
            ((BorderPane) root).setCenter(ViewController.FIND_INTERVIEW_TO_CHANGE.loadParent(previousSearch));
        } else {
            cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
        }
    }

    /**
     * When the user clicks "confirm"
     * an interview object is created and send to the database
     *
     * @param event sends an interview object to the database
     */
    @FXML
    private void handleConfirmation(ActionEvent event) {
        Interview interview = interviewSubController.getInterview();
        int employeeId = interviewSubController.getEmployeeID();

        //Send confirmation
        if (interview.getInterviewID() == null){
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Interview was added to the Database Successfully!");
            info.showAndWait();
        }else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Interview was updated in the Database Successfully!");
            info.showAndWait();
        }

        try {
            DbFacade.connect();
            DbFacade.insertInterview(interview, employeeId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    /**
     * @param event Resets the user required fields
     */
    @FXML
    private void handleReset(ActionEvent event) {
        interviewSubController.resetForm();
    }
}
