package Application.Controller.Find.FindToChange;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindProviderSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Provider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class FindProviderToChange extends AbstractController {

    @FXML
    private FindProviderSub findProviderSubController;

    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private TableView<Provider> providerTableView;


    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        providerTableView = findProviderSubController.getProviderTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(providerTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * {@inheritDoc}
     * <br/><br/>
     * Calling this initValues will ultimately invoke {@link Application.Controller.SubControllers.Find.FindSub#initValues(SearchContainer) FindSub} reset.
     * This will set the search form to the values of the given SearchContainer.
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findProviderSubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen
        Parent root = confirmationButton.getScene().getRoot();
        root.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Get selection
        Provider toBeChangedProvider = providerTableView.getSelectionModel().getSelectedItem();

        //Get the search container
        SearchContainer currentSearch = findProviderSubController.getFindSubController().getCurrentSearchContainer();

        //Goto Change Company
        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.ALTER_PROVIDER.loadParent(currentSearch, toBeChangedProvider));

    }
}
