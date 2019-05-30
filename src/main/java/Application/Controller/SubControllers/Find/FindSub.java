package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class FindSub extends AbstractController {
    public TitledPane companyPane;
    public TextField companyIDTextField;
    public Tooltip companyIDTooltip;
    public TextField cvrNrTextField;
    public Tooltip cvrNrTooltip;
    public TextField companyNameTextField;
    public Tooltip companyNameTooltip;
    public TitledPane consultationPane;
    public DatePicker consultationMaxDatePicker;
    public Tooltip consultationMaxDateTooltip;
    public DatePicker consultationMinDatePicker;
    public Tooltip consultationMinDateTooltip;
    public TextField consultationNameTextField;
    public Tooltip consultationNameTooltip;
    public TextField consultationIDTextField;
    public Tooltip consultationIDTooltip;
    public TitledPane employeePane;
    public TextField employeeIDTextField;
    public Tooltip employeeIDTooltip;
    public TextField empFirstNameTextField;
    public Tooltip empFirstNameTooltip;
    public TextField empLastNameTextField;
    public Tooltip empLastNameTooltip;
    public TextField cprNrTextField;
    public Tooltip cprNrTooltip;
    public TextField eMailTextField;
    public Tooltip eMailTooltip;
    public TextField phoneNrTextField;
    public Tooltip phoneNrTooltip;
    public TitledPane interviewPane;
    public TextField interviewIDTextField;
    public Tooltip interviewIDTooltip;
    public TextField interviewsNameTextField;
    public Tooltip interviewNameTooltip;
    public TitledPane educationPane;
    public TextField amuNrTextField;
    public Tooltip amuNrTooltip;
    public TextField educationNameTextField;
    public Tooltip educationNameTooltip;
    public TextField educationNoOfDaysTextField;
    public Tooltip educationNoOfDaysTooltip;
    public DatePicker educationMinDatePicker;
    public Tooltip educationMinDateTooltip;
    public DatePicker educationMaxDatePicker;
    public Tooltip educationMaxDateTooltip;
    public TitledPane providerPane;
    public TextField providerIDTextField;
    public Tooltip providerIDTooltip;
    public TextField providerNameTextField;
    public Tooltip providerNameTooltip;

    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button
    public Button searchButton;
    public Button resetButton;


    public void initialize(){
        //setup isValid
        //hookup bindings
    }

    public void handleCompanyIDInput(KeyEvent keyEvent) {
    }

    public void handleCvrNrInput(KeyEvent keyEvent) {
    }

    public void handleCompanyNameInput(KeyEvent keyEvent) {
    }

    public void handleConsulationDatePicked(ActionEvent event) {
    }

    public void handleConsultationNameInput(KeyEvent keyEvent) {
    }

    public void handleConsultationIDInput(KeyEvent keyEvent) {
    }

    public void handleEmployeeIDInput(KeyEvent keyEvent) {
    }

    public void handleEmpFirstNameInput(KeyEvent keyEvent) {
    }

    public void handleEmpLastNameInput(KeyEvent keyEvent) {
    }

    public void handleCprNrInput(KeyEvent keyEvent) {
    }

    public void handleEmailInput(KeyEvent keyEvent) {
    }

    public void handlePhoneNrInput(KeyEvent keyEvent) {
    }

    public void handleInterviewIDInput(KeyEvent keyEvent) {
    }

    public void handleInterviewNameInput(KeyEvent keyEvent) {
    }

    public void handleAmuNrInput(KeyEvent keyEvent) {
    }

    public void handleEducationNameInput(KeyEvent keyEvent) {
    }

    public void handleEducationNoOfDaysInput(KeyEvent keyEvent) {
    }

    public void handleEducationDatePicked(ActionEvent event) {
    }

    public void handleProviderIDInput(KeyEvent keyEvent) {
    }

    public void handleProviderNameInput(KeyEvent keyEvent) {
    }

    public void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
    }

    public void resetForm(){
        //Reset fields, set field if it has a selected Domain object
    }

    public void handleSearch(ActionEvent event) {
    }

    public void handleReset(ActionEvent event) {
    }
}
