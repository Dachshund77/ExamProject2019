package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ConsultationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Consultation;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class AlterConsultation extends AbstractController {

    @FXML
    private ConsultationSub consultationSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;


    private SearchContainer previousSearch;

    /**
     * initalizes the confirmationButton and disables it
     * until the required textfields have valid content
     */
    @FXML
    private void initialize() {
        confirmationButton.disableProperty().bind(consultationSubController.isValid.not());
        consultationSubController.resetForm();
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

            /*
            Consultation returnedConsultation = consultationSubController.getConsultationTableView().getSelectionModel().getSelectedItem();
            SearchContainer currentSearch = findConsultationSubController.getFindSubController().getCurrentSearchContainer();
            */

            Parent root = cancelButton.getScene().getRoot();
            ((BorderPane) root).setCenter(ViewController.ALTER_CONSULTATION.loadParent(previousSearch));
        } else {
            cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
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
