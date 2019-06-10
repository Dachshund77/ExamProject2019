package Application.Controller.Record;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ProviderSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Provider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordProvider extends AbstractController {
    @FXML
    private ProviderSub providerSubController;
    @FXML
    private Button returnButton;

    private SearchContainer previousSearchContainer = null;

    public void initialize(){
        providerSubController.setDisabled(true);
    }

    @Override
    public void initValues(SearchContainer searchContainer, Provider provider) {
        previousSearchContainer = searchContainer;
        providerSubController.initValues(provider);
    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_COMPANY_TO_RECORD.loadParent(previousSearchContainer));
    }
}
