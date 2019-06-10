package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ProviderSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Provider;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class AlterProvider extends AbstractController {


    @FXML
    private ProviderSub providerSubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct
    @FXML
    private Button cancelButton;
    private SearchContainer previousSearch;

    /**
     * disables the "Confirm" button
     * until the required TextFields are valid
     */
    @FXML
    private void initialize() {
        confirmationButton.disableProperty().bind(providerSubController.isValid.not());
        providerSubController.resetForm();
    }

    @Override
    public void initValues(SearchContainer searchContainer, Provider provider) {
        //Save search container for returning
        previousSearch = searchContainer;
        //propergate Consultation to setup form
        providerSubController.initValues(provider);


    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){
            /*
            Provider returnedCompany = findConsultationSubController.getConsultationTableView().getSelectionModel().getSelectedItem();
            SearchContainer currentSearch = findConsultationSubController.getFindSubController().getCurrentSearchContainer();
            */

            Parent root = cancelButton.getScene().getRoot();
            ((BorderPane) root).setCenter(ViewController.FIND_PROVIDER_TO_CHANGE.loadParent(previousSearch));
        } else {
           cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
        }
    }

    /**
     * Updates the database with a new company
     *
     * @param event on user click, create a new provider obj
     *              with content from the textfields,
     *              which is then send to the database
     */

    @FXML
    private void handleConfirmation(ActionEvent event) { //TODO  need correction
        Provider provider = providerSubController.getProvider();

        //Send confirmation
        if (provider.getProviderID() == null){
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Provider was added to the Database Successfully!");
            info.showAndWait();
        }else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Provider was updated in the Database Successfully!");
            info.showAndWait();
        }

        try {
            DbFacade.connect();
            DbFacade.insertProvider(provider);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    /**
     * Resets the TextFields
     * if the user isnt satisfied with
     * what is entered
     *
     * @param event on user click,
     *              resets all TextFields
     */
    @FXML
    private void handleReset(ActionEvent event) {
        providerSubController.resetForm();
    }
}
