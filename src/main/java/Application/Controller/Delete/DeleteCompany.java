package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import Domain.DomainObjects.Company;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import javax.swing.text.View;
import java.sql.SQLException;

public class DeleteCompany extends AbstractController {

    @FXML
    private CompanySub companySubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    /**
     * Removes ability to edit the textfields as well
     * also removes the companyIDText
     *
     * sidenote: CVR number will show red, if the CVR is less than 8 digits
     */
    @FXML
    public void initialize(){
        companySubController.companyIDText.setVisible(false);
        companySubController.companyNameTextField.setEditable(false);
        companySubController.cvrNrTextField.setEditable(false);

    }

    /**
     * When the user has selected a company
     * the TextField in the scene will be populated
     * with the consultation name
     * @param searchContainer
     * @param company
     */
    @Override
    public void initValues(SearchContainer searchContainer, Company company){
        previousSearch = searchContainer;
        companySubController.initValues(company);
    }


    /**
     * When the user clicks "Confirm"
     * a new company object with all
     * the information is deleted from the database
     * @param actionEvent
     */
    public void handleConfirmation(ActionEvent actionEvent) {
        try {
            DbFacade.connect();
            DbFacade.deleteCompany(companySubController.selectedCompany.getCompanyID());
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
}
