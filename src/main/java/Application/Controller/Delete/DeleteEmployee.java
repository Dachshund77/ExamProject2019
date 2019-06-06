package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EmployeeSub;
import Application.SearchContainer;
import Domain.Consultation;
import Domain.Employee;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class DeleteEmployee extends AbstractController {
    @FXML
    private EmployeeSub employeeSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize() {

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
        Employee createToDeleteEmployeeObj = new Employee(employeeSubController.selectedEmployee.getEmployeeID(),
                employeeSubController.selectedEmployee.getEmployeeFirstName(), employeeSubController.selectedEmployee.getEmployeeLastName(),
                employeeSubController.selectedEmployee.getCprNr(), employeeSubController.selectedEmployee.getEmail(),
                employeeSubController.selectedEmployee.getPhoneNr(), employeeSubController.selectedEmployee.getInterviews());

        try {
            DbFacade.connect();
            DbFacade.deleteEmployee(createToDeleteEmployeeObj.getEmployeeID());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
