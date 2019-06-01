package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.Education;
import Domain.Provider;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.event.Event;
import java.time.LocalDate;
import java.util.ArrayList;


public class EducationSub extends AbstractController {

    public Label AmuNrText;
    public TextField educationNameTextField;
    public Tooltip educationNameTooltip;
    public TextArea descriptionTextArea;
    public Tooltip descriptionTooltip;
    public ChoiceBox noOfDaysChoiceBox; // may be converted to a drop down if you want
    public Tooltip noOfDaysTooltip;
    public ListView<LocalDate> dateListView;
    public DatePicker datePicker;
    public Button addDateButton;
    public Button removeDateButton;
    public Button pickProviderButton;

    public ArrayList<LocalDate> dates;
    public Provider selectedProvider;

    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button
    public Education selectedEducation;

    public void initialize(){
        //setup listview
        //Setup isValid
        //setup bindings
    }

    @Override
    public void initValues(Education education) {
        //jook up education
    }

    public void handleEducationNameInput(KeyEvent keyEvent){
        // whenever input in education textfield
        // should also update is valid
    }


    public void handleDescriptionInput(KeyEvent keyEvent){

    }

    public void handleNoOFDaysInput(KeyEvent keyEvent){

    }

    public void handleAddDate(ActionEvent event){

    }

    public void handleRemoveDate(ActionEvent event){

    }

    public void handlePickProvider(ActionEvent event){

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
