package Application.Controller.Delete;

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
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;
import java.util.Optional;

public class DeleteConsultation extends AbstractController {

    @FXML
    private ConsultationSub consultationSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button returnButton;

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
    public void initValues(SearchContainer searchContainer, Consultation consultation, Company company){
        previousSearch = searchContainer;
        consultationSubController.initValues(consultation, company);
    }

    /**
     * When the user clicks "Confirm"
     * a new consultation object with all
     * the information is deleted from the database
     * @param actionEvent
     */
    public void handleConfirmation(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletion Warning");
        alert.setHeaderText("You are about to delete a Consultation!");
        alert.setContentText("This Action will delete this Consultation permanently!");
        Optional<ButtonType> result = alert.showAndWait();

        boolean outcome = false;
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                DbFacade.connect();
                outcome = DbFacade.deleteConsultation(consultationSubController.selectedConsultation.getConsultationID());


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    DbFacade.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (outcome) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Success!");
                info.setHeaderText(null);
                info.setContentText("Consultation was deleted from the Database Successfully!");
                info.showAndWait();
            }else {
                Alert info = new Alert(Alert.AlertType.ERROR);
                info.setTitle("ERROR!");
                info.setHeaderText(null);
                info.setContentText("Encountered critical database error!");
                info.showAndWait();
            }

            confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
        }
    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_CONSULTATION_TO_DELETE.loadParent(previousSearch));
    }
}
