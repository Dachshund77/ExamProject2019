package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Company;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

//TODO NEEDS JAVADOC -Sven
public class AlterCompany extends AbstractController {

    @FXML
    private CompanySub companySubController;

    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;


    private SearchContainer previousSearch;

    /**
     * disables the "Confirm" button
     * until the required TextFields are valid
     */
    @FXML
    private void initialize() {
        confirmationButton.disableProperty().bind(companySubController.isValid.not());
        companySubController.resetForm();
    }

    @Override
    public void initValues(SearchContainer searchContainer, Company company) {
        //Save search container for returning
        previousSearch = searchContainer;
        companySubController.initValues(company);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){
            Parent root = cancelButton.getScene().getRoot();
            ((BorderPane) root).setCenter(ViewController.FIND_COMPANY_TO_CHANGE.loadParent(previousSearch));
        } else {
            cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
        }
    }

    /**
     * Updates the database with a new company
     *
     * @param event on user click, create a new company obj
     *              with content from the textfields,
     *              which is then send to the database
     */
    @FXML //FIXME sends 2 objects to the company
    private void handleConfirmation(ActionEvent event) {
        Company company = companySubController.getCompany();

        //Send confirmation
        if (company.getCompanyID() == null){
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Company was added to the Database Successfully!");
            info.showAndWait();
        }else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Success!");
            info.setHeaderText(null);
            info.setContentText("Company was updated in the Database Successfully!");
            info.showAndWait();
        }

        try {
            DbFacade.connect();
            DbFacade.insertCompany(company);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        confirmationButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    /**
     * Resets the TextFields
     * if the user isnt satisfied with
     * what is entered
     *
     * @param event on user click,
     *              resets all TextFields
     */
    @FXML
    private void handleReset(ActionEvent event) {
        companySubController.resetForm();
    }

}
