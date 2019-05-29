package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EmployeeSub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlterEmployee extends AbstractController {
    @FXML
    public Button confirmationButton;

    @FXML
    private EmployeeSub employeeSub;

    public void handleCancel(ActionEvent event) {
    }

    public void handleConfirmation(ActionEvent event) {
    }

    public void handleReset(ActionEvent event) {
    }
}
