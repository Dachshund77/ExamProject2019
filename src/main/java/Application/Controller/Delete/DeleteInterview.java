package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.InterviewSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Interview;
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

public class DeleteInterview extends AbstractController {

    @FXML
    private InterviewSub interviewSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button returnButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){
       interviewSubController.setDisabled(true);
    }

    /**
     * When the user has selected an employee
     * the TextField in the scene will be populated
     * with the consultation name
     *
     * @param searchContainer
     * @param interview
     */
    @Override
    public void initValues(SearchContainer searchContainer, Interview interview){
        previousSearch = searchContainer;
        interviewSubController.initValues(interview);
    }

    /**
     * When the user clicks "Confirm"
     * a new interview object with all
     * the information is deleted from the database
     *
     * @param actionEvent
     */
    public void handleConfirmation(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletion Warning");
        alert.setHeaderText("You are about to delete an Interview!");
        alert.setContentText("This Action will delete this Interview permanently!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                DbFacade.connect();
                DbFacade.deleteInterview(interviewSubController.selectedInterview.getInterviewID());

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
            info.setContentText("Interview was deleted from the Database Successfully!");
            info.showAndWait();

            confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
        }
    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_INTERVIEW_TO_DELETE.loadParent(previousSearch));
    }
}
