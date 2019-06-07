package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Application.Controller.PopUp.Find.FindCompanyPopUp;
import Domain.Company;
import Domain.Consultation;
import Domain.Employee;
import UI.CompanyChoice;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
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

    private ArrayList<Employee> employeeArrayList;

    public BooleanBinding isValid;
    private SimpleBooleanProperty consultationNameIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty startDateIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty endDateIsValid = new SimpleBooleanProperty(true);
    public Consultation selectedConsultation;


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
            }
            @Override
            protected boolean computeValue() {
                if (consultationNameIsValid.get() && startDateIsValid.get() && endDateIsValid.get()) {
                    return true;
                } else
                return false;
            }
        };
        resetForm();
    }

    @Override
    public void initValues(Consultation consultation) {
        selectedConsultation = consultation;
        resetForm();
    }

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

    public void handleDatePickerInput() {
        LocalDate localDateStartDate = startDatePicker.getValue();
        LocalDate localDateEndDate = endDatePicker.getValue();
        System.out.println(localDateStartDate);
        System.out.println(localDateEndDate);

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

    public void resetForm() {
        if (selectedConsultation != null) {
            consultationNameTextField.setText(selectedConsultation.getConsultationName());
        } else {
            consultationNameTextField.setText("");
        }
    }

    public void handleSelectCompany(ActionEvent actionEvent) {
        CompanyChoice newSelectCompany = new CompanyChoice();
        Company foundCompany = newSelectCompany.showAndReturn(new FindCompanyPopUp());
    }
}
