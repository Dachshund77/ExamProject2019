package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindEducationSub;
import Application.Controller.SubControllers.Find.FindEmployeeSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayEmployee;
import Domain.DomainObjects.Employee;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import javax.swing.text.View;
import java.sql.SQLException;

public class FindEmployeeToDelete extends AbstractController {


    @FXML
    private FindEmployeeSub findEmployeeSubController;

    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<DisplayEmployee> employeeTableView;

    /**
     * disables the confirm button
     * until the user has selected an employee
     */
    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        employeeTableView = findEmployeeSubController.getEmployeeTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(employeeTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * initializes the findEmployeeSubController
     * to have values from the searchContainer
     * @param searchContainer
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findEmployeeSubController.initValues(searchContainer);
    }

    /**
     * If the user clicks the "cancel" button
     * the user will return to the main scene
     * @param event
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    /**
     * when the user has selected a company
     * and pressed the "confirm" button
     * the user will be presented with a new stage
     * where it is possible to see the following
     * Employee ID
     * Employee name
     * Employee Phone No.
     * Employee E-Mail
     * @param event
     */
    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Init values
        Employee toBeDeletedEmployee = null;

        //Get selection
        DisplayEmployee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        int id = selectedEmployee.getEmployeeID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeDeletedEmployee = DbFacade.findEmployeeByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        SearchContainer currentSearch = findEmployeeSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.DELETE_EMPLOYEE.loadParent(currentSearch, toBeDeletedEmployee));
    }
}