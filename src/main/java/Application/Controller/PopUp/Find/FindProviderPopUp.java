package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.ProviderReturnableController;
import Application.Controller.SubControllers.Find.FindProviderSub;
import Application.Controller.ViewController;
import Domain.Provider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class FindProviderPopUp extends ProviderReturnableController {
    @FXML
    private FindProviderSub findProviderSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<Provider> providerTableView;
    @FXML
    private Provider selectedProvider;


    public void initialize() {
        //Init fields
        selectedProvider = null;

        // Load the TableView reference from subController
        providerTableView = findProviderSubController.getProviderTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(providerTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    public void handleConfirmation(ActionEvent actionEvent) {
        Stage stage = (Stage) confirmationButton.getScene().getWindow();
        selectedProvider = providerTableView.getSelectionModel().getSelectedItem(); //Confirmation can only be activated if something is selected
        stage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        selectedProvider = null;
        stage.close();
    }

    @Override
    public String getURL() {
        return ViewController.FIND_COMPANY_POPUP.getURL();
    }

    /**
     * @return Loads the FindProviderPopUp Stage
     */
    @Override
    public Provider getReturn() {
        return selectedProvider;
    }
}
