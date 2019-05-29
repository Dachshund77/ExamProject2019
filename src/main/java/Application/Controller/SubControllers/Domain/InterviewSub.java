package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.EducationWish;
import Domain.FinishedEducation;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class InterviewSub extends AbstractController {
    @FXML
    public Text interviewID;
    @FXML
    public TextField interViewNameTextField;
    @FXML
    public Tooltip interViewNameTooltip;
    @FXML
    public MenuButton productUnderstandingMenuButton;
    @FXML
    public MenuButton problemUnderstandingMenuButton;
    @FXML
    public MenuButton qualityAwarnessMenuButton;
    @FXML
    public MenuButton cooperationMenuButton;
    @FXML
    public MenuButton flexiblityMenuButton;
    @FXML
    public TableView<FinishedEducation> finishedEducationTableView;
    @FXML
    public TableColumn<FinishedEducation, String> finishedEducationNameColumn;
    @FXML
    public TableColumn<FinishedEducation, LocalDate> finishedEducationDateColumn;
    @FXML
    public TableView<EducationWish> educationWishTableView;
    @FXML
    public TableColumn<EducationWish, String> educationWishNameColumn;
    @FXML
    public TableColumn<EducationWish, Integer> educationWishPriorityColumn;
    @FXML
    public Button addEducationWishbutton;
    @FXML
    public Button removeEducationWishButton;
    @FXML
    public Button pickEduForWishButton;
    @FXML
    public Button addFinishedEducationButton;
    @FXML
    public Button removeFinishedEducationButton;
    @FXML
    public Button pickEduForFinishedEducationButton;
    @FXML
    public DatePicker dateForFinishedEducation;

    private ArrayList<EducationWish> educationWishArrayList;
    private ArrayList<FinishedEducation> finishedEducationArrayList;

    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button

    public void initialize(){
        //setup listview
        //Setup isValid
        //setup bindings
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

    private void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
    }

    private void setEditable(boolean bool){

    }
}
