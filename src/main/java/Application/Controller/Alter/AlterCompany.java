package Application.Controller.Alter;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.SearchContainer;
import Domain.Company;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class AlterCompany extends AbstractController {

    @FXML
    private CompanySub companySubController;

    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

    private SearchContainer searchContainer;

    /**
     * disables the "Confirm" button
     * until the required TextFields are valid
     */
    @FXML
    private void initialize(){
        confirmationButton.disableProperty().bind(companySubController.isValid.not());
    }

    @Override
    public void initValues(SearchContainer searchContainer, Company company) {
        //Save search container for returning
        //propergate Consultation to setup form
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen or search
        //if coming from search return to search with initValues
    }

    /**
     * Updates the database with a new company
     * @param event on user click, create a new company obj
     *              with content from the textfields,
     *              which is then send to the database
     */
    @FXML //FIXME sends 2 objects to the company
    private void handleConfirmation(ActionEvent event) {

        System.out.println(companySubController.isValid.get());
        Company createNewCompanyObj = new Company(null, companySubController.cvrNrTextField.getText(),
                companySubController.companyNameTextField.getText(), null, null);

        try {
            DbFacade.connect();
            DbFacade.insertCompany(createNewCompanyObj);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
     * @param event on user click,
     *              resets all TextFields
     */
    @FXML
    private void handleReset(ActionEvent event) {
        companySubController.resetForm();
    }

}
