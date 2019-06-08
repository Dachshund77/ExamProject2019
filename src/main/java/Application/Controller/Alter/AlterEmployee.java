package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EmployeeSub;
import Application.SearchContainer;
import Domain.DomainObjects.Employee;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class AlterEmployee extends AbstractController {
    @FXML
    private EmployeeSub employeeSubController;

    @FXML
    private Button confirmationButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button changeAction;

    private SearchContainer searchContainer;

    private Employee selectedEmployee;

    /**
     * This will bind the confirmation button to the subcontroller
     * If the neccesary fields are not filled out,
     * it will disable the button until they are
     */
    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(employeeSubController.isValid.not());
    }

    @Override
    public void initValues(SearchContainer searchContainer, Employee employee) {
        //Save search container for returning
        //propergate Consultation to setup form
        employeeSubController.initValues(employee);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
    }

    /**
     * Updates the database with a new employee
     * @param event on user pressing "Confirm"
     *              Creates a new employee object,
     *              which pulls the data from the TextFields
     *              and then inserts them into database
     */
    @FXML
    private void handleConfirmation(ActionEvent event) {
        System.out.println(employeeSubController.isValid.get());

        Employee createNewEmployeeObj = new Employee(null,employeeSubController.employeeFirstNameTextField.getText(),
                employeeSubController.employeeLastNameTextField.getText(),employeeSubController.cprNrTextField.getText(),
                employeeSubController.emailTextField.getText(),employeeSubController.phoneNrTextField.getText(),null);

        try {
            DbFacade.connect();
            DbFacade.insertEmployee(createNewEmployeeObj);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * This will reset the TextFields when the button "Reset" or "Clear" is pressed
     * It will set all the TextFields to null, ready to type again
     * @param event
     */
    @FXML
    private void handleReset(ActionEvent event) {
        employeeSubController.resetForm();
    }
}
