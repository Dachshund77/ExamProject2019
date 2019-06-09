package Application.Controller.Find.FindToChange;

import Application.Controller.AbstractController;
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

import java.sql.SQLException;

public class FindEmployeeToChange extends AbstractController {

    @FXML
    private FindEmployeeSub findEmployeeSubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private TableView<DisplayEmployee> employeeTableView;


    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        employeeTableView = findEmployeeSubController.getEmployeeTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(employeeTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * {@inheritDoc}
     * <br/><br/>
     * Calling this initValues will ultimately invoke {@link Application.Controller.SubControllers.Find.FindSub#initValues(SearchContainer) FindSub} reset.
     * This will set the search form to the values of the given SearchContainer.
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findEmployeeSubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen
        Parent root = confirmationButton.getScene().getRoot();
        root.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Init values
        Employee toBeChangedEmployee = null;

        //Get selection
        DisplayEmployee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        int id = selectedEmployee.getEmployeeID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeChangedEmployee = DbFacade.findEmployeeByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        //Get the search container
        SearchContainer currentSearch = findEmployeeSubController.getFindSubController().getCurrentSearchContainer();

        //Goto Change Company
        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.ALTER_EMPLOYEE.loadParent(currentSearch, toBeChangedEmployee));

    }
}
