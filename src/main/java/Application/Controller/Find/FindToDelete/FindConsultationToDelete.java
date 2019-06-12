package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindConsultationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayConsultation;
import Domain.DomainObjects.Company;
import Domain.DomainObjects.Consultation;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import java.sql.SQLException;

public class FindConsultationToDelete extends AbstractController {


    @FXML
    private FindConsultationSub findConsultationSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<DisplayConsultation> consultationTableView;
    /**
     * disables the confirm button
     * until the user has selected a consultation
     */
    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        consultationTableView = findConsultationSubController.getConsultationTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(consultationTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * initializes the findConsultationController
     * to have values from the searchContainer
     * @param searchContainer
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findConsultationSubController.initValues(searchContainer);
    }

    /**
     * If the user clicks the "cancel" button
     * the user will return to the main screen
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
     * where it is possible to see the following;
     * Consultation ID
     * Consultation name
     * Consultation start date
     * Consultation end date
     * @param event
     */
    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //init values
        Consultation toBeDeletedConsultation = null;
        Company parentCompany = null;

        //Get selection
        DisplayConsultation selectedConsultation = consultationTableView.getSelectionModel().getSelectedItem();
        int id = selectedConsultation.getConsultationID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            parentCompany = DbFacade.findCompanyByConsultationID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        //find the attached consultation
        if (parentCompany != null){
            for (Consultation consultation : parentCompany.getConsultations()) {
                if (consultation.getConsultationID() == id){
                    toBeDeletedConsultation = consultation;
                }
            }
        }

        SearchContainer currentSearch = findConsultationSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.DELETE_CONSULTATION.loadParent(currentSearch, toBeDeletedConsultation, parentCompany));
    }
}