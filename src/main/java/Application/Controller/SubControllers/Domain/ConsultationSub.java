package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Application.Controller.PopUp.Find.FindCompanyPopUp;
import Domain.DomainObjects.Company;
import Domain.DomainObjects.Consultation;
import Domain.DomainObjects.Employee;
import UI.CompanyChoice;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConsultationSub extends AbstractController {

    public Text consultationIDText;
    public TextField consultationNameTextField;
    public Tooltip consultationNameTooltip;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public TableView<Employee> employeeTableView;
    public TableColumn<Employee, String> employeeFirstNameColumn;
    public TableColumn<Employee, String> employeeLastNameColumn;
    public Button selectCompanyButton;
    public Label startDateLabel;
    public Label endDateLabel;

    public  ArrayList<Employee> employeeArrayList = new ArrayList<>();

    public BooleanBinding isValid;
    private SimpleBooleanProperty consultationNameIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty startDateIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty endDateIsValid = new SimpleBooleanProperty(true);

    public Consultation selectedConsultation = null;
    private Company orginalParentCompany = null;
    private SimpleObjectProperty<Company> selectedParentCompany = new SimpleObjectProperty<>();


    public void initialize() {

        /*
        Setting up the employee tableview
         */
        employeeFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeFirstName"));
        employeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
        employeeTableView.getColumns().setAll(employeeFirstNameColumn, employeeLastNameColumn);
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        employeeTableView.setItems(employeeObservableList);


        /*
        Hides the tableview when the user selects "New Consultation"
         */
        if (selectedConsultation == null) {
            employeeTableView.setVisible(false);
        }

        consultationNameTextField.textProperty().addListener(((observable) -> handleConsultationNameInput()));
        startDatePicker.valueProperty().addListener((observable -> handleDatePickerInput()));
        endDatePicker.valueProperty().addListener((observable -> handleDatePickerInput()));

        /*
        binds the .isValid() to the controls
        and returns true or false depending
        if the requirements are met
         */
        isValid = new BooleanBinding() {
            {
            bind(consultationNameIsValid);
            bind(startDateIsValid);
            bind(endDateIsValid);
            bind(selectedParentCompany);
            }
            @Override
            protected boolean computeValue() {
                return consultationNameIsValid.get() && startDateIsValid.get() && endDateIsValid.get() && selectedParentCompany.isNotNull().get();
            }
        };

        //visual for company button text
        StringBinding companyButtonVisual = new StringBinding() {
            {
                bind(selectedParentCompany);
            }
            @Override
            protected String computeValue() {
                if (selectedParentCompany.isNull().get()){
                    if (!selectCompanyButton.getStyleClass().contains("Button-Error")){
                        selectCompanyButton.getStyleClass().add("Button-Error");
                    }
                    return "Pick Company";
                } else {
                    selectCompanyButton.getStyleClass().removeAll("Button-Error");
                    return "ID "+selectedParentCompany.get().getCompanyID();
                }
            }
        };
        selectCompanyButton.textProperty().bind(companyButtonVisual);

        resetForm();
    }

    /**
     * initalizes the values from the selected consultation
     * it then sets the controls with the information about the consultations
     */
    @Override
    public void initValues(Consultation consultation, Company company) {
        selectedConsultation = consultation;
        orginalParentCompany = company;
        selectedParentCompany.set(company);
        resetForm();
        employeeArrayList.addAll(selectedConsultation.getEmployees());
    }

    /**
     * When the user inputs information into the textfield
     * it will update for each keypress
     * if the information in the textfield isn't valid
     * then the textfield will continue to be red
     * and a tooltip will show
     *
     * if the requirements are met, it will turn to normal color
     * and the user can proceed
     *
     */
    public void handleConsultationNameInput() {
        if (Consultation.isValidConsultationName(consultationNameTextField.getText())) {
            consultationNameTextField.setTooltip(null);
            consultationNameIsValid.set(true);
            consultationNameTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = Consultation.consultationIDInvalidCause(consultationNameTextField.getText());
            consultationNameTextField.setTooltip(new Tooltip(invalidCause));
            consultationNameIsValid.set(false);
            if (!consultationNameTextField.getStyleClass().contains("TextField-Error")) {
                consultationNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    /**
     * Here the user selects a startdate and enddate for a consultation period
     * if the end date is before the start date both DatePickers will become red
     * and a tooltip will become present if the user hovers a cursor over the
     * individual datePickers
     */
    public void handleDatePickerInput() {
        LocalDate localDateStartDate = startDatePicker.getValue();
        LocalDate localDateEndDate = endDatePicker.getValue();

        if (localDateStartDate.isBefore(localDateEndDate)) {
            startDatePicker.setTooltip(null);
            startDateIsValid.set(true);
            startDatePicker.getStyleClass().removeAll("DatePicker-Error");

            endDatePicker.setTooltip(null);
            endDateIsValid.set(true);
            endDatePicker.getStyleClass().removeAll("DatePicker-Error");
        } else {
            String invalidCause = Consultation.dateInvalidCause(localDateStartDate, localDateEndDate);
            startDatePicker.setTooltip(new Tooltip(invalidCause));
            startDateIsValid.set(false);
            endDatePicker.setTooltip(new Tooltip(invalidCause));
            endDateIsValid.set(false);
            if (!endDatePicker.getStyleClass().contains("DatePicker-Error") && !startDatePicker.getStyleClass().contains("DatePicker-Error")){
                startDatePicker.getStyleClass().add("DatePicker-Error");
                endDatePicker.getStyleClass().add("DatePicker-Error");
            }
        }
    }

    
    public void setDisabled(boolean bool) {
        consultationNameTextField.setDisable(bool);
        startDatePicker.setDisable(bool);
        startDatePicker.setVisible(false);
        startDateLabel.setVisible(false);
        endDatePicker.setDisable(bool);
        endDatePicker.setVisible(false);
        endDateLabel.setVisible(false);
        selectCompanyButton.setDisable(bool);
        selectCompanyButton.setVisible(false);
        consultationIDText.setVisible(false);

    }

    /**
     * When the user clicks on the "reset" button
     * all the controls will be cleared
     * if the user has arrived to this scene
     * from "Change Consultation" it will reset its values
     * back to the information from that search
     */
    public void resetForm() { //TODO need fix
        if (selectedConsultation != null) {
            consultationNameTextField.setText(selectedConsultation.getConsultationName());
        } else {
            consultationNameTextField.setText("");
        }
    }

    public void handleSelectCompany(ActionEvent actionEvent) {
        CompanyChoice newSelectCompany = new CompanyChoice();
        Company foundCompany = newSelectCompany.showAndReturn(new FindCompanyPopUp());
        selectedParentCompany.set(foundCompany);
    }

    /**
     * When the user has made the needed changes
     * a new Consultation object will be created which replaces
     * the old information with the new information
     * @return Consultation
     */

    public Consultation getConsultation(){
        Integer consultationID = null;
        if (selectedConsultation != null){
            consultationID = selectedConsultation.getConsultationID();
        }
        String consultationName = consultationNameTextField.getText();
        LocalDate ldStartDate = startDatePicker.getValue();
        LocalDate ldEndDate = endDatePicker.getValue();
        ArrayList<Employee> returnableEmployees = employeeArrayList;
        return new Consultation(consultationID, consultationName, ldStartDate, ldEndDate, returnableEmployees);
    }

    public int getCompanyID(){
        return selectedParentCompany.get().getCompanyID();
    }
}
