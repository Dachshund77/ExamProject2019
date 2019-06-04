package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.ConsultationReturnableController;
import Application.Controller.ViewController;
import Domain.Company;
import Domain.Consultation;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FindConsultationPopUp extends ConsultationReturnableController {

    public Button confirmationButton;
    public Button cancelButton;

    @Override
    public Consultation getReturn() {
        return null;
    }

    public void handleConfirmation(ActionEvent actionEvent) {

    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    @Override
    public Parent getParent() {
        return ViewController.FIND_CONSULTATION_POPUP.loadParent();
    }
}
