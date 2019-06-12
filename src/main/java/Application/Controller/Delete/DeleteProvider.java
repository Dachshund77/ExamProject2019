package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ProviderSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Provider;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;
import java.util.Optional;

public class DeleteProvider extends AbstractController {

    @FXML
    private ProviderSub providerSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button returnButton;

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletion Warning");
        alert.setHeaderText("You are about to delete a Provider!");
        alert.setContentText("This Action will delete this Provider, all related Educations and Data that is related to those Educations.");
        Optional<ButtonType> result = alert.showAndWait();

        boolean outcome = false;
        if (result.isPresent() && result.get() == ButtonType.OK){
            try {
                DbFacade.connect();
                outcome = DbFacade.deleteProvider(providerSubController.selectedProvider.getProviderID());

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    DbFacade.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (outcome) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Success!");
                info.setHeaderText(null);
                info.setContentText("Provider was deleted from the Database Successfully!");
                info.showAndWait();
            } else {
                Alert info = new Alert(Alert.AlertType.ERROR);
                info.setTitle("ERROR!");
                info.setHeaderText(null);
                info.setContentText("Encountered critical database error!");
                info.showAndWait();
            }

            confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
        }

    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_PROVIDER_TO_DELETE.loadParent(previousSearch));
    }
}
