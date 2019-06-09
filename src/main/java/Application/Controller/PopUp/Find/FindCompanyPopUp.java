package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.CompanyReturnableController;
import Application.Controller.SubControllers.Find.FindCompanySub;
import Application.Controller.ViewController;
import Domain.DisplayObjects.DisplayCompany;
import Domain.DomainObjects.Company;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FindCompanyPopUp extends CompanyReturnableController {

    @FXML
    private FindCompanySub findCompanySubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<DisplayCompany> companyTableView;
    private Company selectedCompany;


    public void initialize() {
        //Init fields
        selectedCompany = null;

        // Load the TableView reference from subController
        companyTableView = findCompanySubController.getCompanyTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(companyTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    @SuppressWarnings("Duplicates")
    public void handleConfirmation(ActionEvent actionEvent) {
        //Get selection
        DisplayCompany selectedItem = companyTableView.getSelectionModel().getSelectedItem();
        int id = selectedItem.getCompanyID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            selectedCompany = DbFacade.findCompanyByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) confirmationButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        selectedCompany = null;
        stage.close();
    }

    /**
     * @return Loads the FindCompanyPopUp Stage
     */
    @Override
    public String getURL() {
        return ViewController.FIND_COMPANY_POPUP.getURL();
    }

    @Override
    public Company getReturn() {
        return selectedCompany;
    }
}
