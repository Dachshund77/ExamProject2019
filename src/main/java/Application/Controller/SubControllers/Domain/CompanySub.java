package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Application.Controller.PopUp.Find.FindCompanyPopUp;
import Domain.Company;
import Domain.Consultation;
import Domain.Education;
import Foundation.DbFacade;
import UI.CompanyChoice;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

//TODO NEED JAVADOC
public class CompanySub extends AbstractController { //TODO CLEAN UP CODE THAT WILL NOT BE USED, INCREASE READABILITY

    public Text companyIDText;
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
    public Button addCompany;
    public Button newConsultationButton;

    // FIXME: 29/05/2019 there should probably bee a add education stuff here, but there some object reference issues with that

    public ArrayList<Consultation> consultationArrayList = new ArrayList<>();
    public ArrayList<Education> educationArrayList;

    public Company selectedCompany;

    public BooleanBinding isValid;
    private SimpleBooleanProperty companyNameIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty cvrNrIsValid = new SimpleBooleanProperty(true);

    public void initialize() {


        //Setting up consultation TableView
        consultationNameColumn.setCellValueFactory(new PropertyValueFactory<>("consultationName"));
        consultationStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        consultationEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        consultationTableView.getColumns().setAll(consultationNameColumn, consultationStartDateColumn, consultationEndDateColumn);

         //Setting up EducationTableView
        educationNameColumn.setCellValueFactory(new PropertyValueFactory<>("educationName"));
        educationTableView.getColumns().setAll(educationNameColumn);
        ObservableList<Education> educationList = FXCollections.observableArrayList();
        educationTableView.setItems(educationList);


        //Hides the tableviews when the user selects "New Company"
        if (selectedCompany == null) {
            educationTableView.setVisible(false);
            consultationTableView.setVisible(false);
        }

        /*
         adds a listener to the handlers of
         cvrTextField and companyNameTextfield
         */
        cvrNrTextField.textProperty().addListener(((observable) -> handleCvrNrInput()));
        companyNameTextField.textProperty().addListener(((observable) -> handleCompanyNameInput()));

        /*
        binds the .isValid() to the controls
        and returns true or false depending
        if the requirements are met
         */
        isValid = new BooleanBinding() {
            {
                bind(companyNameIsValid);
                bind(cvrNrIsValid);
            }
            @Override
            protected boolean computeValue() {
                if (companyNameIsValid.get() && cvrNrIsValid.get()) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        resetForm();
    }

    /**
     * initializes the company domain
     * and primes the resetform
     *
     * @param company
     */
    @Override
    public void initValues(Company company) {
        selectedCompany = company;
        resetForm();
    }

    /**
     * On every keypress the cvrInputTextField
     * is updated to check if the content is valid
     * if not, the TextField will be red and a
     * tooltip with the error cause will show
     */
    public void handleCvrNrInput() {
        System.out.println(cvrNrTextField.getText());
        System.out.println(Company.isValidCvrNr(cvrNrTextField.getText()));
        System.out.println(Arrays.toString(cvrNrTextField.getStyleClass().toArray()));
        if (Company.isValidCvrNr(cvrNrTextField.getText())) {
            cvrNrTextField.setTooltip(null);
            cvrNrIsValid.set(true);
            cvrNrTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = Company.cvrNrInvalidCause(cvrNrTextField.getText());
            cvrNrTextField.setTooltip(new Tooltip(invalidCause));
            cvrNrIsValid.set(false);
            if (!cvrNrTextField.getStyleClass().contains("TextField-Error")) {
                cvrNrTextField.getStyleClass().add("TextField-Error");
            }

        }

    }

    /**
     * On every keypress the companyNameTextField
     * is updated to check if the content is valid
     * if not, the TextField will be red, and show
     * a tooltip with the cause for errors
     */
    public void handleCompanyNameInput() {
        if (Company.isValidCompanyName(companyNameTextField.getText())) {
            companyNameTextField.setTooltip(null);
            companyNameIsValid.set(true);
            companyNameTextField.getStyleClass().remove("TextField-Error");
        } else {
            String invalidCause = Company.companyNameInvalidClause(companyNameTextField.getText());
            companyNameTextField.setTooltip(new Tooltip(invalidCause));
            companyNameIsValid.set(false);
            companyNameTextField.getStyleClass().add("TextField-Error");
        }
    }

    /**
     * checks if both TextFields have valid content
     *
     * @param bool
     */
    public void setDisabled(boolean bool) {
        companyNameTextField.setDisable(bool);
        cvrNrTextField.setDisable(bool);
        newConsultationButton.setDisable(bool);
        addCompany.setDisable(bool);

    }

    /**
     * when the user clicks "reset"
     * all TextFields are cleared
     */
    public void resetForm() {
        if (selectedCompany != null) {
            companyNameTextField.setText(selectedCompany.getCompanyName());
            cvrNrTextField.setText(selectedCompany.getCvrNr());
        } else {
            companyNameTextField.setText("");
            cvrNrTextField.setText("");
        }

    }
}
