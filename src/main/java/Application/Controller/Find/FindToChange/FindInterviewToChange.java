package Application.Controller.Find.FindToChange;

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

import java.sql.SQLException;

public class FindInterviewToChange extends AbstractController {
    @FXML
    private FindInterviewSub findInterviewSubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private TableView<DisplayInterview> interviewTableView;


    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        interviewTableView = findInterviewSubController.getInterviewTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(interviewTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * {@inheritDoc}
     * <br/><br/>
     * Calling this initValues will ultimately invoke {@link Application.Controller.SubControllers.Find.FindSub#initValues(SearchContainer) FindSub} reset.
     * This will set the search form to the values of the given SearchContainer.
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findInterviewSubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen
        Parent root = confirmationButton.getScene().getRoot();
        root.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        Interview toBeChangedInterview = null;

        //Get selection
        DisplayInterview selectedInterview = interviewTableView.getSelectionModel().getSelectedItem();
        int id = selectedInterview.getInterviewID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeChangedInterview = DbFacade.findInterviewByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        //Get the search container
        SearchContainer currentSearch = findInterviewSubController.getFindSubController().getCurrentSearchContainer();

        //Goto Change Company
        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.ALTER_INTERVIEW.loadParent(currentSearch, toBeChangedInterview));

    }
}
