package Application.Controller.Find.FindToRecord;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindInterviewSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayInterview;
import Domain.DomainObjects.Employee;
import Domain.DomainObjects.Interview;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class FindInterviewToRecord extends AbstractController {

    @FXML
    private FindInterviewSub findInterviewSubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct
    @FXML
    private Button cancelButton;

    private TableView<DisplayInterview> interviewTableView;

    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        interviewTableView = findInterviewSubController.getInterviewTableView();

        confirmationButton.disableProperty().bind(interviewTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findInterviewSubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Init values
        Interview toBeShownInterview = null;
        Employee parentEmployee = null;

        //Get selection
        DisplayInterview selectedProvider = interviewTableView.getSelectionModel().getSelectedItem();
        int id = selectedProvider.getInterviewID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            parentEmployee = DbFacade.findEmployeeByInterviewID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (parentEmployee != null) {
            for (Interview interview : parentEmployee.getInterviews()) {
                if (interview.getInterviewID() == id){
                    toBeShownInterview = interview;
                }
            }
        }


        SearchContainer currentSearch = findInterviewSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.RECORD_INTERVIEW.loadParent(currentSearch, toBeShownInterview, parentEmployee));
    }
}
