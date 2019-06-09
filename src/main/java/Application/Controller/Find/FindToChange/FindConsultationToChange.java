package Application.Controller.Find.FindToChange;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindConsultationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayConsultation;
import Domain.DomainObjects.Consultation;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class FindConsultationToChange extends AbstractController {

    @FXML
    private FindConsultationSub findConsultationSubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private TableView<DisplayConsultation> consultationTableView;

    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        consultationTableView = findConsultationSubController.getConsultationTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(consultationTableView.getSelectionModel().selectedItemProperty().isNull());
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
        findConsultationSubController.initValues(searchContainer);
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
        //init values
        Consultation toBeChangedConsultation = null;

        //Get selection
        DisplayConsultation selectedConsultation = consultationTableView.getSelectionModel().getSelectedItem();
        int id = selectedConsultation.getConsultationID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeChangedConsultation = DbFacade.findConsultationByID(id);
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
        SearchContainer currentSearch = findConsultationSubController.getFindSubController().getCurrentSearchContainer();

        //Goto Change Company
        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.ALTER_CONSULTATION.loadParent(currentSearch, toBeChangedConsultation));
    }
}
