package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.EducationReturnableController;
import Application.Controller.ViewController;
import Domain.Company;
import Domain.Education;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class FindEducationPopUp extends EducationReturnableController {
    public Button confirmationButton;
    public Button cancelButton;

    @Override
    public Education getReturn() {
        return null;
    }

    public void handleConfirmation(ActionEvent actionEvent) {

    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    /**
     * @return Loads the FindEducationPopUp Stage
     */
    @Override
    public Parent getParent() {
        return ViewController.FIND_EDUCATION_POPUP.loadParent();
    }
}
