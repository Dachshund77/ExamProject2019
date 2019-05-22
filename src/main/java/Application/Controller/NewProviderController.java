package Application.Controller;

import Application.AbstractController;
import Domain.Provider;
import Foundation.DB;
import Persistance.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * Controller class for a simple scene where the User can enter a new provider for later use.
 */
public class NewProviderController extends AbstractController {

    @FXML
    public TextField newProviderTextField;

    /**
     * Confirms the form and writes the new Provider to the to the database.
     * @param event Clicked Conform button
     * @see DbFacade
     */
    @FXML
    public void handleConfirmForm(ActionEvent event) {
        //Getting needed values
        String newProviderName = newProviderTextField.getText();

        //Connecting to the and executing sp_InsertProvider
        DB database = DB.getInstance();
        try{
            database.connect();
            DbFacade.insertProvider(new Provider(null,newProviderName));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                database.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
