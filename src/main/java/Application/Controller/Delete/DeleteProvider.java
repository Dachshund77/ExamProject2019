package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ProviderSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Provider;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class DeleteProvider extends AbstractController {
    @FXML
    private ProviderSub providerSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){
    providerSubController.setDisabled(true);
    }

    /**
     * When the user has selected a provider
     * the TextField in the scene will be populated
     * with the consultation name
     *
     * @param searchContainer
     * @param provider
     */
    @Override
    public void initValues(SearchContainer searchContainer, Provider provider){
        previousSearch = searchContainer;
        providerSubController.initValues(provider);
    }


    /**
     * When the user clicks "Confirm"
     * a provider object with all
     * the selected information is deleted from the database
     *
     * @param actionEvent
     */
    public void handleConfirmation(ActionEvent actionEvent) {
        try {
            DbFacade.connect();
            DbFacade.deleteInterview(providerSubController.selectedProvider.getProviderID());
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
}
