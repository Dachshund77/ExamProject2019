package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.EmployeeReturnableController;
import Application.Controller.SubControllers.Find.FindEmployeeSub;
import Application.Controller.ViewController;
import Domain.DisplayObjects.DisplayEmployee;
import Domain.DomainObjects.Employee;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FindEmployeePopUp extends EmployeeReturnableController {
    @FXML
    private FindEmployeeSub findEmployeeSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<DisplayEmployee> employeeTableView;
    private Employee selectedEmployee;


    public void initialize() {
        //Init fields
        selectedEmployee = null;

        // Load the TableView reference from subController
        employeeTableView = findEmployeeSubController.getEmployeeTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(employeeTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void handleConfirmation(ActionEvent actionEvent) {
        //Get selection
        DisplayEmployee selectedItem = employeeTableView.getSelectionModel().getSelectedItem();
        int id = selectedItem.getEmployeeID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            selectedEmployee = DbFacade.findEmployeeByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) confirmationButton.getScene().getWindow();
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

    /**
     * @return Loads the FindEducationPopUp Stage
     */
    @Override
    public Employee getReturn() {
        return selectedEmployee;
    }
}
