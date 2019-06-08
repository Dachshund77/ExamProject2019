package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.InterviewReturnableController;
import Application.Controller.SubControllers.Find.FindInterviewSub;
import Application.Controller.ViewController;
import Domain.DomainObjects.Interview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class FindInterviewPopUp extends InterviewReturnableController {

    @FXML
    private FindInterviewSub findInterviewSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<Interview> interviewTableView;
    private Interview selectedInterview;


    public void initialize() {
        //Init fields
        selectedInterview = null;

        // Load the TableView reference from subController
        interviewTableView = findInterviewSubController.getInterviewTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(interviewTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    public void handleConfirmation(ActionEvent actionEvent) {
        Stage stage = (Stage) confirmationButton.getScene().getWindow();
        selectedInterview = interviewTableView.getSelectionModel().getSelectedItem(); //Confirmation can only be activated if something is selected
        stage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        selectedInterview = null;
        stage.close();
    }

    @Override
    public String getURL() {
        return ViewController.FIND_INTERVIEW_POPUP.getURL();
    }

    /**
     * @return Loads the FindCInterviewPopUp Stage
     */
    @Override
    public Interview getReturn() {
        return selectedInterview;
    }
}
