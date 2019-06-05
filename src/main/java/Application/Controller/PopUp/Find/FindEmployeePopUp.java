package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.EmployeeReturnableController;
import Application.Controller.ViewController;
import Domain.Company;
import Domain.Employee;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class FindEmployeePopUp extends EmployeeReturnableController {
    public Button confirmationButton;
    public Button cancelButton;

    @Override
    public Employee getReturn() {
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
        return ViewController.FIND_EMPLOYEE_POPUP.loadParent();
    }
}
