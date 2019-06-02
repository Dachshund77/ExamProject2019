package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.Company;
import Domain.Consultation;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class FindSub extends AbstractController { // FIXME: 31/05/2019 at some point this class neec to be claned up, we realy dont validate input we do that at the SearchContainer

    //Company
    public TextField companyIDTextField;
    public TextField cvrNrTextField;
    public TextField companyNameTextField;
    //Consultation
    public DatePicker consultationMaxDatePicker;
    public DatePicker consultationMinDatePicker;
    public TextField consultationNameTextField;
    public TextField consultationIDTextField;
    //Employee
    public TextField employeeIDTextField;
    public TextField empFirstNameTextField;
    public TextField empLastNameTextField;
    public TextField cprNrTextField;
    public TextField eMailTextField;
    public TextField phoneNrTextField;
    //Interview
    public TextField interviewIDTextField;
    public TextField interviewsNameTextField;
    //Education
    public TextField amuNrTextField;
    public TextField educationNameTextField;
    public TextField educationNoOfDaysTextField;
    public DatePicker educationMinDatePicker;
    public DatePicker educationMaxDatePicker;
    //provider
    public TextField providerIDTextField;
    public TextField providerNameTextField;

    public Button searchButton;
    public Button resetButton;

    BooleanBinding allTrue;

    private ArrayList<Company> searchResult;
    private SearchContainer previousSearchContainer = null;

    private SimpleBooleanProperty isValid = new SimpleBooleanProperty();

    private SimpleBooleanProperty isValidCompanyID = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidConsultationID = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidEmpID = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidInterviewID = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidAmuNr = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidProviderID = new SimpleBooleanProperty(true);

    public void initialize() {

        companyIDTextField.textProperty().addListener((observable, oldValue, newValue) ->{
            //Validation method call.
            //React on outcome, set UI, tooltip

        });








    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        this.previousSearchContainer = searchContainer;
        resetToPreviousSearch();
    }

    public void handleSearch(ActionEvent event) { //TODO need to overload
        SearchContainer container = new SearchContainer();

        //We asume that the search button is not clicked before the integer is valid

        //container.setCompanyID(companyIDTextField.getText());
        container.setCvrNr(cvrNrTextField.getText());
        container.setCompanyName(companyNameTextField.getText());
        //Consultation
        container.setConsultationMaxDate(consultationMaxDatePicker.getValue());
        container.setConsultationMinDate(consultationMinDatePicker.getValue());
        container.setConsultationName(consultationNameTextField.getText());
        //container.setConsultationID(consultationIDTextField.getText());
        //Employee
        //container.setEmployeeID(employeeIDTextField.getText());
        container.setEmployeeFirstName(empFirstNameTextField.getText());
        container.setEmployeeLastName(empLastNameTextField.getText());
        container.setCprNr(cprNrTextField.getText());
        container.setEmail(eMailTextField.getText());
        container.setPhoneNr(phoneNrTextField.getText());
        //Interview
        //container.setInterviewID(interviewIDTextField.getText());
        //container.setInterviewName(interviewsNameTextField.getText());
        //Education
        //container.setAmuNr(amuNrTextField.getText());
        container.setEducationName(educationNameTextField.getText());
        //container.setEducationNoOfDays(educationNoOfDaysTextField.getText());
        container.setEducationMinDate(educationMinDatePicker.getValue());
        container.setEducationMaxDate(educationMaxDatePicker.getValue());
        //provider
        //container.setProviderID(providerIDTextField.getText());
        container.setProviderName(providerNameTextField.getText());
    }

    /**
     * Reset all field to be empty or if a previous SearchContainer was loaded set the field to the previous values.
     * @param event user clicked the reset button.
     */
    @SuppressWarnings("Duplicates")
    public void handleReset(ActionEvent event) {
        if (previousSearchContainer == null) {
            resetToEmpty();
        } else {
            resetToPreviousSearch();
        }
    }

    private void resetToEmpty(){
        companyIDTextField.setText("");
        cvrNrTextField.setText("");
        companyNameTextField.setText("");
        //Consultation
        consultationMaxDatePicker.getEditor().setText(null);
        consultationMinDatePicker.getEditor().setText(null);
        consultationNameTextField.setText("");
        consultationIDTextField.setText("");
        //Employee
        employeeIDTextField.setText("");
        empFirstNameTextField.setText("");
        empLastNameTextField.setText("");
        cprNrTextField.setText("");
        eMailTextField.setText("");
        phoneNrTextField.setText("");
        //Interview
        interviewIDTextField.setText("");
        interviewsNameTextField.setText("");
        //Education
        amuNrTextField.setText("");
        educationNameTextField.setText("");
        educationNoOfDaysTextField.setText("");
        educationMinDatePicker.getEditor().setText(null);
        educationMaxDatePicker.getEditor().setText(null);
        //provider
        providerIDTextField.setText("");
        providerNameTextField.setText("");
    }

    private void resetToPreviousSearch(){
        companyIDTextField.setText(previousSearchContainer.getCompanyID().toString());
        cvrNrTextField.setText(previousSearchContainer.getCompanyName());
        companyNameTextField.setText(previousSearchContainer.getCompanyName());
        //Consultation
        consultationMaxDatePicker.getEditor().setText(previousSearchContainer.getConsultationMaxDate().toString());
        consultationMinDatePicker.getEditor().setText(previousSearchContainer.getConsultationMinDate().toString());
        consultationNameTextField.setText(previousSearchContainer.getConsultationName());
        consultationIDTextField.setText(previousSearchContainer.getConsultationID().toString());
        //Employee
        employeeIDTextField.setText(previousSearchContainer.getEmployeeID().toString());
        empFirstNameTextField.setText(previousSearchContainer.getEmployeeFirstName());
        empLastNameTextField.setText(previousSearchContainer.getEmployeeLastName());
        cprNrTextField.setText(previousSearchContainer.getCprNr());
        eMailTextField.setText(previousSearchContainer.getEmail());
        phoneNrTextField.setText(previousSearchContainer.getPhoneNr());
        //Interview
        interviewIDTextField.setText(previousSearchContainer.getInterviewID().toString());
        interviewsNameTextField.setText(previousSearchContainer.getInterviewName());
        //Education
        amuNrTextField.setText(previousSearchContainer.getAmuNr().toString());
        educationNameTextField.setText(previousSearchContainer.getEducationName());
        educationNoOfDaysTextField.setText(previousSearchContainer.getEducationNoOfDays().toString());
        educationMinDatePicker.getEditor().setText(previousSearchContainer.getEducationMinDate().toString());
        educationMaxDatePicker.getEditor().setText(previousSearchContainer.getEducationMaxDate().toString());
        //provider
        providerIDTextField.setText(previousSearchContainer.getProviderID().toString());
        providerNameTextField.setText(previousSearchContainer.getProviderName());
    }
}
