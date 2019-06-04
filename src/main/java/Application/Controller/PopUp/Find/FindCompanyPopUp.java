package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.CompanyReturnableController;
import Application.Controller.ViewController;
import Domain.Company;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class FindCompanyPopUp extends CompanyReturnableController {

    public Button confirmationButton;
    public Button cancelButton;

    @Override
    public Company getReturn() {
        return null;
    }

    public void handleConfirmation(ActionEvent actionEvent) {
        
    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    @Override
    public Parent getParent() {
        return ViewController.FIND_COMPANY_POPUP.loadParent();
    }
}
