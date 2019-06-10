package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EmployeeSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Employee;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

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

    private SearchContainer previousSearch;

    private Employee selectedEmployee;

    /**
     * This will bind the confirmation button to the subcontroller
     * If the neccesary fields are not filled out,
     * it will disable the button until they are
     */
    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(employeeSubController.isValid.not());
        employeeSubController.resetForm();
    }

    @Override
    public void initValues(SearchContainer searchContainer, Employee employee) {
        //Save search container for returning
        //propergate Consultation to setup form
        previousSearch = searchContainer;
        employeeSubController.initValues(employee);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){
            Parent root = cancelButton.getScene().getRoot();
            ((BorderPane) root).setCenter(ViewController.FIND_EMPLOYEE_TO_CHANGE.loadParent(previousSearch));
        } else {
            // goto main screen
            cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
        }
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
        Employee employee = employeeSubController.getEmployee();

        //Send confirmation
        if (employee.getEmployeeID() == null){
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Consultation was added to the Database Successfully!");
            info.showAndWait();
        }else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Consultation was updated in the Database Successfully!");
            info.showAndWait();
        }

        try {
            DbFacade.connect();
            DbFacade.insertEmployee(employee); //TODO check that the relationShip between employee and Consultation is actually done somwhere
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
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
