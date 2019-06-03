package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.Employee;
import Domain.Interview;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class EmployeeSub extends AbstractController {

    public Label employeeIDText;
    public TextField employeeFirstNameTextField;
    public Tooltip employeeFirstnameTooltip;
    public TextField employeeLastNameTextField;
    public Tooltip employeeLastNameTooltip;
    public TextField cprNrTextField;
    public Tooltip cprNrTooltip;
    public TextField phoneNrTextField;
    public Tooltip phoneNrTooltip;
    public TableView<Interview> interviewTableView;
    public TableColumn<Interview,Integer> interviewIDColumn;
    public TableColumn<Interview,String> interviewNameColumn;
    public Button removeInterviewButton;
    public Button newInterviewButton;
    public Button seeDetailedInterviewButton; //maybe

    private ArrayList<Interview> interviews;


    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button
    public Employee selectedEmployee;


    public void initialize(){
        //setup Tableview
        //setup is valid
        // setup bindings
    }

    @Override
    public void initValues(Employee employee) {
        // hook up employee
    }

    public void handleFirstNameInput(KeyEvent keyevent){
        // whenever input in textfield is detected, react on it
        // should update its tooltip
        // should also update is valid
    }

    public void handleLastNameInput(KeyEvent keyEvent){

    }

    public void handleCprNrInput(KeyEvent keyEvent){

    }

    public void handlePhoneNrInput(KeyEvent keyEvent){

    }

    public void handleRemoveInterview(ActionEvent event){

    }

    public void handleAddInterview(ActionEvent event){
        //open popup and stuff
    }

    public void handleSeeInterview(ActionEvent event){

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
