package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindEducationSub;
import Application.Controller.SubControllers.Find.FindEmployeeSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Education;
import Domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import javax.swing.text.View;

public class FindEmployeeToDelete extends AbstractController {


    @FXML
    private FindEmployeeSub findEmployeeSubController;

    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    /**
     * disables the confirm button
     * until the user has selected an employee
     */
    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(findEmployeeSubController.getEmployeeTableView().getSelectionModel().selectedItemProperty().isNull());
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
    @FXML
    private void handleConfirmation(ActionEvent event) {
        Employee toBeDeletedEmployee = findEmployeeSubController.getEmployeeTableView().getSelectionModel().getSelectedItem();
        SearchContainer currentSearch = findEmployeeSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.DELETE_EMPLOYEE.loadParent(currentSearch, toBeDeletedEmployee));
    }
}