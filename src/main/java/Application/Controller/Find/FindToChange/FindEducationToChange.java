package Application.Controller.Find.FindToChange;

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

public class FindEducationToChange extends AbstractController {

    @FXML
    private FindEducationSub findEducationSubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private TableView<DisplayEducation> educationTableView;


    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        educationTableView = findEducationSubController.getEducationTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(educationTableView.getSelectionModel().selectedItemProperty().isNull());
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
        findEducationSubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen
        Parent root = confirmationButton.getScene().getRoot();
        root.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Init values
        Education toBeChangedEducation = null;

        //Get selection
        DisplayEducation selectedEducation = educationTableView.getSelectionModel().getSelectedItem();
        int id = selectedEducation.getAmuNr();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeChangedEducation = DbFacade.findEducationByID(id);
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
        SearchContainer currentSearch = findEducationSubController.getFindSubController().getCurrentSearchContainer();

        //Goto Change Company
        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.ALTER_EDUCATION.loadParent(currentSearch, toBeChangedEducation));

    }
}
