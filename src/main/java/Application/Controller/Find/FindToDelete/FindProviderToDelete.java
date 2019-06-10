package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindEducationSub;
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

import javax.swing.text.View;
import java.sql.SQLException;

public class FindProviderToDelete extends AbstractController {


    @FXML
    private FindProviderSub findProviderSubController;

    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<DisplayProvider> providerTableView;

    /**
     * disables the confirm button
     * until the user has selected a company
     */
    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        providerTableView = findProviderSubController.getProviderTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(providerTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * initializes the findProviderSubController
     * to have values from the searchContainer
     * @param searchContainer
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findProviderSubController.initValues(searchContainer);
    }
    /**
     * If the user clicks the "cancel" button
     * the user will return to the main scene
     * @param event
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    /**
     * when the user has selected a provider
     * and pressed the "confirm" button
     * the user will be presented with a new stage
     * where it is possible to see the following
     * Provider ID
     * Provider name
     * @param event
     */
    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Init values
        Provider toBeDeletedProvider = null;

        //Get selection
        DisplayProvider selectedProvider = providerTableView.getSelectionModel().getSelectedItem();
        int id = selectedProvider.getProviderID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeDeletedProvider = DbFacade.findProviderByID(id);
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
        ((BorderPane) root).setCenter(ViewController.DELETE_PROVIDER.loadParent(currentSearch, toBeDeletedProvider));
    }
}