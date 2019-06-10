package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.InterviewSub;
import Application.SearchContainer;
import Domain.DomainObjects.Interview;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class AlterInterview extends AbstractController {

    @FXML
    private InterviewSub interviewSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(interviewSubController.isValid.not());
        interviewSubController.resetForm();
    }


    @Override
    public void initValues(SearchContainer searchContainer, Interview interview) {
        //Save search container for returning
        previousSearch = searchContainer;
        //propergate Consultation to setup form
        interviewSubController.initValues(interview);
    }

    @FXML
    private void handleCancel(ActionEvent event) { //TODO needs implementation
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){

        } else {

        }
    }

    /**
     * When the user clicks "confirm"
     * an interview object is created and send to the database
     * @param event sends an interview object to the database
     */
    @FXML
    private void handleConfirmation(ActionEvent event) { //TODO needs fix
        int productUnderstanding = Integer.parseInt(interviewSubController.productUnderstandingComboBox.getValue().toString());
        int problemUnderstanding = Integer.parseInt(interviewSubController.problemUnderstandingComboBox.getValue().toString());
        int qualityAwareness = Integer.parseInt(interviewSubController.qualityAwarenessComboBox.getValue().toString());
        int cooperation = Integer.parseInt(interviewSubController.cooperationComboBox.getValue().toString());
        int flexibility = Integer.parseInt(interviewSubController.flexibilityComboBox.getValue().toString());

        /*Interview createNewInterviewObj = new Interview(null,interviewSubController.interViewNameTextField.getText(),
                productUnderstanding,problemUnderstanding,qualityAwareness,cooperation,flexibility,null,null); //FIXME : Needs finished and education wishes
        */
        try {
            DbFacade.connect();
            //DbFacade.insertInterview(createNewInterviewObj,); // FIXME : Needs to get an employeeID
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param event Resets the user required fields
     */
    @FXML
    private void handleReset(ActionEvent event) {
        interviewSubController.resetForm();
    }
}
