package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.Provider;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;


public class EducationSub extends AbstractController {

    @FXML
    public Text AmuNrText;
    @FXML
    public TextField educationNameTextField;
    @FXML
    public Tooltip educationNameTooltip;
    @FXML
    public TextArea descriptionTextArea;
    @FXML
    public Tooltip descriptionTooltip;
    @FXML
    public TextField noOfDaysTextField; // may be converted to a drop down if you want
    @FXML
    public Tooltip noOfDaysTooltip;
    @FXML
    public ListView<LocalDate> dateListView;
    @FXML
    public DatePicker datePicker;
    @FXML
    public Button addDateButton;
    @FXML
    public Button removeDateButton;
    @FXML
    public Button pickProviderButton;

    private ArrayList<LocalDate> dates;
    private Provider selectedProvider;

    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button

    public void initialize(){
        //setup listview
        //Setup isValid
        //setup bindings
    }

    @FXML
    public void handleEducationNameInput(KeyEvent keyEvent){
        // whenever input in education textfield
        // should also update is valid
    }

    @FXML
    public void handleDescriptionInput(KeyEvent keyEvent){

    }

    @FXML
    public void handleNoOFDaysInput(KeyEvent keyEvent){

    }

    @FXML
    public void handleAddDate(ActionEvent event){

    }

    @FXML
    public void handleRemoveDate(ActionEvent event){

    }

    @FXML
    public void handlePickProvider(ActionEvent event){

    }

    private void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
    }

    private void setEditable(boolean bool){

    }

}
