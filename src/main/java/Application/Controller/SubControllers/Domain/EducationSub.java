package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.Education;
import Domain.Provider;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.event.Event;
import java.time.LocalDate;
import java.util.ArrayList;


public class EducationSub extends AbstractController {

    public Label AmuNrText;
    public TextField educationNameTextField;
    public TextArea descriptionTextArea;
    public TextField noOfDaysTextField; // may be converted to a drop down if you want
    public ListView<LocalDate> dateListView;
    public DatePicker datePicker;
    public Button addDateButton;
    public Button removeDateButton;
    public Button pickProviderButton;

    public ArrayList<LocalDate> dates;
    public Provider selectedProvider; // The provider this education has
    public Education selectedEducation; // WHen coming from find this need to be loaded up

    public BooleanBinding isValid;

    //Flags for the booleanBinding
    private SimpleBooleanProperty educationNameIsValid = new SimpleBooleanProperty(true); //Those field should start out as true or false depending on if they may be null orn ot
    private SimpleBooleanProperty descriptionIsValid = new SimpleBooleanProperty(true); //What you chose ins actually not important, as we set them immideatly with the first call to the validation in reset form
    private SimpleBooleanProperty noOfDaysIsValid = new SimpleBooleanProperty(true); //This is just set tre since the the method for that is not implemented

    public void initialize() {
        //setup ListView (later at some point we will do that not relevant for demonstration purpose)
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
                // validate the actual expression
                if (educationNameIsValid.get() && descriptionIsValid.get() && noOfDaysIsValid.get()) {
                    System.out.println("true");
                    return true;
                } else {
                    System.out.println("false");
                    return false;
                }
            }
        };

        // we should at least call reset for here once so that the start validation is set
        resetForm();
    }

    @Override
    public void initValues(Education education) {
        //Hook up education
        selectedEducation = education;
        resetForm(); //easy set of all values to the form if you wrote the reset form correctlty ofc
    }

    private void handleEducationNameInput() {
        //Gets text validates it and reacts on it. Is called whenever something happens to the text property of the textfield
        if (Education.isValidEducationName(educationNameTextField.getText())) { //put into method
            educationNameTextField.setTooltip(null); //Remove tooltip
            educationNameIsValid.set(true); //Activate flag
            educationNameTextField.getStyleClass().remove("TextField-Error"); //At some point we can maybe style some stuff in the CSS file...
        } else {
            String invalidCause = Education.educationNameInvalidCause(educationNameTextField.getText());
            educationNameTextField.setTooltip(new Tooltip(invalidCause)); //set tooltip
            educationNameIsValid.set(false); //Set flag
            educationNameTextField.getStyleClass().add("TextField-Error");
        }
    }

    private void handleDescriptionInput() {
        //Gets text validates it and reacts on it. Is called whenever something happens to the text property of the textfield
        if (Education.isValidDescription(descriptionTextArea.getText())) {
            descriptionTextArea.setTooltip(null);
            descriptionIsValid.set(true);
            descriptionTextArea.getStyleClass().remove("TextArea-Error");
        } else {
            String invalidCause = Education.descriptionInvalidCause(descriptionTextArea.getText());
            descriptionTextArea.setTooltip(new Tooltip((invalidCause)));
            descriptionIsValid.set(false);
            descriptionTextArea.getStyleClass().add("TextArea-Error");
        }
    }

    private void handleNoOfDaysInput() {
        // fix for integer incoming but here the same stuff happens as the previous two
    }


    public void setDisabled(boolean bool) {
        //Was changed from set editable to set Disable same logic better naming convention for reflecting thr actual code
        educationNameTextField.setDisable(bool);
        descriptionTextArea.setDisable(bool);
        noOfDaysTextField.setDisable(bool);
    }

    public void resetForm() {
        //Reset fields, set field if it has a selected Domain object
        //If no previous education was loaded, just reset evrything  to start values, if education was loaded reset to educations fields.
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

}
