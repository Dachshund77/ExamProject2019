package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EducationSub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlterEducation extends AbstractController {
    @FXML
    public Button confirmationButton;

    @FXML
    private EducationSub educationSub;

    public void handleCancel(ActionEvent event) {
    }

    public void handleConfirmation(ActionEvent event) {
    }

    public void handleReset(ActionEvent event) {
    }
}
