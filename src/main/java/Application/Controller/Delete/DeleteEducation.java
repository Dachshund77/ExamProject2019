package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EducationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Education;
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

public class DeleteEducation extends AbstractController {

    @FXML
    private EducationSub educationSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button returnButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){
        educationSubController.setDisabled(true);
    }

    /**
     * When the user has selected an education
     * the TextField in the scene will be populated
     * with the consultation name
     * @param searchContainer
     * @param education
     */
    @Override
    public void initValues(SearchContainer searchContainer, Education education){
        previousSearch = searchContainer;
        educationSubController.initValues(education);
    }

    /**
     * When the user clicks "Confirm"
     * a new education object with all
     * the information is deleted from the database
     * @param actionEvent
     */
    public void handleConfirmation(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletion Warning");
        alert.setHeaderText("You are about to delete an Education!");
        alert.setContentText("This Action will delete this Education permanently!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                DbFacade.connect();
                DbFacade.deleteEducation(educationSubController.selectedEducation.getAmuNr());

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    DbFacade.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Education was deleted from the Database Successfully!");
            info.showAndWait();

            confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
        }
    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_EDUCATION_TO_DELETE.loadParent(previousSearch));
    }
}
