package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.EmployeeReturnableController;
import Application.Controller.SubControllers.Find.FindEmployeeSub;
import Application.Controller.ViewController;
import Domain.Company;
import Domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class FindEmployeePopUp extends EmployeeReturnableController {
    @FXML
    private FindEmployeeSub findEmployeeSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<Employee> employeeTableView;
    private Employee selectedEmployee;


    public void initialize() {
        //Init fields
        selectedEmployee = null;

        // Load the TableView reference from subController
        employeeTableView = findEmployeeSubController.getEmployeeTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(employeeTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    public void handleConfirmation(ActionEvent actionEvent) {
        Stage stage = (Stage) confirmationButton.getScene().getWindow();
        selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem(); //Confirmation can only be activated if something is selected
        stage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        selectedEmployee = null;
        stage.close();
    }

    @Override
    public String getURL() {
        return ViewController.FIND_EMPLOYEE_POPUP.getURL();
    }

    @Override
    public Employee getReturn() {
        return selectedEmployee;
    }
}
