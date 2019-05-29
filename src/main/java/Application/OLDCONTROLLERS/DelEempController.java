package Application.OLDCONTROLLERS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class DelEempController {

    @FXML
    TableView empTableView;
    @FXML
    ComboBox compDrop;
    @FXML
    Button confirmBtn;
    @FXML
    Button deleteBtn;

    /**
     * initializing the comboBox with all companies
     */
    public void initialize(){
    }

    /**
     *Create a tableView with cells that correspond with the database
     */

     /*
    TableColumn<Integer, Employee> employeeID = new TableColumn<>("ID");
    employeeID.setCellValueFactory(new propertyValueFactory<>("ID"));
    employeeID.getItems().add();

    TableColumn<String, Employee> employeeFirst = new TableColumn<>("fName");
    employeeFirst.setCellValueFactory(New propertyValueFactory<>("First name"));
    employeeFirst.getItems().add();

    TableColumn<String, Employee> employeeLast = new TableColumn<>("lastName");
    employeeLast.setCellValueFactory(New propertyValueFactory<>("Last Name"));
    employeeLast.getItems().add();

    TableColumn<String, Employee> employeeCPR = new TableColumn<>("CPR");
    EmployeeCPR.setCellValueFactory(New propertyValueFactory<>("CPR no"));
    employeeCPR.getItems().add();

    TableColumn<String, Employee> employeeEmail = new TableColumn<>("Email");
    employeeEmail.setCellValueFactory(New propertyValueFactory<>("Email"));
    employeeEmail.getItems().add();

    TableColumn<String, Employee> employeePhone = new TableColumn<>("Phone no.");
    employeePhone.setCellValueFactory(New propertyValueFactory<>("Phone no."));
    employeePhone.getItems().add();

    */


    /**
     * When a company has been selected from the comboBox
     * All the employees should be populated in the TableView
     * @param actionEvent
     */
    public void handleSelected(ActionEvent actionEvent) {
    }

    /**
     * When an employee has been selected from the TableView
     * Then that employee will be deleted when the
     * "Delete" button has been clicked
     * @param actionEvent
     */
    public void handleDeleteEmp(ActionEvent actionEvent) {
    }

    /**
     * Confirm the actions made
     * @param actionEvent
     */
    public void handleConfirm(ActionEvent actionEvent) {
    }


}
