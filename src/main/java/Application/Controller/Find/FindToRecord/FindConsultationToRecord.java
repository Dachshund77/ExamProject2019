package Application.Controller.Find.FindToRecord;

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

public class FindConsultationToRecord extends AbstractController {

    @FXML
    private FindConsultationSub findConsultationSubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct
    @FXML
    private Button cancelButton;

    private TableView<DisplayConsultation> consultationTableView;

    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        consultationTableView = findConsultationSubController.getConsultationTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(consultationTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findConsultationSubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //init values
        Consultation toBeShownConsultation = null;

        //Get selection
        DisplayConsultation selectedConsultation = consultationTableView.getSelectionModel().getSelectedItem();
        int id = selectedConsultation.getConsultationID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeShownConsultation = DbFacade.findConsultationByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        SearchContainer currentSearch = findConsultationSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.RECORD_CONSULTATION.loadParent(currentSearch, toBeShownConsultation));
    }
}
