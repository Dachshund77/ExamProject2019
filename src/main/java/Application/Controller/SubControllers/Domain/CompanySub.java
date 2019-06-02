package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.Company;
import Domain.Consultation;
import Domain.Education;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompanySub extends AbstractController {

    public Label companyIDText;
    public TextField cvrNrTextField;
    public Tooltip cvrNrTooltip;
    public TextField companyNameTextField;
    public Tooltip companyNameTooltip;
    public TableView<Consultation> consultationTableView;
    public TableColumn<Consultation, String> consultationNameColumn;
    public TableColumn<Consultation, LocalDate> consultationStartDateColumn;
    public TableColumn<Consultation, LocalDate> consultationEndDateColumn;
    public TableView<Education> educationTableView;
    public TableColumn<Education, String> educationNameColumn;
    public Button addConsultationButton;
    public Button removeConsultationButton;
    public Button newConsultationButton;

    // FIXME: 29/05/2019 there should probably bee a add education stuff here, but there some object reference issues with that

    public ArrayList<Consultation> consultationArrayList;
    public ArrayList<Education> educationArrayList;

    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button
    public Company selectedCompany;

    public void initialize(){
        //setup listview
        //Setup isValid
        //setup bindings
    }

    @Override
    public void initValues(Company company) {
        //hook up Company
    }

    public void handleCvrNrInput(KeyEvent keyEvent){
        // whenever input in education textfield
        // should also update is valid
    }

    public void handleCompanyNameInput(KeyEvent keyEvent){

    }

    public void handleAddConsultation(ActionEvent event){

    }

    public void handleRemoveConsultation(ActionEvent event){

    }

    public void handleNewConsultation(ActionEvent event){

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
