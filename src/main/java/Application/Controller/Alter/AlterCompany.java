package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Company;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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
        //propergate Consultation to setup form
        //TODO THERE IS MISSING STUFF HERE -Sven
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
        if (previousSearch != null){
            /*
            Company returnedCompany = findConsultationSubController.getConsultationTableView().getSelectionModel().getSelectedItem();
            SearchContainer currentSearch = findConsultationSubController.getFindSubController().getCurrentSearchContainer();
            */

            Parent root = cancelButton.getScene().getRoot();
            ((BorderPane) root).setCenter(ViewController.ALTER_COMPANY.loadParent(previousSearch));
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

        System.out.println(companySubController.isValid.get());
        Company createNewCompanyObj = new Company(null, companySubController.cvrNrTextField.getText(),
                companySubController.companyNameTextField.getText(), null, null);
            //TODO rewirte the subcontroller to private, sorry
        try {
            DbFacade.connect();
            DbFacade.insertCompany(createNewCompanyObj);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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
