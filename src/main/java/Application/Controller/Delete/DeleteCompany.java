package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.SearchContainer;
import Domain.Company;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteCompany extends AbstractController {

    @FXML
    private CompanySub companySubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){

    }

    @Override
    public void initValues(SearchContainer searchContainer, Company company){
        previousSearch = searchContainer;
        companySubController.initValues(company);
    }


    public void handleConfirmation(ActionEvent actionEvent) {

    }
}
