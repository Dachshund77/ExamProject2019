package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.SearchContainer;
import Domain.Company;
import Domain.Consultation;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class DeleteCompany extends AbstractController {

    @FXML
    private CompanySub companySubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){

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

        Company createToDeleteCompanyObj = new Company(companySubController.selectedCompany.getCompanyID(),
                companySubController.selectedCompany.getCvrNr(),
                companySubController.selectedCompany.getCompanyName(), companySubController.selectedCompany.getConsultations(),
                companySubController.selectedCompany.getEducationList());
        try {
            DbFacade.connect();
            DbFacade.deleteCompany(createToDeleteCompanyObj.getCompanyID());
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
}
