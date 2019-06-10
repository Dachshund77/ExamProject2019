package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EmployeeSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Employee;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class DeleteEmployee extends AbstractController {

    @FXML
    private EmployeeSub employeeSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button returnButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize() {
        employeeSubController.setDisabled(true);
    }

    /**
     * When the user has selected an employee
     * the TextField in the scene will be populated
     * with the consultation name
     *
     * @param searchContainer
     * @param employee
     */
    @Override
    public void initValues(SearchContainer searchContainer, Employee employee) {
        previousSearch = searchContainer;
        employeeSubController.initValues(employee);
    }

    /**
     * When the user clicks "Confirm"
     * a new employee object with all
     * the information is deleted from the database
     *
     * @param actionEvent
     */
    public void handleConfirmation(ActionEvent actionEvent) {

        try {
            DbFacade.connect();
            DbFacade.deleteEmployee(employeeSubController.selectedEmployee.getEmployeeID());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_EMPLOYEE_TO_DELETE.loadParent(previousSearch));
    }
}
