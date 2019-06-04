package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.ProviderReturnableController;
import Application.Controller.ViewController;
import Domain.Company;
import Domain.Provider;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class FindProviderPopUp extends ProviderReturnableController {
    public Button confirmationButton;
    public Button cancelButton;

    @Override
    public Provider getReturn() {
        return null;
    }

    public void handleConfirmation(ActionEvent actionEvent) {

    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    @Override
    public Parent getParent() {
        return ViewController.FIND_PROVIDER_POPUP.loadParent();
    }
}
