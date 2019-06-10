package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ConsultationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletion Warning");
        alert.setHeaderText("You are about to delete a Consultation!");
        alert.setContentText("This Action will delete this Consultation permanently!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                DbFacade.connect();
                DbFacade.deleteConsultation(consultationSubController.selectedConsultation.getConsultationID());

                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Success!");
                info.setHeaderText(null);
                info.setContentText("Consultation was deleted from the Database Successfully!");
                info.showAndWait();

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

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_CONSULTATION_TO_DELETE.loadParent(previousSearch));
    }
}
