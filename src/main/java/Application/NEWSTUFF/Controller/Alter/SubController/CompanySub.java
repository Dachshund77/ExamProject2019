package Application.NEWSTUFF.Controller.Alter.SubController;

import Application.NEWSTUFF.Controller.AbstractController;
import Domain.Consultation;
import Domain.Education;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompanySub extends AbstractController {

    @FXML
    public Text companyIDText;
    @FXML
    public TextField cvrNrTextField;
    @FXML
    public Tooltip cvrnrTooltip;
    @FXML
    public TextField companyNameTextField;
    @FXML
    public Tooltip companyNameTooltip;
    @FXML
    public TableView<Consultation> consultationTableView;
    @FXML
    public TableColumn<Consultation, String> consultationNameColumn;
    @FXML
    public TableColumn<Consultation, LocalDate> consultationStartdateColumn;
    @FXML
    public TableColumn<Consultation, LocalDate> consultationEndDateColumn;
    @FXML
    public TableView<Education> educationTableView;
    @FXML
    public TableColumn<Education, String> educationNameColumn;
    @FXML
    public Button addConsultationButton;
    @FXML
    public Button removeConsultationButton;
    @FXML
    public Button newConsultationButton;

    // FIXME: 29/05/2019 there should probably bee a add education stuff here, but there some object reference issues with that

    private ArrayList<Consultation> consultationArrayList;
    private ArrayList<Education> educationArrayList;

    public SimpleBooleanProperty isEditiable; //Hook to make the form unchangeable
    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button

    @FXML
    public void handleCvrNrInput(KeyEvent keyEvent){
        // whenever input in education textfield
        // should also update is valid
    }

    @FXML
    public void handleCompanyNameInput(){

    }

    @FXML
    public void handleAddConsultation(){

    }

    @FXML
    public void handleRemoveConsultation(){

    }

    @FXML
    public void handleNewConsultation(){

    }


    private void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
    }
}
