package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.Controller.SubControllers.Find.FindCompanySub;
import Application.Controller.SubControllers.Find.FindSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Company;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class FindCompanyToDelete extends AbstractController {


    @FXML
    private FindCompanySub findCompanySubController;
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
        confirmationButton.disableProperty().bind(findCompanySubController.getCompanyTableView().getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * initializes the findCompanySubController
     * to have values from the searchContainer
     * @param searchContainer
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findCompanySubController.initValues(searchContainer);
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
     * when the user has selected a company
     * and pressed the "confirm" button
     * the user will be presented with a new stage
     * where it is possible to see the following
     * Company ID
     * Company name
     * Company CVR No.
     * @param event
     */
    @FXML
    private void handleConfirmation(ActionEvent event) {

        Company toBeDeletedCompany = findCompanySubController.getCompanyTableView().getSelectionModel().getSelectedItem();

        SearchContainer currentSearch = findCompanySubController.getFindSubController().getCurrentSearchContainer();

        confirmationButton.getScene().setRoot(ViewController.DELETE_COMPANY.loadParent(currentSearch, toBeDeletedCompany));
    }




}
