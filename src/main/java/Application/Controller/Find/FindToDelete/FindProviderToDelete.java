package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindEducationSub;
import Application.Controller.SubControllers.Find.FindProviderSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Education;
import Domain.Provider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import javax.swing.text.View;

public class FindProviderToDelete extends AbstractController {


    @FXML
    private FindProviderSub findProviderSubController;

    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    /**
     * disables the confirm button
     * until the user has selected a company
     */
    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(findProviderSubController.getProviderTableView().getSelectionModel().selectedItemProperty().isNull());
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
    @FXML
    private void handleConfirmation(ActionEvent event) {
        Provider toBeDeletedProvider = findProviderSubController.getProviderTableView().getSelectionModel().getSelectedItem();
        SearchContainer currentSearch = findProviderSubController.getFindSubController().getCurrentSearchContainer();

        confirmationButton.getScene().setRoot(ViewController.DELETE_PROVIDER.loadParent(currentSearch, toBeDeletedProvider));
    }
}