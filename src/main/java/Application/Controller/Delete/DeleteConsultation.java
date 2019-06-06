package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ConsultationSub;
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
        Consultation createToDeleteConsultationObj = new Consultation(consultationSubController.selectedConsultation.getConsultationID(),
                consultationSubController.selectedConsultation.getConsultationName(), consultationSubController.selectedConsultation.getStartDate(),
                consultationSubController.selectedConsultation.getEndDate(), consultationSubController.selectedConsultation.getEmployees());

        try {
            DbFacade.connect();
            DbFacade.deleteConsultation(createToDeleteConsultationObj.getConsultationID());
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
}
