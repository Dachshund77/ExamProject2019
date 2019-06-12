package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ConsultationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Company;
import Domain.DomainObjects.Consultation;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
    public void initValues(SearchContainer searchContainer, Consultation consultation, Company company) {
        previousSearch = searchContainer;
        consultationSubController.initValues(consultation, company);
        //Update confirmation button
        confirmationButton.setText("Change");
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){
            Parent root = cancelButton.getScene().getRoot();
            ((BorderPane) root).setCenter(ViewController.FIND_CONSULTATION_TO_CHANGE.loadParent(previousSearch));
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
    private void handleConfirmation(ActionEvent event) { //TODO needs fix
       Consultation consultation = consultationSubController.getConsultation();
       int companyId =  consultationSubController.getCompanyID();

       boolean outcome = true;
        try {
            DbFacade.connect();
            DbFacade.insertConsultation(consultation, companyId);

        } catch (SQLException e) {
            outcome = false;
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //Send confirmation
        if (outcome) {
            if (consultation.getConsultationID() == null) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Success!");
                info.setHeaderText(null);
                info.setContentText("Consultation was added to the Database Successfully!");
                info.showAndWait();
            } else {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Success!");
                info.setHeaderText(null);
                info.setContentText("Consultation was updated in the Database Successfully!");
                info.showAndWait();
            }
        }else {
            Alert info = new Alert(Alert.AlertType.ERROR);
            info.setTitle("ERROR!");
            info.setHeaderText(null);
            info.setContentText("Encountered critical database error!");
            info.showAndWait();
        }

        confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    /**
     * @param event Resets the user required fields
     */
    @FXML
    private void handleReset(ActionEvent event) {
        consultationSubController.resetForm();
    }
}
