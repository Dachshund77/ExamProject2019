package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ConsultationSub;
import Application.SearchContainer;
import Domain.DomainObjects.Consultation;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class AlterConsultation extends AbstractController {

    @FXML
    private ConsultationSub consultationSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    /**
     * initalizes the confirmationButton and disables it
     * until the required textfields have valid content
     */
    @FXML
    private void initialize() {
        confirmationButton.disableProperty().bind(consultationSubController.isValid.not());
    }


    @Override
    public void initValues(SearchContainer searchContainer, Consultation consultation) {
        previousSearch = searchContainer;
        consultationSubController.initValues(consultation);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){

        } else {

        }
    }

    /**
     * When the user clicks on the "confirm" button
     * a consultation object is created and send to the database
     *
     * @param event create consultation object and send it to the database
     */
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

    /**
     * @param event Resets the user required fields
     */
    @FXML
    private void handleReset(ActionEvent event) {
        consultationSubController.resetForm();
    }
}
