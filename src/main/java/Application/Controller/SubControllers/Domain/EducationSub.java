package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Application.Controller.PopUp.Find.FindProviderPopUp;
import Domain.DomainObjects.Education;
import Domain.DomainObjects.Provider;
import UI.ProviderChoice;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class EducationSub extends AbstractController {

    @FXML
    private TextField educationNameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField noOfDaysTextField; // may be converted to a drop down if you want
    @FXML
    private ListView<LocalDate> educationDatesListView;
    @FXML
    private DatePicker educationDatePicker;
    @FXML
    private Button addDateButton;
    @FXML
    private Button removeDateButton;
    @FXML
    private Text educationAmurNrText;
    @FXML
    private Button selectProviderButton;


    public ObservableList<LocalDate> dates;

    public Provider selectedProvider; // The provider this education has
    public Education selectedEducation; // WHen coming from find this need to be loaded up

    public BooleanBinding isValid;

    //Flags for the booleanBinding
    private SimpleBooleanProperty educationNameIsValid = new SimpleBooleanProperty(true); //Those field should start out as true or false depending on if they may be null orn ot
    private SimpleBooleanProperty descriptionIsValid = new SimpleBooleanProperty(true); //What you chose ins actually not important, as we set them immideatly with the first call to the validation in reset form
    private SimpleBooleanProperty noOfDaysIsValid = new SimpleBooleanProperty(true); //This is just set tre since the the method for that is not implemented
    private SimpleBooleanProperty validProvider = new SimpleBooleanProperty(false);

    public void initialize() {
        //setup ListView
        dates = FXCollections.observableArrayList();
        educationDatesListView.setItems(dates);

        //Setup the add date button to only be active when the datepicker has value
        addDateButton.disableProperty().bind(educationDatePicker.valueProperty().isNull());

        //Setup the remove date button to only be active when a date is picked
        removeDateButton.disableProperty().bind(educationDatesListView.getSelectionModel().selectedItemProperty().isNull());

        //Setup The textfield listeners
        educationNameTextField.textProperty().addListener((observable) -> handleEducationNameInput());
        descriptionTextArea.textProperty().addListener((observable) -> handleDescriptionInput());
        noOfDaysTextField.textProperty().addListener((observable) -> handleNoOfDaysInput());

        /*setup master isValid Boolean Binding
        The reason we need the binding is that a binding is actually a change listener. So whenever something changes it
        notifies  other bound values.
        */
        isValid = new BooleanBinding() {
            @Override
            protected boolean computeValue() { //so when all flags are true, this binding is true.
                //Here we tell when to fire this event, so when one of those propertys change fire the validation
                bind(educationNameIsValid);
                bind(descriptionIsValid);
                bind(noOfDaysIsValid);
                bind(validProvider);
                // validate the actual expression
                return educationNameIsValid.get() && descriptionIsValid.get() && noOfDaysIsValid.get() && validProvider.get();
            }
        };
        resetForm();
    }

    @Override
    public void initValues(Education education) { //TODO ADD DATES and setup
        selectedEducation = education;
        resetForm();
    }

    private void handleEducationNameInput() {
        //Gets text validates it and reacts on it. Is called whenever something happens to the text property of the textfield
        if (Education.isValidEducationName(educationNameTextField.getText())) { //put into method
            educationNameTextField.setTooltip(null); //Remove tooltip
            educationNameIsValid.set(true); //Activate flag
            educationNameTextField.getStyleClass().removeAll("TextField-Error"); //At some point we can maybe style some stuff in the CSS file...
        } else {
            String invalidCause = Education.educationNameInvalidCause(educationNameTextField.getText());
            educationNameTextField.setTooltip(new Tooltip(invalidCause)); //set tooltip
            educationNameIsValid.set(false); //Set flag
            if (!educationNameTextField.getStyleClass().contains("TextField-Error")) {
                educationNameTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleDescriptionInput() {
        if (Education.isValidDescription(descriptionTextArea.getText())) {
            descriptionTextArea.setTooltip(null);
            descriptionIsValid.set(true);
            descriptionTextArea.getStyleClass().removeAll("TextArea-Error");
        } else {
            String invalidCause = Education.descriptionInvalidCause(descriptionTextArea.getText());
            descriptionTextArea.setTooltip(new Tooltip((invalidCause)));
            descriptionIsValid.set(false);
            if (!descriptionTextArea.getStyleClass().contains("TextArea-Error")) {
                descriptionTextArea.getStyleClass().add("TextArea-Error");
            }
        }
    }

    private void handleNoOfDaysInput() {
        if (Education.isValidNoOfDays(noOfDaysTextField.getText())) {
            noOfDaysTextField.setTooltip(null);
            noOfDaysIsValid.set(true);
            noOfDaysTextField.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = Education.noOfDaysInvalidCause(noOfDaysTextField.getText());
            noOfDaysTextField.setTooltip(new Tooltip((invalidCause)));
            noOfDaysIsValid.set(false);
            if (!noOfDaysTextField.getStyleClass().contains("TextField-Error")) {
                noOfDaysTextField.getStyleClass().add("TextField-Error");
            }
        }
    }

    private void handleProviderPickedInput() {
        //TODO need fxml implementation
    }


    public void setDisabled(boolean bool) {
        educationNameTextField.setDisable(bool);
        descriptionTextArea.setDisable(bool);
        noOfDaysTextField.setDisable(bool);
    }

    public void resetForm() {
        if (selectedEducation != null) {
            educationNameTextField.setText(selectedEducation.getEducationName());
            descriptionTextArea.setText(selectedEducation.getDescription());
            noOfDaysTextField.setText(selectedEducation.getNoOfDays().toString());
        } else {
            educationNameTextField.setText("");
            descriptionTextArea.setText("");
            noOfDaysTextField.setText("");
        }
    }

    public void handleAddDate(ActionEvent event) {
        LocalDate tempDate = educationDatePicker.getValue();
        dates.add(tempDate);
        educationDatePicker.setValue(null);
    }

    public void handleRemoveDate(ActionEvent event) {
        LocalDate toBeRemoved = educationDatesListView.getSelectionModel().getSelectedItem();
        dates.remove(toBeRemoved);
    }

    public Education getEducation() {
        //TODO Implement this
        //build the object either with null id or loaded id, depending on if we change or not change and existing object.
        return null;
    }

    public void handleProviderSelection(ActionEvent event) {
        Provider returnedProvider = null;
        ProviderChoice popUp = new ProviderChoice();
        returnedProvider = popUp.showAndReturn(new FindProviderPopUp());

        if (returnedProvider != null){

        }
    }
}
