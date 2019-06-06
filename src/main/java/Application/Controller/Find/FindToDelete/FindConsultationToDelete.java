package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.PopUp.Find.FindConsultationPopUp;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.Controller.SubControllers.Find.FindCompanySub;
import Application.Controller.SubControllers.Find.FindConsultationSub;
import Application.Controller.SubControllers.Find.FindSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Company;
import Domain.Consultation;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import javax.swing.text.View;

public class FindConsultationToDelete extends AbstractController {


    @FXML
    private FindConsultationSub findConsultationSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;


    /**
     * disables the confirm button
     * until the user has selected a consultation
     */
    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(findConsultationSubController.getConsultationTableView().getSelectionModel().selectedItemProperty().isNull());
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
    @FXML
    private void handleConfirmation(ActionEvent event) {
        Consultation toBeDeletedConsultation = findConsultationSubController.getConsultationTableView().getSelectionModel().getSelectedItem();
        SearchContainer currentSearch = findConsultationSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.DELETE_CONSULTATION.loadParent(currentSearch, toBeDeletedConsultation));
    }
}