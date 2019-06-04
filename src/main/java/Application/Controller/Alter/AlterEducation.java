package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EducationSub;
import Application.SearchContainer;
import Domain.Education;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlterEducation extends AbstractController {
    @FXML //Note that private fields should have annotation, else the fxml cant inject
    private EducationSub educationSubController; //it is fxId in the fxml + Controller. NOTE that this is case sensetive so you prbably needto change the first letter to inforce camel casing.
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private SearchContainer previousSearch; // This will be loaded up when we come from searching so that we can return to the search


    /**
     * initializes the confirmationbutton
     * it is disabled until the required
     * Textfields have valid content
     */
    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(educationSubController.isValid.not()); //Note the not, that important to inverse the boolean statment
    }

    @Override
    public void initValues(SearchContainer searchContainer, Education education) {
        //Save search container for returning
        previousSearch = searchContainer;

        //propergate Consultation to setup form
        educationSubController.initValues(education); // Be careful that you implement the correct init values, else you will get unsupported Exception

    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){
            // go to findEducationTo change with initValue call
        } else {
            // goto main screen
        }
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        System.out.println(educationSubController.isValid.get());
    }

    @FXML
    private void handleReset(ActionEvent event) {
        educationSubController.resetForm();
    }
}
