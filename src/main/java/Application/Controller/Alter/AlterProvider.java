package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ProviderSub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlterProvider extends AbstractController {
    @FXML
    public Button confirmationButton;

    @FXML
    private ProviderSub providerSub;

    public void handleCancel(ActionEvent event) {
    }

    public void handleConfirmation(ActionEvent event) {
    }

    public void handleReset(ActionEvent event) {
    }
}
