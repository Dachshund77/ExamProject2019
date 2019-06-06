package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ProviderSub;
import Application.SearchContainer;
import Domain.Provider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteProvider extends AbstractController {
    @FXML
    private ProviderSub providerSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){

    }

    @Override
    public void initValues(SearchContainer searchContainer, Provider provider){
        previousSearch = searchContainer;
        providerSubController.initValues(provider);
    }


    public void handleConfirmation(ActionEvent actionEvent) {

    }
}
