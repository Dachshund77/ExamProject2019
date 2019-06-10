package Application.Controller.Alter;

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
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class AlterEducation extends AbstractController {

    @FXML
    private EducationSub educationSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private SearchContainer previousSearch;


    /**
     * initializes the confirmationbutton
     * it is disabled until the required
     * Textfields have valid content
     */
    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(educationSubController.isValid.not());
        educationSubController.resetForm();
    }

    @Override
    public void initValues(SearchContainer searchContainer, Education education) {
        previousSearch = searchContainer;
        educationSubController.initValues(education); // Be careful that you implement the correct init values, else you will get unsupported Exception
        //Update confirmation button
        confirmationButton.setText("Change");
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){
            Parent root = cancelButton.getScene().getRoot();
            ((BorderPane) root).setCenter(ViewController.FIND_EDUCATION_TO_CHANGE.loadParent(previousSearch));
        } else {
            // goto main screen
            cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
        }
    }

    /**
     * When the user clicks "confirm"
     * an education object is created and send to the database
     * @param event sends an education object to the database
     */
    @FXML
    private void handleConfirmation(ActionEvent event) { //TODO needs implementation
        Education education = educationSubController.getEducation();



        try {
            DbFacade.connect();
            DbFacade.insertEducation(education);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Send confirmation
        if (education.getAmuNr() == null){
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Education was added to the Database Successfully!");
            info.showAndWait();
        }else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Education was updated in the Database Successfully!");
            info.showAndWait();
        }

        confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    /**
     * @param event Resets the user required fields
     */
    @FXML
    private void handleReset(ActionEvent event) {
        educationSubController.resetForm();
    }
}
