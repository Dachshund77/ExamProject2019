package Application.Controller.Find.FindToRecord;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindProviderSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayProvider;
import Domain.DomainObjects.Provider;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class FindProviderToRecord extends AbstractController {
    @FXML
    private FindProviderSub findProviderSubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct
    @FXML
    private Button cancelButton;

    private TableView<DisplayProvider> providerTableView;
    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        providerTableView = findProviderSubController.getProviderTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(providerTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findProviderSubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Init values
        Provider toBeShownProvider = null;

        //Get selection
        DisplayProvider selectedProvider = providerTableView.getSelectionModel().getSelectedItem();
        int id = selectedProvider.getProviderID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeShownProvider = DbFacade.findProviderByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }


        SearchContainer currentSearch = findProviderSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.RECORD_PROVIDER.loadParent(currentSearch, toBeShownProvider));
    }
}
