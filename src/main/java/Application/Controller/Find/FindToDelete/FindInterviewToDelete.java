package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindInterviewSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayInterview;
import Domain.DomainObjects.Interview;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import javax.swing.text.View;
import java.sql.SQLException;

public class FindInterviewToDelete extends AbstractController {


    @FXML
    private FindInterviewSub findInterviewSubController;

    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<DisplayInterview> interviewTableView;

    /**
     * disables the confirm button
     * until the user has selected a company
     */
    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        interviewTableView = findInterviewSubController.getInterviewTableView();

        confirmationButton.disableProperty().bind(interviewTableView.getSelectionModel().selectedItemProperty().isNull());
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
    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Init values
        Interview toBeDeletedInterview = null;

        //Get selection
        DisplayInterview selectedProvider = interviewTableView.getSelectionModel().getSelectedItem();
        int id = selectedProvider.getInterviewID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeDeletedInterview = DbFacade.findInterviewByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }


        SearchContainer currentSearch = findInterviewSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.DELETE_INTERVIEW.loadParent(currentSearch, toBeDeletedInterview));
    }
}