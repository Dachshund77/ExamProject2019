package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Application.Controller.PopUp.Alter.AlterInterviewPopUp;
import Application.Controller.PopUp.Find.FindInterviewPopUp;
import Domain.Employee;
import Domain.Interview;
import UI.InterviewChoice;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class EmployeeSub extends AbstractController {

    public Label employeeIDText;
    public TextField employeeFirstNameTextField;
    public TextField employeeLastNameTextField;
    public TextField cprNrTextField;
    public TextField emailTextField;
    public TextField phoneNrTextField;
    public TableView<Interview> interviewTableView;
    public TableColumn<Interview,Integer> interviewIDColumn;
    public TableColumn<Interview,String> interviewNameColumn;
    public Button removeInterview;
    public Button addInterview;
    public Button seeDetailedInterviewButton; //maybe

    private ArrayList<Interview> interviews;

    public BooleanBinding isValid;
    public Employee selectedEmployee;

    private SimpleBooleanProperty employeeFirstNameIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty employeeLastNameIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty cprNrIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty eMailIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty phoneNrIsValid = new SimpleBooleanProperty(true);

    ObservableList<Interview> list = FXCollections.observableArrayList();

    /**
     * Initalizes the controller
     * Setting up listeners to TextFields and binding them
     * Sets up a TableView if an employee has been selected, with interview data for them.
     */
    public void initialize(){
        employeeFirstNameTextField.textProperty().addListener((observable -> handleFirstNameInput()));
        employeeLastNameTextField.textProperty().addListener((observable -> handleLastNameInput()));
        cprNrTextField.textProperty().addListener((observable -> handleCprNrInput()));
        emailTextField.textProperty().addListener((observable -> handleEmailInput()));
        phoneNrTextField.textProperty().addListener((observable -> handlePhoneNrInput()));

        interviewIDColumn.setCellValueFactory(new PropertyValueFactory<>("interviewID"));
        interviewNameColumn.setCellValueFactory(new PropertyValueFactory<>("interviewName"));

        interviewTableView.getColumns().setAll(interviewIDColumn, interviewNameColumn);
        Interview TestInterview =  new Interview(1,"Test 1",null,null,null,null,null,null,null);
        Interview TestInterview2 =  new Interview(2,"Test 2",null,null,null,null,null,null,null);
        Interview TestInterview3 =  new Interview(3,"Test 3",null,null,null,null,null,null,null);

        interviewTableView.setItems(list);

        list.add(TestInterview);
        list.add(TestInterview2);
        list.add(TestInterview3);

        isValid = new BooleanBinding() {
            {
                bind(employeeFirstNameIsValid);
                bind(employeeLastNameIsValid);
                bind(cprNrIsValid);
                bind(eMailIsValid);
                bind(phoneNrIsValid);
            }
            @Override
            protected boolean computeValue() {
                if (employeeFirstNameIsValid.get() && employeeLastNameIsValid.get()
                        && cprNrIsValid.get() && eMailIsValid.get() && phoneNrIsValid.get()) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        resetForm();
    }

    /**
     * Intializes the employee domain
     * And performs the resetForm() method, to prime the listeners
     * @param employee
     */
    @Override
    public void initValues(Employee employee) {
        selectedEmployee = employee;
        resetForm();
    }

    /**
     * On every keypress in the TextField employeeFirstNameTextField,
     * this method updates due to a listener.
     * Checks if the input is valid or not.
     * If the input is wrong, it displays a tooltip with the error and makes the field red
     */
    public void handleFirstNameInput(){
        if(Employee.isValidEmployeeFirstName(employeeFirstNameTextField.getText()))
        {
            employeeFirstNameTextField.setTooltip(null);
            employeeFirstNameIsValid.set(true);
            employeeFirstNameTextField.getStyleClass().remove("TextField-Error");
        } else {
            String invalidCause = Employee.employeeFirstNameInvalidCause(employeeFirstNameTextField.getText());
            employeeFirstNameTextField.setTooltip(new Tooltip(invalidCause));
            employeeFirstNameIsValid.set(false);
            if(!employeeFirstNameTextField.getStyleClass().contains("TextField-Error"))
                employeeFirstNameTextField.getStyleClass().add("TextField-Error");
        }
    }

    /**
     * On every keypress in the TextField employeeLastNameTextField,
     * this method updates due to a listener.
     * Checks if the input is valid or not.
     * If the input is wrong, it displays a tooltip with the error and makes the field red
     */
    public void handleLastNameInput(){
        if(Employee.isValidEmployeeLastName(employeeLastNameTextField.getText()))
        {
            employeeLastNameTextField.setTooltip(null);
            employeeLastNameIsValid.set(true);
            employeeLastNameTextField.getStyleClass().remove("TextField-Error");
        } else {
            String invalidCause = Employee.employeeLastNameInvalidCause(employeeLastNameTextField.getText());
            employeeLastNameTextField.setTooltip(new Tooltip(invalidCause));
            employeeLastNameIsValid.set(false);
            if(!employeeLastNameTextField.getStyleClass().contains("TextField-Error"))
                employeeLastNameTextField.getStyleClass().add("TextField-Error");
        }
    }

    /**
     * On every keypress in the TextField cprNrTextField,
     * this method updates due to a listener.
     * Checks if the input is valid or not.
     * If the input is wrong, it displays a tooltip with the error and makes the field red
     */
    public void handleCprNrInput(){
        if(Employee.isValidCprNr(cprNrTextField.getText()))
        {
            cprNrTextField.setTooltip(null);
            cprNrIsValid.set(true);
            cprNrTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = Employee.cprNrInvalidCause(cprNrTextField.getText());
            cprNrTextField.setTooltip(new Tooltip(invalidCause));
            cprNrIsValid.set(false);
            if(!cprNrTextField.getStyleClass().contains("TextField-Error"))
                cprNrTextField.getStyleClass().add("TextField-Error");
        }
    }

    /**
     * On every keypress in the TextField emailTextField,
     * this method updates due to a listener.
     * Checks if the input is valid or not.
     * If the input is wrong, it displays a tooltip with the error and makes the field red
     */
    public void handleEmailInput(){
        if(Employee.isValidEmail(emailTextField.getText()))
        {
            emailTextField.setTooltip(null);
            eMailIsValid.set(true);
            emailTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = Employee.eMailInvalidCause(emailTextField.getText());
            emailTextField.setTooltip(new Tooltip(invalidCause));
            eMailIsValid.set(false);
            if(!emailTextField.getStyleClass().contains("TextField-Error"))
                emailTextField.getStyleClass().add("TextField-Error");
        }
    }

    /**
     * On every keypress in the TextField phoneNrTextField,
     * this method updates due to a listener.
     * Checks if the input is valid or not.
     * If the input is wrong, it displays a tooltip with the error and makes the field red
     */
    public void handlePhoneNrInput(){
        if(Employee.isValidPhoneNr(phoneNrTextField.getText()))
        {
            phoneNrTextField.setTooltip(null);
            phoneNrIsValid.set(true);
            phoneNrTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = Employee.phoneNumberInvalidCause(phoneNrTextField.getText());
            phoneNrTextField.setTooltip(new Tooltip(invalidCause));
            phoneNrIsValid.set(false);
            if(!phoneNrTextField.getStyleClass().contains("TextField-Error"))
                phoneNrTextField.getStyleClass().add("TextField-Error");
        }
    }

    /**
     * A method to remove a interview from the TableView
     * @param event Upon selecting an item from the table and clicking "Remove interview"
     */
    public void handleRemoveInterview(ActionEvent event){
        Interview selectedData = interviewTableView.getSelectionModel().getSelectedItem();
        list.remove(selectedData);
    }

    /**
     * A method to add a interview to the Employee and Table
     * @param event Upon clicking "Add interview"
     */
    public void handleAddInterview(ActionEvent event){
        //open popup and stuff
        InterviewChoice newSeeInterview = new InterviewChoice();
        //Interview foundInterview = newSeeInterview.showAndReturn(new AlterInterviewPopUp()); //TODO : After Alter pop has been created
    }

    /**
     * A method to disable TextFields, in cases that needs it
     * @param bool true or false, depending on if fields should be disabled
     */
    public void setDisabled(boolean bool){
        employeeFirstNameTextField.setDisable(bool);
        employeeLastNameTextField.setDisable(bool);
        cprNrTextField.setDisable(bool);
        emailTextField.setDisable(bool);
        phoneNrTextField.setDisable(bool);
    }

    /**
     * Method to reset the TextFields, either manually or through startup
     * Checks if you got here by selecting an employee or through new.
     * If an employee is selected, it will setup the fields with information
     */
    public void resetForm(){
        if(selectedEmployee != null)
        {
            employeeFirstNameTextField.setText(selectedEmployee.getEmployeeFirstName());
            employeeLastNameTextField.setText(selectedEmployee.getEmployeeLastName());
            cprNrTextField.setText(selectedEmployee.getCprNr());
            emailTextField.setText(selectedEmployee.getEmail());
            phoneNrTextField.setText(selectedEmployee.getPhoneNr());
            interviewTableView.setVisible(true);
            addInterview.setVisible(true);
            removeInterview.setVisible(true);
        }
        else
        {
            employeeFirstNameTextField.setText("");
            employeeLastNameTextField.setText("");
            cprNrTextField.setText("");
            emailTextField.setText("");
            phoneNrTextField.setText("");
            interviewTableView.setVisible(false);
            addInterview.setVisible(false);
            removeInterview.setVisible(false);
        }
    }
}
