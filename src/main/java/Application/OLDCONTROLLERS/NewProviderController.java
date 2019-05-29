package Application.OLDCONTROLLERS;

import Application.NEWSTUFF.Controller.AbstractController;
import Application.NEWSTUFF.Controller.ViewController;
import Domain.Provider;
import Foundation.DB;
import Persistance.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * OLDCONTROLLERS class for a simple scene where the User can enter a new provider for later use.
 */
public class NewProviderController extends AbstractController {

    @FXML
    public TextField newProviderTextField;

    private Provider selectedProvider;

    /**
     * Builds and empty provider and binds the values to the fields of this controller.
     */
    public void initialize(){
        selectedProvider = new Provider(null,null);
        selectedProvider.providerNameProperty().bind(newProviderTextField.textProperty());
    }

    /**
     * Confirms the form and writes the new Provider to the to the database.
     * @param event Clicked Conform button
     * @see DbFacade
     */
    @FXML
    public void handleConfirmForm(ActionEvent event) {
        //Connecting to the and executing sp_InsertProvider
        DB database = DB.getInstance();
        try{
            database.connect();
            DbFacade.insertProvider(selectedProvider);
            database.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                database.disconnect();
                //Change to mainView
                ViewController.START_PAGE_CONTROLLER.reLoad(newProviderTextField.getScene());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
