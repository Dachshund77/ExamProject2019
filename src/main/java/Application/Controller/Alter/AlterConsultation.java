package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ConsultationSub;
import Application.SearchContainer;
import Domain.Consultation;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class AlterConsultation extends AbstractController {

    @FXML
    private ConsultationSub consultationSubController; //SubController

    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private SearchContainer previousSearch;

    /**
     * initalizes the confirmationButton and disables it
     * until the required textfields have valid content
     */
    @FXML
    private void initialize() {
        // hook up the  button with subcontroller form correctness
        confirmationButton.disableProperty().bind(consultationSubController.isValid.not());
    }

    @Override
    public void initValues(SearchContainer searchContainer, Consultation consultation) {
        //Save search container for returning
        previousSearch = searchContainer;
        //propergate Consultation to setup form
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){

        } else {

        }
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Creates a new consultation Obj to send to the database
        Consultation createNewConsultationObj = new Consultation(null, consultationSubController.consultationNameTextField.getText(),
                consultationSubController.startDatePicker.getValue(), consultationSubController.endDatePicker.getValue(), null);
        try {
            DbFacade.connect();
            //FIXME Need pop-up implementation ↓↓↓
            //DbFacade.insertConsultation(createNewConsultationObj);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void handleReset(ActionEvent event) {
        // call subcontroller reset
        consultationSubController.resetForm();
    }
}
