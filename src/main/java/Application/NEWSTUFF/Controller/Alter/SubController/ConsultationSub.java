package Application.NEWSTUFF.Controller.Alter.SubController;

import Application.NEWSTUFF.Controller.AbstractController;
import Domain.Employee;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    public SimpleBooleanProperty isEditiable; //Hook to make the form unchangeable
    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button

    public void initialize(){
        //setup listview
        //Setup isValid
        //setup bindings
    }

    @FXML
    public void handleConsultationnameInput(){

    }

    @FXML
    public void handleStartDateInput(){

    }

    @FXML
    public void handleEndDateInput(){

    }

    @FXML
    public void handleRemoveEmployee(){

    }

    @FXML
    public void handleAddEmployee(){

    }

    @FXML
    public void handleNewEmployee(){

    }

    private void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
    }

}
