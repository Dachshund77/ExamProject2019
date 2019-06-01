package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.Consultation;
import Domain.Employee;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ConsultationSub extends AbstractController {

    public Label consultationIDText;
    public TextField consultationNameTextField;
    public Tooltip consultationNameTooltip;
    public DatePicker startDate;
    public DatePicker endDate;
    public TableView<Employee> employeeTableView;
    public TableColumn<Employee, String> employeeFirstNameColumn;
    public TableColumn<Employee, String> employeeLastNameColumn;
    public Button removeEmployeeButton;
    public Button addEmployeeButton;
    public Button newEmployeeButton;

    private ArrayList<Employee> employeeArrayList;

    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button
    public Consultation selectedConsultation;

    public void initialize(){
        //setup listview
        //Setup isValid
        //setup bindings
    }

    @Override
    public void initValues(Consultation consultation) {
        //hook up consultation
    }

    public void handleConsultationNameInput(KeyEvent keyEvent){

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

    public void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
    }

    public void setEditable(boolean bool){

    }
    public void resetForm(){
        //Reset fields, set field if it has a selected Domain object
    }
}
