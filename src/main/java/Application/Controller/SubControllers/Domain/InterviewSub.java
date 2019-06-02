package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.EducationWish;
import Domain.FinishedEducation;
import Domain.Interview;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class InterviewSub extends AbstractController {

    public Text interviewID;
    public TextField interViewNameTextField;
    public Tooltip interViewNameTooltip;
    public MenuButton productUnderstandingMenuButton;
    public MenuButton problemUnderstandingMenuButton;
    public MenuButton qualityAwarnessMenuButton;
    public MenuButton cooperationMenuButton;
    public MenuButton flexiblityMenuButton;
    public TableView<FinishedEducation> finishedEducationTableView;
    public TableColumn<FinishedEducation, String> finishedEducationNameColumn;
    public TableColumn<FinishedEducation, LocalDate> finishedEducationDateColumn;
    public TableView<EducationWish> educationWishTableView;
    public TableColumn<EducationWish, String> educationWishNameColumn;
    public TableColumn<EducationWish, Integer> educationWishPriorityColumn;
    public Button addEducationWishbutton;
    public Button removeEducationWishButton;
    public Button pickEduForWishButton;
    public Button addFinishedEducationButton;
    public Button removeFinishedEducationButton;
    public Button pickEduForFinishedEducationButton;
    public DatePicker dateForFinishedEducation;

    public ArrayList<EducationWish> educationWishArrayList;
    public ArrayList<FinishedEducation> finishedEducationArrayList;

    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button
    public Interview selectedInterview;
    public void initialize(){
        //setup listview
        //Setup isValid
        //setup bindings
    }

    @Override
    public void initValues(Interview interview) {
        // hook up interview
    }

    public void handleinterviewNameInput(KeyEvent keyEvent){

    }

    public void handleAddEducationWish(ActionEvent event){

    }

    public void handleAddFinishedEducation(ActionEvent event){

    }

    public void handleRemoveEducationWish(ActionEvent event){

    }

    public void handleRemoveFinishedEducation(ActionEvent event){

    }

    public void handlePickFinishedEducation(ActionEvent actionEvent) {

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
