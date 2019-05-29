package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlterCompany extends AbstractController {

    @FXML
    public Button confirmationButton;

    @FXML
    private CompanySub companySub;

    public void handleCancel(ActionEvent event) {
    }

    public void handleConfirmation(ActionEvent event) {
    }

    public void handleReset(ActionEvent event) {
    }
}
