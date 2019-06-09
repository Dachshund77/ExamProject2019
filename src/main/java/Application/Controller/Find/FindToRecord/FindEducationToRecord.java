package Application.Controller.Find.FindToRecord;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindEducationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayEducation;
import Domain.DomainObjects.Education;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class FindEducationToRecord extends AbstractController {

    @FXML
    private FindEducationSub findEducationSubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct
    @FXML
    private Button cancelButton;

    private TableView<DisplayEducation> educationTableView;

    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        educationTableView = findEducationSubController.getEducationTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(educationTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findEducationSubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Init values
        Education toBeShownEducation = null;

        //Get selection
        DisplayEducation selectedEducation = educationTableView.getSelectionModel().getSelectedItem();
        int id = selectedEducation.getAmuNr();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeShownEducation = DbFacade.findEducationByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        SearchContainer currentSearch = findEducationSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.RECORD_EDUCATION.loadParent(currentSearch, toBeShownEducation));
    }

}
