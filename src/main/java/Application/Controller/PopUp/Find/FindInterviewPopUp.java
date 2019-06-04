package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.InterviewReturnableController;
import Application.Controller.ViewController;
import Domain.Company;
import Domain.Interview;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class FindInterviewPopUp extends InterviewReturnableController {

    public Button confirmationButton;
    public Button cancelButton;

    @Override
    public Interview getReturn() {
        return null;
    }

    public void handleConfirmation(ActionEvent actionEvent) {

    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    @Override
    public Parent getParent() {
        return ViewController.FIND_INTERVIEW_POPUP.loadParent();
    }
}
