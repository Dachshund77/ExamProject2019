package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindEducationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Education;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import javax.swing.text.View;

public class FindEducationToDelete extends AbstractController {


    @FXML
    private FindEducationSub findEducationSubController;

    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    /**
     * disables the confirm button
     * until the user has selected an education
     */
    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(findEducationSubController.getEducationTableView().getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * initializes the findEducationSubController
     * to have values from the searchContainer
     * @param searchContainer
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findEducationSubController.initValues(searchContainer);
    }

    /**
     * If the user clicks the "cancel" button
     * the user will return to the main scene
     * @param event
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    /**
     * when the user has selected a company
     * and pressed the "confirm" button
     * the user will be presented with a new stage
     * where it is possible to see the following
     * Education AMU No.
     * Education name
     * Education Description
     * Education Lenght ( number of days)
     * @param event
     */
    @FXML
    private void handleConfirmation(ActionEvent event) {
        Education toBeDeletedEducation = findEducationSubController.getEducationTableView().getSelectionModel().getSelectedItem();
        SearchContainer currentSearch = findEducationSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.DELETE_EDUCATION.loadParent(currentSearch, toBeDeletedEducation));
    }
}
