package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.DomainObjects.Company;
import Foundation.DbFacade;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;


/**
 * SubController for the search UI. This subController represents the search values the user can fil out.
 * It is also this Class that connects to the Database and fetches the result. It is up to the parent
 * controllers to format that result into an TableView.
 * @see DbFacade
 */
@SuppressWarnings("Duplicates")
public class FindSub extends AbstractController {

    //Company
    @FXML
    private TitledPane companyPane;
    @FXML
    private TextField companyIDTextField;
    @FXML
    private TextField cvrNrTextField;
    @FXML
    private TextField companyNameTextField;
    //Consultation
    @FXML
    private TitledPane consultationPane;
    @FXML
    private DatePicker consultationMaxDatePicker;
    @FXML
    private DatePicker consultationMinDatePicker;
    @FXML
    private TextField consultationNameTextField;
    @FXML
    private TextField consultationIDTextField;
    //Employee
    @FXML
    private TitledPane employeePane;
    @FXML
    private TextField employeeIDTextField;
    @FXML
    private TextField empFirstNameTextField;
    @FXML
    private TextField empLastNameTextField;
    @FXML
    private TextField cprNrTextField;
    @FXML
    private TextField eMailTextField;
    @FXML
    private TextField phoneNrTextField;
    //Interview
    @FXML
    private TitledPane interviewPane;
    @FXML
    private TextField interviewIDTextField;
    @FXML
    private TextField interviewsNameTextField;
    //Education
    @FXML
    private TitledPane educationPane;
    @FXML
    private TextField amuNrTextField;
    @FXML
    private TextField educationNameTextField;
    @FXML
    private TextField educationNoOfDaysTextField;
    @FXML
    private DatePicker educationMinDatePicker;
    @FXML
    private DatePicker educationMaxDatePicker;
    //provider
    @FXML
    private TitledPane providerPane;
    @FXML
    private TextField providerIDTextField;
    @FXML
    private TextField providerNameTextField;

    @FXML
    private Button searchButton; //TODO do i need this?
    @FXML
    private Button resetButton; //TODO do i need this evt clean up

    private ObservableList<Company> searchResultList = FXCollections.observableArrayList();
    private SearchContainer previousSearchContainer = null;
    private SearchContainer currentSearchContainer = null;

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

    /**
     * Set ups the binding and validation for the Controller.
     * Most notably disables the Search button if any validation criteria is not met.
     * Validation is done with static validators in {@link SearchContainer}.
     */
    public void initialize() {
        //Company section
        companyIDTextField.textProperty().addListener(observable -> handleCompanyIdInput());
        cvrNrTextField.textProperty().addListener(observable -> handleCvrNrInput());
        companyNameTextField.textProperty().addListener(observable -> handleCompanyNameInput());

        companyValid = new BooleanBinding() {
            {
                super.bind(isValidCompanyID);
                super.bind(isValidCvrNr);
                super.bind(isValidCompanyName);
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
                super.bind(isValidConsultationID);
                super.bind(isValidConsultationName);
                super.bind(isValidConsultationDates);
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
        cprNrTextField.textProperty().addListener(observable -> handleCprNrInput());
        eMailTextField.textProperty().addListener(observable -> handleEmailInput());
        phoneNrTextField.textProperty().addListener(observable -> handlePhoneNrInput());

        employeeValid = new BooleanBinding() {
            {
                super.bind(isValidEmpID);
                super.bind(isValidEmpFirstName);
                super.bind(isValidEmpLastName);
                super.bind(isValidCprNr);
                super.bind(isValidEmail);
                super.bind(isValidPhoneNr);
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
                super.bind(isValidInterviewID);
                super.bind(isValidInterviewName);
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
                super.bind(isValidAmuNr);
                super.bind(isValidEducationName);
                super.bind(isValidNoOfEducationDays);
                super.bind(isValidEducationDates);
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
                super.bind(isValidProviderID);
                super.bind(isValidProviderName);
            }

            @Override
            protected boolean computeValue() {
                return handleProviderSectionInput();
            }
        };

        //Master binding(search button)
        allValid = new BooleanBinding() {
            {
                super.bind(companyValid);
                super.bind(consultationValid);
                super.bind(employeeValid);
                super.bind(interviewValid);
                super.bind(educationValid);
                super.bind(providerValid);
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

    /**
     * {@inheritDoc}
     * <br/><br/>
     * This implementation is used to reset the textFields to the previous search query and make
     * a search.
     * {@link #handleReset(ActionEvent)} will also reset the fields to this searchContainers values, when loaded.
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        this.previousSearchContainer = searchContainer;
        resetToPreviousSearch();
        handleSearch(new ActionEvent()); //TODO i have no idea if this works - Sven
    }

    private void handleCompanyIdInput() {
        if (SearchContainer.isValidCompanyID(companyIDTextField.getText())) {
            companyIDTextField.setTooltip(null);
            isValidCompanyID.set(true);
            companyIDTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.companyIDInvalidCause(companyIDTextField.getText());
            companyIDTextField.setTooltip(new Tooltip(invalidCause));
            isValidCompanyID.set(false);
            if (!companyIDTextField.getStyleClass().contains("TextField-Error")) {
                companyIDTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleCvrNrInput() {
        if (SearchContainer.isValidCvrNr(cvrNrTextField.getText())) {
            cvrNrTextField.setTooltip(null);
            isValidCvrNr.set(true);
            cvrNrTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.cvrNrInvalidCause(cvrNrTextField.getText());
            cvrNrTextField.setTooltip(new Tooltip(invalidCause));
            isValidCvrNr.set(false);
            if (!cvrNrTextField.getStyleClass().contains("TextField-Error")) {
                cvrNrTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleCompanyNameInput() {
        if (SearchContainer.isValidCompanyName(companyNameTextField.getText())) {
            companyNameTextField.setTooltip(null);
            isValidCompanyName.set(true);
            companyNameTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.companyNameInvalidClause(companyNameTextField.getText());
            companyNameTextField.setTooltip(new Tooltip(invalidCause));
            isValidCompanyName.set(false);
            if (!companyNameTextField.getStyleClass().contains("TextField-Error")) {
                companyNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleConsultationIdInput() {
        if (SearchContainer.isValidConsultationID(consultationIDTextField.getText())) {
            consultationIDTextField.setTooltip(null);
            isValidConsultationID.set(true);
            consultationIDTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.consultationIDInvalidCause(consultationIDTextField.getText());
            consultationIDTextField.setTooltip(new Tooltip(invalidCause));
            isValidConsultationID.set(false);
            if (!consultationIDTextField.getStyleClass().contains("TextField-Error")) {
                consultationIDTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleConsultationNameInput() {
        if (SearchContainer.isValidCompanyName(consultationNameTextField.getText())) {
            consultationNameTextField.setTooltip(null);
            isValidConsultationName.set(true);
            consultationNameTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.consultationNameInvalidCause(consultationNameTextField.getText());
            consultationNameTextField.setTooltip(new Tooltip(invalidCause));
            isValidConsultationName.set(false);
            if (!consultationNameTextField.getStyleClass().contains("TextField-Error")) {
                consultationNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private void handleConsultationDateInput() {
        if (SearchContainer.isValidDate(consultationMinDatePicker.getValue(), consultationMaxDatePicker.getValue())) {
            consultationMinDatePicker.setTooltip(null);
            consultationMaxDatePicker.setTooltip(null);
            isValidConsultationDates.set(true);
            consultationMinDatePicker.getStyleClass().removeAll("DatePicker-Error");
            consultationMaxDatePicker.getStyleClass().removeAll("DatePicker-Error");
        } else {
            String invalidCause = SearchContainer.dateInvalidCause(consultationMinDatePicker.getValue(), consultationMaxDatePicker.getValue());
            consultationMinDatePicker.setTooltip(new Tooltip(invalidCause));
            consultationMaxDatePicker.setTooltip(new Tooltip(invalidCause));
            isValidConsultationName.set(false);
            if (!consultationMinDatePicker.getStyleClass().contains("DatePicker-Error")) {
                consultationMinDatePicker.getStyleClass().add("DatePicker-Error");
            }
            if (!consultationMaxDatePicker.getStyleClass().contains("DatePicker-Error")) {
                consultationMaxDatePicker.getStyleClass().add("DatePicker-Error");
            }
        }
    }

    private void handleEmpIdInput() {
        if (SearchContainer.isValidEmployeeID(employeeIDTextField.getText())) {
            employeeIDTextField.setTooltip(null);
            isValidEmpID.set(true);
            employeeIDTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.employeeIDInvalidCause(employeeIDTextField.getText());
            employeeIDTextField.setTooltip(new Tooltip(invalidCause));
            isValidEmpID.set(false);
            if (!employeeIDTextField.getStyleClass().contains("TextField-Error")) {
                employeeIDTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleEmpFirstNameInput() {
        if (SearchContainer.isValidEmployeeFirstName(empFirstNameTextField.getText())) {
            empFirstNameTextField.setTooltip(null);
            isValidEmpFirstName.set(true);
            empFirstNameTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.employeeFirstNameInvalidCause(empFirstNameTextField.getText());
            empFirstNameTextField.setTooltip(new Tooltip(invalidCause));
            isValidEmpFirstName.set(false);
            if (!empFirstNameTextField.getStyleClass().contains("TextField-Error")) {
                empFirstNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleEmpLastNameInput() {
        if (SearchContainer.isValidEmployeeLastName(empLastNameTextField.getText())) {
            empLastNameTextField.setTooltip(null);
            isValidEmpLastName.set(true);
            empLastNameTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.employeeLastNameInvalidCause(empLastNameTextField.getText());
            empLastNameTextField.setTooltip(new Tooltip(invalidCause));
            isValidEmpLastName.set(false);
            if (!empLastNameTextField.getStyleClass().contains("TextField-Error")) {
                empLastNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleCprNrInput() {
        if (SearchContainer.isValidCprNr(cprNrTextField.getText())) {
            cprNrTextField.setTooltip(null);
            isValidCprNr.set(true);
            cprNrTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.cprNrInvalidCause(cprNrTextField.getText());
            cprNrTextField.setTooltip(new Tooltip(invalidCause));
            isValidCprNr.set(false);
            if (!cprNrTextField.getStyleClass().contains("TextField-Error")) {
                cprNrTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleEmailInput() {
        if (SearchContainer.isValidEmail(eMailTextField.getText())) {
            eMailTextField.setTooltip(null);
            isValidEmail.set(true);
            eMailTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.eMailInvalidCause(eMailTextField.getText());
            eMailTextField.setTooltip(new Tooltip(invalidCause));
            isValidEmail.set(false);
            if (!eMailTextField.getStyleClass().contains("TextField-Error")) {
                eMailTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handlePhoneNrInput() {
        if (SearchContainer.isValidPhoneNr(phoneNrTextField.getText())) {
            phoneNrTextField.setTooltip(null);
            isValidPhoneNr.set(true);
            phoneNrTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.phoneNumberInvalidCause(phoneNrTextField.getText());
            phoneNrTextField.setTooltip(new Tooltip(invalidCause));
            isValidPhoneNr.set(false);
            if (!phoneNrTextField.getStyleClass().contains("TextField-Error")) {
                phoneNrTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleInterviewIdInput() {
        if (SearchContainer.isValidInterviewID(interviewIDTextField.getText())) {
            interviewIDTextField.setTooltip(null);
            isValidInterviewID.set(true);
            interviewIDTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.interviewIDInvalidCause(interviewIDTextField.getText());
            interviewIDTextField.setTooltip(new Tooltip(invalidCause));
            isValidInterviewID.set(false);
            if (!interviewIDTextField.getStyleClass().contains("TextField-Error")) {
                interviewIDTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleInterviewNameInput() {
        if (SearchContainer.isValidInterviewName(interviewsNameTextField.getText())) {
            interviewsNameTextField.setTooltip(null);
            isValidInterviewName.set(true);
            interviewsNameTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.interviewNameInvalidCause(interviewsNameTextField.getText());
            interviewsNameTextField.setTooltip(new Tooltip(invalidCause));
            isValidInterviewName.set(false);
            if (!interviewsNameTextField.getStyleClass().contains("TextField-Error")) {
                interviewsNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleAmuNrInput() {
        if (SearchContainer.isValidAmuNr(amuNrTextField.getText())) {
            amuNrTextField.setTooltip(null);
            isValidAmuNr.set(true);
            amuNrTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.amuNrInvalidCause(amuNrTextField.getText());
            amuNrTextField.setTooltip(new Tooltip(invalidCause));
            isValidAmuNr.set(false);
            if (!amuNrTextField.getStyleClass().contains("TextField-Error")) {
                amuNrTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleEducationNameInput() {
        if (SearchContainer.isValidEducationName(educationNameTextField.getText())) {
            educationNameTextField.setTooltip(null);
            isValidEducationName.set(true);
            educationNameTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.educationNameInvalidCause(educationNameTextField.getText());
            educationNameTextField.setTooltip(new Tooltip(invalidCause));
            isValidEducationName.set(false);
            if (!educationNameTextField.getStyleClass().contains("TextField-Error")) {
                educationNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleNoOfEducationDaysInput() {
        if (SearchContainer.isValidNoOfDays(educationNoOfDaysTextField.getText())) {
            educationNoOfDaysTextField.setTooltip(null);
            isValidNoOfEducationDays.set(true);
            educationNoOfDaysTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.noOfDaysInvalidCause(educationNoOfDaysTextField.getText());
            educationNoOfDaysTextField.setTooltip(new Tooltip(invalidCause));
            isValidNoOfEducationDays.set(false);
            if (!educationNoOfDaysTextField.getStyleClass().contains("TextField-Error")) {
                educationNoOfDaysTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private void handleEducationDateInput() {
        if (SearchContainer.isValidDate(educationMinDatePicker.getValue(), educationMaxDatePicker.getValue())) {
            educationMinDatePicker.setTooltip(null);
            educationMaxDatePicker.setTooltip(null);
            isValidEducationDates.set(true);
            educationMinDatePicker.getStyleClass().removeAll("DatePicker-Error");
            educationMaxDatePicker.getStyleClass().removeAll("DatePicker-Error");
        } else {
            String invalidCause = SearchContainer.dateInvalidCause(educationMinDatePicker.getValue(), educationMaxDatePicker.getValue());
            educationMinDatePicker.setTooltip(new Tooltip(invalidCause));
            educationMaxDatePicker.setTooltip(new Tooltip(invalidCause));
            isValidEducationDates.set(false);
            if (!educationMinDatePicker.getStyleClass().contains("DatePicker-Error")) {
                educationMinDatePicker.getStyleClass().add("DatePicker-Error");
            }
            if (!educationMaxDatePicker.getStyleClass().contains("DatePicker-Error")) {
                educationMaxDatePicker.getStyleClass().add("DatePicker-Error");
            }
        }
    }

    private void handleProviderIdInput() {
        if (SearchContainer.isValidProviderID(providerIDTextField.getText())) {
            providerIDTextField.setTooltip(null);
            isValidProviderID.set(true);
            providerIDTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.invalidProviderIDCause(providerIDTextField.getText());
            providerIDTextField.setTooltip(new Tooltip(invalidCause));
            isValidProviderID.set(false);
            if (!providerIDTextField.getStyleClass().contains("TextField-Error")) {
                providerIDTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleProviderNameInput() {
        if (SearchContainer.isValidProviderName(providerNameTextField.getText())) {
            providerNameTextField.setTooltip(null);
            isValidProviderName.set(true);
            providerNameTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = SearchContainer.providerNameInvalidCause(providerNameTextField.getText());
            providerNameTextField.setTooltip(new Tooltip(invalidCause));
            isValidProviderName.set(false);
            if (!providerNameTextField.getStyleClass().contains("TextField-Error")) {
                providerNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private boolean handleCompanySectionInput() {
        if (isValidCompanyID.get() && isValidCompanyName.get() && isValidCvrNr.get()) {
            companyPane.getStyleClass().removeAll("TitledPane-Error");
            return true;
        } else {
            if (!companyPane.getStyleClass().contains("TitledPane-Error")) {
                companyPane.getStyleClass().add("TitledPane-Error");
            }
            return false;
        }
    }

    @SuppressWarnings("Duplicates")
    private boolean handleConsultationSectionInput() {
        if (isValidConsultationID.get() && isValidConsultationName.get() && isValidConsultationDates.get()) {
            consultationPane.getStyleClass().removeAll("TitledPane-Error");
            return true;
        } else {
            if (!consultationPane.getStyleClass().contains("TitledPane-Error")) {
                consultationPane.getStyleClass().add("TitledPane-Error");
            }
            return false;
        }
    }

    @SuppressWarnings("Duplicates")
    private boolean handleEmployeeSectionInput() {
        if (isValidEmpID.get() && isValidEmpFirstName.get() && isValidEmpLastName.get() && isValidEmail.get() && isValidPhoneNr.get() && isValidCprNr.get()) {
            employeePane.getStyleClass().removeAll("TitledPane-Error");
            return true;
        } else {
            if (!employeePane.getStyleClass().contains("TitledPane-Error")) {
                employeePane.getStyleClass().add("TitledPane-Error");
            }
            return false;
        }
    }

    private boolean handleInterviewSectionInput() {
        if (isValidInterviewID.get() && isValidInterviewName.get()) {
            interviewPane.getStyleClass().removeAll("TitledPane-Error");
            return true;
        } else {
            if (!interviewPane.getStyleClass().contains("TitledPane-Error")) {
                interviewPane.getStyleClass().add("TitledPane-Error");
            }
            return false;
        }
    }

    @SuppressWarnings("Duplicates")
    private boolean handleEducationSectionInput() {
        if (isValidAmuNr.get() && isValidEducationName.get() && isValidNoOfEducationDays.get() && isValidEducationDates.get()) {
            educationPane.getStyleClass().removeAll("TitledPane-Error");
            return true;
        } else {
            if (!educationPane.getStyleClass().contains("TitledPane-Error")) {
                educationPane.getStyleClass().add("TitledPane-Error");
            }
            return false;
        }
    }

    private boolean handleProviderSectionInput() {
        if (isValidProviderID.get() && isValidProviderName.get()) {
            providerPane.getStyleClass().removeAll("TitledPane-Error");
            return true;
        } else {
            if (!providerPane.getStyleClass().contains("TitledPane-Error")) {
                providerPane.getStyleClass().add("TitledPane-Error");
            }
            return false;
        }
    }

    private boolean isAllValid() {
        boolean companyBool = companyValid.get();
        boolean consultationBool = consultationValid.get();
        boolean employeeBool = employeeValid.get();
        boolean interviewBool = interviewValid.get();
        boolean educationBool = educationValid.get();
        boolean providerBool = providerValid.get();
        return companyBool && consultationBool && employeeBool && interviewBool && educationBool && providerBool;
    }

    @FXML
    private void handleSearch(ActionEvent event) {
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

        currentSearchContainer = container;
        //Connect to db
        try {
            DbFacade.connect();
            searchResultList.clear();
            searchResultList.addAll(DbFacade.findCompanies(container));

            DbFacade.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reset all field to be empty or if a previous SearchContainer was loaded set the field to the previous values.
     *
     * @param event user clicked the reset button.
     */
    @SuppressWarnings("Duplicates")
    @FXML
    private void handleReset(ActionEvent event) {
        if (previousSearchContainer == null) {
            currentSearchContainer = null;
            resetToEmpty();
        } else {
            currentSearchContainer = previousSearchContainer;
            resetToPreviousSearch();
        }
        //Clear searchResultList
        searchResultList.clear();
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
        companyIDTextField.setText(previousSearchContainer.getCompanyIDasString());
        cvrNrTextField.setText(previousSearchContainer.getCompanyName());
        companyNameTextField.setText(previousSearchContainer.getCompanyName());
        //Consultation
        consultationMaxDatePicker.setValue(previousSearchContainer.getConsultationMaxDate());
        consultationMinDatePicker.setValue(previousSearchContainer.getConsultationMinDate());
        consultationNameTextField.setText(previousSearchContainer.getConsultationName());
        consultationIDTextField.setText(previousSearchContainer.getConsultationIDasString());
        //Employee
        employeeIDTextField.setText(previousSearchContainer.getEmployeeIDasString());
        empFirstNameTextField.setText(previousSearchContainer.getEmployeeFirstName());
        empLastNameTextField.setText(previousSearchContainer.getEmployeeLastName());
        cprNrTextField.setText(previousSearchContainer.getCprNr());
        eMailTextField.setText(previousSearchContainer.getEmail());
        phoneNrTextField.setText(previousSearchContainer.getPhoneNr());
        //Interview
        interviewIDTextField.setText(previousSearchContainer.getInterviewIDasString());
        interviewsNameTextField.setText(previousSearchContainer.getInterviewName());
        //Education
        amuNrTextField.setText(previousSearchContainer.getAmuNrAsString());
        educationNameTextField.setText(previousSearchContainer.getEducationName());
        educationNoOfDaysTextField.setText(previousSearchContainer.getEducationNoOfDaysAsString());
        educationMinDatePicker.setValue(previousSearchContainer.getEducationMinDate());
        educationMaxDatePicker.setValue(previousSearchContainer.getEducationMaxDate());
        //provider
        providerIDTextField.setText(previousSearchContainer.getProviderIDasString());
        providerNameTextField.setText(previousSearchContainer.getProviderName());
    }

    @FXML
    private void resetConsultationMinDate(ActionEvent event) {
        consultationMinDatePicker.setValue(null);
    }

    @FXML
    private void resetConsultationMaxDate(ActionEvent event) {
        consultationMaxDatePicker.setValue(null);
    }

    @FXML
    private void resetEducationMinDate(ActionEvent event) {
        educationMinDatePicker.setValue(null);
    }

    @FXML
    private void resetEducationMaxDate(ActionEvent event) {
        educationMaxDatePicker.setValue(null);
    }

    public ObservableList<Company> getSearchResultList() {
        return searchResultList;
    }

    public SearchContainer getCurrentSearchContainer() {
        return currentSearchContainer;
    }
}
