package Application.Controller.SubControllers.Domain;

import Domain.Interview;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class EmployeeSub {

    @FXML
    public Text employeeIDText;
    @FXML
    public TextField employeeFirstNameTextField;
    @FXML
    public Tooltip employeeFirstnameTooltup;
    @FXML
    public TextField employeeLastNameTextField;
    @FXML
    public Tooltip employeeLastNameTooltip;
    @FXML
    public TextField cprNrTextField;
    @FXML
    public Tooltip cprNrTooltip;
    @FXML
    public TextField phoneNrTextField;
    @FXML
    public Tooltip phoneNrTooltip;
    @FXML
    public TableView<Interview> interviewTableView;
    @FXML
    public TableColumn<Interview,Integer> interviewIDColumn;
    @FXML
    public TableColumn<Interview,String> interviewNameColumn;
    @FXML
    public Button removeInterviewButton;
    @FXML
    public Button newInterviewButton;
    @FXML
    public Button seeDetailedInterviewButton; //maybe

    private ArrayList<Interview> interviews;


    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button

    public void initialize(){
        //setup Tableview
        //setup is valid
        // setup bindings
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

    private void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
    }

    private void setEditable(boolean bool){

    }
}
