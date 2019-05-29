package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.Employee;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ConsultationSub extends AbstractController {

    @FXML
    public Text consultationIDText;
    @FXML
    public TextField consultationNameTextField;
    @FXML
    public Tooltip consultationNameTooltip;
    @FXML
    public DatePicker startDate;
    @FXML
    public DatePicker endDate;
    @FXML
    public TableView<Employee> employeeTableView;
    @FXML
    public TableColumn<Employee, String> employeeFirstNameColumn;
    @FXML
    public TableColumn<Employee, String> employeeLastnameColumn;
    @FXML
    public Button removeEmployeeButton;
    @FXML
    public Button addEmployeeButton;
    @FXML
    public Button newEmployeeButtonl;

    private ArrayList<Employee> employeeArrayList;

    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button

    public void initialize(){
        //setup listview
        //Setup isValid
        //setup bindings
    }

    public void handleConsultationnameInput(KeyEvent keyEvent){

    }

    public void handleStartDateInput(KeyEvent keyEvent){

    }

    public void handleEndDateInput(ActionEvent event){

    }

    public void handleRemoveEmployee(ActionEvent event){

    }

    public void handleAddEmployee(ActionEvent event){

    }

    public void handleNewEmployee(ActionEvent event){

    }

    private void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
    }

    private void setEditable(boolean bool){

    }

}
