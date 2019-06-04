package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ProviderSub;
import Application.SearchContainer;
import Domain.Provider;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class AlterProvider extends AbstractController {

    @FXML
    private ProviderSub providerSub;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private SearchContainer previousSearch;

    /**
     * disables the "Confirm" button
     * until the required TextFields are valid
     */
    @FXML
    private void initialize() {
        confirmationButton.disableProperty().bind(providerSub.isValid.not());
    }

    @Override
    public void initValues(SearchContainer searchContainer, Provider provider) {
        //Save search container for returning
        previousSearch = searchContainer;
        //propergate Consultation to setup form
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){

        } else {

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
    private void handleConfirmation(ActionEvent event) {
        //Write to db

        Provider createProviderObj = new Provider(null, providerSub.providerNameTextfield.getText());
        try {
            DbFacade.connect();
            DbFacade.insertProvider(createProviderObj);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        providerSub.resetForm();
    }
}
