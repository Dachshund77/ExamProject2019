package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindEducationSub;
import Application.Controller.SubControllers.Find.FindInterviewSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Education;
import Domain.Interview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import javax.swing.text.View;

public class FindInterviewToDelete extends AbstractController {


    @FXML
    private FindInterviewSub findInterviewSubController;

    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct
    @FXML
    private Button cancelButton;

    /**
     * disables the confirm button
     * until the user has selected a company
     */
    @FXML
    private void initialize(){
        // hook up the  button with subcontroller form correctness
        confirmationButton.disableProperty().bind(findInterviewSubController.getInterviewTableView().getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * initializes the findInterviewSubController
     * to have values from the searchContainer
     * @param searchContainer
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findInterviewSubController.initValues(searchContainer);
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
     * when the user has selected an interview
     * and pressed the "confirm" button
     * the user will be presented with a new stage
     * where it is possible to see the following
     * Interview ID
     * Interview name
     * @param event
     */
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //goto next
        Interview toBeDeletedInterview = findInterviewSubController.getInterviewTableView().getSelectionModel().getSelectedItem();

        //Get the search container
        SearchContainer currentSearch = findInterviewSubController.getFindSubController().getCurrentSearchContainer();

        confirmationButton.getScene().setRoot(ViewController.DELETE_INTERVIEW.loadParent(currentSearch, toBeDeletedInterview));
    }
}