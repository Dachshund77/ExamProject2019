package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.InterviewSub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlterInterview extends AbstractController {
    @FXML
    public Button confirmationButton;

    @FXML
    private InterviewSub interviewSub;

    public void handleCancel(ActionEvent event) {
    }

    public void handleConfirmation(ActionEvent event) {
    }

    public void handleReset(ActionEvent event) {
    }
}
