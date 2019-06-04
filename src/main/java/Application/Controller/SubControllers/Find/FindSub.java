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
    public TitledPane companyPane;
    public TextField companyIDTextField;
    public TextField cvrNrTextField;
    public TextField companyNameTextField;
    //Consultation
    public TitledPane consultationPane;
    public DatePicker consultationMaxDatePicker;
    public DatePicker consultationMinDatePicker;
    public TextField consultationNameTextField;
    public TextField consultationIDTextField;
    //Employee
    public TitledPane employeePane;
    public TextField employeeIDTextField;
    public TextField empFirstNameTextField;
    public TextField empLastNameTextField;
    public TextField cprNrTextField;
    public TextField eMailTextField;
    public TextField phoneNrTextField;
    //Interview
    public TitledPane interviewPane;
    public TextField interviewIDTextField;
    public TextField interviewsNameTextField;
    //Education
    public TitledPane educationPane;
    public TextField amuNrTextField;
    public TextField educationNameTextField;
    public TextField educationNoOfDaysTextField;
    public DatePicker educationMinDatePicker;
    public DatePicker educationMaxDatePicker;
    //provider
    public TitledPane providerPane;
    public TextField providerIDTextField;
    public TextField providerNameTextField;

    public Button searchButton; //TODO do i need this?
    public Button resetButton; //TODO do i need this evt clean up

    private ArrayList<Company> searchResult;
    private SearchContainer previousSearchContainer = null;

    //BooleanBindings
    private BooleanBinding allValid;
    private BooleanBinding companyValid;
    private BooleanBinding consultationValid;
    private BooleanBinding employeeValid;
    private BooleanBinding interviewValid;
    private BooleanBinding educationValid;
    private BooleanBinding providerValid;

    //Company flags
    private SimpleBooleanProperty isValidCompanyID = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidCvrNr = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidCompanyName = new SimpleBooleanProperty(true);

    //Consultation flags
    private SimpleBooleanProperty isValidConsultationID = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidConsultationName = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidConsultationDates = new SimpleBooleanProperty(true);

    //Employee flags
    private SimpleBooleanProperty isValidEmpID = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidEmpFirstName = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidEmpLastName = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidCprNr = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidEmail = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidPhoneNr = new SimpleBooleanProperty(true);

    //Interview flags
    private SimpleBooleanProperty isValidInterviewID = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidInterviewName = new SimpleBooleanProperty(true);

    //Education flags
    private SimpleBooleanProperty isValidAmuNr = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidEducationName = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidNoOfEducationDays = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidEducationDates = new SimpleBooleanProperty(true);

    //Provider flags
    private SimpleBooleanProperty isValidProviderID = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty isValidProviderName = new SimpleBooleanProperty(true);

    public void initialize() {
        //Company section
        companyIDTextField.textProperty().addListener(observable -> handleCompanyIdInput());
        cvrNrTextField.textProperty().addListener(observable -> handleCvrNrInput());
        companyNameTextField.textProperty().addListener(observable -> handleCompanyNameInput());

        companyValid = new BooleanBinding() {
            {
                bind(isValidCompanyID);
                bind(isValidCvrNr);
                bind(isValidCompanyName);
            }
            @Override
            protected boolean computeValue() {
                return handleCompanySectionInput();
            }
        };

        //Consultation section
        consultationIDTextField.textProperty().addListener(observable -> handleConsultationIdInput());
        consultationNameTextField.textProperty().addListener(observable -> handleConsultationNameInput());
        consultationMinDatePicker.valueProperty().addListener(observable -> handleConsultationDateInput());
        consultationMaxDatePicker.valueProperty().addListener(observable -> handleConsultationDateInput());

        consultationValid = new BooleanBinding() {
            {
                bind(isValidConsultationID);
                bind(isValidConsultationName);
                bind(isValidConsultationDates);
            }
            @Override
            protected boolean computeValue() {
                return handleConsultationSectionInput();
            }
        };


        //Employee section
        employeeIDTextField.textProperty().addListener(observable -> handleEmpIdInput());
        empFirstNameTextField.textProperty().addListener(observable -> handleEmpFirstNameInput());
        empLastNameTextField.textProperty().addListener(observable -> handleEmpLastNameInput());
        cprNrTextField.textProperty().addListener(observable ->  handleCprNrInput());
        eMailTextField.textProperty().addListener(observable -> handleEmailInput());
        phoneNrTextField.textProperty().addListener(observable -> handlePhoneNrInput());

        employeeValid = new BooleanBinding() {
            {
                bind(isValidEmpID);
                bind(isValidEmpFirstName);
                bind(isValidEmpLastName);
                bind(isValidCprNr);
                bind(isValidEmail);
                bind(isValidPhoneNr);
            }
            @Override
            protected boolean computeValue() {
                return handleEmployeeSectionInput();
            }
        };

        //Interview section
        interviewIDTextField.textProperty().addListener(observable -> handleInterviewIdInput());
        interviewsNameTextField.textProperty().addListener(observable -> handleInterviewNameInput());

        interviewValid = new BooleanBinding() {
            {
                bind(isValidInterviewID);
                bind(isValidInterviewName);
            }
            @Override
            protected boolean computeValue() {
                return handleInterviewSectionInput();
            }
        };

        //Education section
        amuNrTextField.textProperty().addListener(observable -> handleAmuNrInput());
        educationNameTextField.textProperty().addListener(observable -> handleEducationNameInput());
        educationNoOfDaysTextField.textProperty().addListener(observable -> handleNoOfEducationDaysInput());
        educationMinDatePicker.valueProperty().addListener(observable -> handleEducationDateInput());
        educationMaxDatePicker.valueProperty().addListener(observable -> handleEducationDateInput());

        educationValid = new BooleanBinding() {
            {
                bind(isValidAmuNr);
                bind(isValidEducationName);
                bind(isValidNoOfEducationDays);
                bind(isValidEducationDates);
            }
            @Override
            protected boolean computeValue() {
                return handleEducationSectionInput();
            }
        };

        //Provider section
        providerIDTextField.textProperty().addListener(observable -> handleProviderIdInput());
        providerNameTextField.textProperty().addListener(observable -> handleProviderNameInput());

        providerValid = new BooleanBinding() {
            {
                bind(isValidProviderID);
                bind(isValidProviderName);
            }
            @Override
            protected boolean computeValue() {
                return handleProviderSectionInput();
            }
        };

        //Master binding(search button)
        allValid = new BooleanBinding() {
            {
                bind(companyValid);
                bind(consultationValid);
                bind(employeeValid);
                bind(interviewValid);
                bind(educationValid);
                bind(providerValid);
            }
            @Override
            protected boolean computeValue() {
                return isAllValid();
            }
        };

        //Set search button disable property
        searchButton.disableProperty().bind(allValid.not());

        //Reset form
        resetToEmpty();
    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        this.previousSearchContainer = searchContainer;
        resetToPreviousSearch();
    }

    private void handleCompanyIdInput() {
        if (SearchContainer.isValidCompanyID(companyIDTextField.getText())){
            companyIDTextField.setTooltip(null);
            isValidCompanyID.set(true);
            companyIDTextField.getStyleClass().removeAll("TextField-Error");
        } else{
            String invalidCause = SearchContainer.companyIDInvalidCause(companyIDTextField.getText());
            companyIDTextField.setTooltip(new Tooltip(invalidCause));
            isValidCompanyID.set(false);
            if (!companyIDTextField.getStyleClass().contains("TextField-Error")){
                companyIDTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleCvrNrInput() {
        if (SearchContainer.isValidCvrNr(cvrNrTextField.getText())){
            cvrNrTextField.setTooltip(null);
            isValidCvrNr.set(true);
            cvrNrTextField.getStyleClass().removeAll("TextField-Error");
        } else{
            String invalidCause = SearchContainer.cvrNrInvalidCause(cvrNrTextField.getText());
            cvrNrTextField.setTooltip(new Tooltip(invalidCause));
            isValidCvrNr.set(false);
            if (!cvrNrTextField.getStyleClass().contains("TextField-Error")){
                cvrNrTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleCompanyNameInput() {
        if (SearchContainer.isValidCompanyName(companyNameTextField.getText())){
            companyNameTextField.setTooltip(null);
            isValidCompanyName.set(true);
            companyNameTextField.getStyleClass().removeAll("TextField-Error");
        } else{
            String invalidCause = SearchContainer.companyNameInvalidClause(companyNameTextField.getText());
            companyNameTextField.setTooltip(new Tooltip(invalidCause));
            isValidCompanyName.set(false);
            if (!companyNameTextField.getStyleClass().contains("TextField-Error")){
                companyNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleConsultationIdInput() {
        if (SearchContainer.isValidConsultationID(consultationIDTextField.getText())){
            consultationIDTextField.setTooltip(null);
            isValidConsultationID.set(true);
            consultationIDTextField.getStyleClass().removeAll("TextField-Error");
        } else{
            String invalidCause = SearchContainer.consultationIDInvalidCause(consultationIDTextField.getText());
            consultationIDTextField.setTooltip(new Tooltip(invalidCause));
            isValidConsultationID.set(false);
            if (!consultationIDTextField.getStyleClass().contains("TextField-Error")){
                consultationIDTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleConsultationNameInput() {
        if (SearchContainer.isValidCompanyName(consultationNameTextField.getText())){
            consultationNameTextField.setTooltip(null);
            isValidConsultationName.set(true);
            consultationNameTextField.getStyleClass().removeAll("TextField-Error");
        } else{
            String invalidCause = SearchContainer.consultationNameInvalidCause(consultationNameTextField.getText());
            consultationNameTextField.setTooltip(new Tooltip(invalidCause));
            isValidConsultationName.set(false);
            if (!consultationNameTextField.getStyleClass().contains("TextField-Error")){
                consultationNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleConsultationDateInput() {
        if (SearchContainer.isValidDate(consultationMinDatePicker.getValue(), consultationMaxDatePicker.getValue())){
            consultationMinDatePicker.setTooltip(null);
            consultationMaxDatePicker.setTooltip(null);
            isValidConsultationDates.set(true);
            consultationMinDatePicker.getStyleClass().removeAll("DatePicker-Error");
            consultationMaxDatePicker.getStyleClass().removeAll("DatePicker-Error");
        } else{
            String invalidCause = SearchContainer.dateInvalidCause(consultationMinDatePicker.getValue(), consultationMaxDatePicker.getValue());
            consultationMinDatePicker.setTooltip(new Tooltip(invalidCause));
            consultationMaxDatePicker.setTooltip(new Tooltip(invalidCause));
            isValidConsultationName.set(false);
            if (!consultationMinDatePicker.getStyleClass().contains("DatePicker-Error")){
                consultationMinDatePicker.getStyleClass().add("DatePicker-Error");
            }
            if (!consultationMaxDatePicker.getStyleClass().contains("DatePicker-Error")){
                consultationMaxDatePicker.getStyleClass().add("DatePicker-Error");
            }
        }
    }

    private void handleEmpIdInput() {
        if (SearchContainer.isValidEmployeeID(employeeIDTextField.getText())){
            employeeIDTextField.setTooltip(null);
            isValidEmpID.set(true);
            employeeIDTextField.getStyleClass().removeAll("TextField-Error");
        } else{
            String invalidCause = SearchContainer.employeeIDInvalidCause(employeeIDTextField.getText());
            employeeIDTextField.setTooltip(new Tooltip(invalidCause));
            isValidEmpID.set(false);
            if (!employeeIDTextField.getStyleClass().contains("TextField-Error")){
                employeeIDTextField.getStyleClass().add("TextField-Error");
            }
        }

    }

    private void handleEmpFirstNameInput() {

    }

    private void handleEmpLastNameInput() {

    }

    private void handleCprNrInput() {

    }

    private void handleEmailInput() {

    }

    private void handlePhoneNrInput() {

    }

    private void handleInterviewIdInput() {

    }

    private void handleInterviewNameInput() {

    }

    private void handleAmuNrInput() {

    }

    private void handleEducationNameInput() {

    }

    private void handleNoOfEducationDaysInput() {

    }

    private void handleEducationDateInput() {

    }

    private void handleProviderIdInput() {

    }

    private void handleProviderNameInput() {

    }

    private boolean handleCompanySectionInput(){
        if (isValidCompanyID.get() && isValidCompanyName.get() && isValidCvrNr.get()){
            companyPane.getStyleClass().removeAll("TitledPane-Error");
            return true;
        }else {
            if (!companyPane.getStyleClass().contains("TitledPane-Error")){
                companyPane.getStyleClass().add("TitledPane-Error");
            }
            return false;
        }
    }

    private boolean handleConsultationSectionInput(){
        return true;
    }

    private boolean handleEmployeeSectionInput(){
        return true;
    }

    private boolean handleInterviewSectionInput(){
        return true;
    }

    private boolean handleEducationSectionInput(){
        return true;
    }

    private boolean handleProviderSectionInput(){
        return true;
    }

    private boolean isAllValid(){
        return companyValid.get() && consultationValid.get() && employeeValid.get() && interviewValid.get() && educationValid.get() && providerValid.get();
    }

    public void handleSearch(ActionEvent event) {
        SearchContainer container = new SearchContainer();

        //We operate under the assumption that the search button is not clicked before the integer is valid
        container.setCompanyID(companyIDTextField.getText());
        container.setCvrNr(cvrNrTextField.getText());
        container.setCompanyName(companyNameTextField.getText());
        //Consultation
        container.setConsultationMaxDate(consultationMaxDatePicker.getValue());
        container.setConsultationMinDate(consultationMinDatePicker.getValue());
        container.setConsultationName(consultationNameTextField.getText());
        container.setConsultationID(consultationIDTextField.getText());
        //Employee
        container.setEmployeeID(employeeIDTextField.getText());
        container.setEmployeeFirstName(empFirstNameTextField.getText());
        container.setEmployeeLastName(empLastNameTextField.getText());
        container.setCprNr(cprNrTextField.getText());
        container.setEmail(eMailTextField.getText());
        container.setPhoneNr(phoneNrTextField.getText());
        //Interview
        container.setInterviewID(interviewIDTextField.getText());
        container.setInterviewName(interviewsNameTextField.getText());
        //Education
        container.setAmuNr(amuNrTextField.getText());
        container.setEducationName(educationNameTextField.getText());
        container.setEducationNoOfDays(educationNoOfDaysTextField.getText());
        container.setEducationMinDate(educationMinDatePicker.getValue());
        container.setEducationMaxDate(educationMaxDatePicker.getValue());
        //provider
        container.setProviderID(providerIDTextField.getText());
        container.setProviderName(providerNameTextField.getText());

        //TODO actual search to facade
    }

    /**
     * Reset all field to be empty or if a previous SearchContainer was loaded set the field to the previous values.
     *
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

    private void resetToEmpty() {
        companyIDTextField.setText(null);
        cvrNrTextField.setText(null);
        companyNameTextField.setText(null);
        //Consultation
        consultationMaxDatePicker.setValue(null);
        consultationMinDatePicker.setValue(null);
        consultationNameTextField.setText(null);
        consultationIDTextField.setText(null);
        //Employee
        employeeIDTextField.setText(null);
        empFirstNameTextField.setText(null);
        empLastNameTextField.setText(null);
        cprNrTextField.setText(null);
        eMailTextField.setText(null);
        phoneNrTextField.setText(null);
        //Interview
        interviewIDTextField.setText(null);
        interviewsNameTextField.setText(null);
        //Education
        amuNrTextField.setText(null);
        educationNameTextField.setText(null);
        educationNoOfDaysTextField.setText(null);
        educationMinDatePicker.setValue(null);
        educationMaxDatePicker.setValue(null);
        //provider
        providerIDTextField.setText(null);
        providerNameTextField.setText(null);
    }

    private void resetToPreviousSearch() {
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

    public void resetConsultationMinDate(ActionEvent event) {
        consultationMinDatePicker.setValue(null);
    }

    public void resetConsultationMaxDate(ActionEvent event) {
        consultationMaxDatePicker.setValue(null);
    }

    public void resetEducationMinDate(ActionEvent event) {
        educationMinDatePicker.setValue(null);
    }

    public void resetEducationMaxDate(ActionEvent event) {
        educationMaxDatePicker.setValue(null);
    }
}
