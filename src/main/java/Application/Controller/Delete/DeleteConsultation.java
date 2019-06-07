package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ConsultationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Consultation;
import Domain.Education;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class DeleteConsultation extends AbstractController {

    @FXML
    private ConsultationSub consultationSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){
        consultationSubController.setDisabled(true);
    }

    /**
     * When the user has selected a consultation
     * the TextField in the scene will be populated
     * with the consultation name
     * @param searchContainer
     * @param consultation
     */
    @Override
    public void initValues(SearchContainer searchContainer, Consultation consultation){
        previousSearch = searchContainer;
        consultationSubController.initValues(consultation);
    }

    /**
     * When the user clicks "Confirm"
     * a new consultation object with all
     * the information is deleted from the database
     * @param actionEvent
     */
    public void handleConfirmation(ActionEvent actionEvent) {
        try {
            DbFacade.connect();
            DbFacade.deleteConsultation(consultationSubController.selectedConsultation.getConsultationID());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }
}
