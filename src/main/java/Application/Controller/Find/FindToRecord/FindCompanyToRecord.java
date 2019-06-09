package Application.Controller.Find.FindToRecord;

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

public class FindCompanyToRecord extends AbstractController {

    @FXML
    private Button cancelButton;
    @FXML
    private FindCompanySub findCompanySubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private TableView<DisplayCompany> companyTableView;

    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        companyTableView = findCompanySubController.getCompanyTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(companyTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findCompanySubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());    }

    @SuppressWarnings("Duplicates")
    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Init values
        Company toBeShownCompany = null;

        //Get selection
        DisplayCompany selectedCompany = companyTableView.getSelectionModel().getSelectedItem();
        int id = selectedCompany.getCompanyID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            toBeShownCompany = DbFacade.findCompanyByID(id);
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
        ((BorderPane) root).setCenter(ViewController.RECORD_COMPANY.loadParent(currentSearch, toBeShownCompany));
    }
}
