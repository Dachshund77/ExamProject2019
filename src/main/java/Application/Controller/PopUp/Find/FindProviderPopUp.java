package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.ProviderReturnableController;
import Application.Controller.SubControllers.Find.FindProviderSub;
import Application.Controller.ViewController;
import Domain.DisplayObjects.DisplayProvider;
import Domain.DomainObjects.Provider;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FindProviderPopUp extends ProviderReturnableController {
    @FXML
    private FindProviderSub findProviderSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<DisplayProvider> providerTableView;

    private Provider selectedProvider;


    public void initialize() {
        //Init fields
        selectedProvider = null;

        // Load the TableView reference from subController
        providerTableView = findProviderSubController.getProviderTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(providerTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void handleConfirmation(ActionEvent actionEvent) {
        //Get selection
        DisplayProvider selectedItem = providerTableView.getSelectionModel().getSelectedItem();
        int id = selectedItem.getProviderID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            selectedProvider = DbFacade.findProviderByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) confirmationButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        selectedProvider = null;
        stage.close();
    }

    @Override
    public String getURL() {
        return ViewController.FIND_PROVIDER_POPUP.getURL();
    }

    /**
     * @return Loads the FindProviderPopUp Stage
     */
    @Override
    public Provider getReturn() {
        return selectedProvider;
    }
}
