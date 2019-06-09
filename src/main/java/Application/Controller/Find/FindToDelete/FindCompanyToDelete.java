package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindCompanySub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayCompany;
import Domain.DomainObjects.Company;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class FindCompanyToDelete extends AbstractController {


    @FXML
    private FindCompanySub findCompanySubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<DisplayCompany> companyTableView;
    /**
     * disables the confirm button
     * until the user has selected a company
     */
    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        companyTableView = findCompanySubController.getCompanyTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(companyTableView.getSelectionModel().selectedItemProperty().isNull());
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
    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {

        //Init values
        Company toBeDeletedCompany = null;

        //Get selection
        DisplayCompany selectedCompany = companyTableView.getSelectionModel().getSelectedItem();
        int id = selectedCompany.getCompanyID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeDeletedCompany = DbFacade.findCompanyByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        SearchContainer currentSearch = findCompanySubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.DELETE_COMPANY.loadParent(currentSearch, toBeDeletedCompany));
    }
}
