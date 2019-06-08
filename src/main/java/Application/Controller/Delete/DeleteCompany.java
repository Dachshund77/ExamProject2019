package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.SearchContainer;
import Domain.DomainObjects.Company;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

public class DeleteCompany extends AbstractController {

    @FXML
    private CompanySub deleteCompanySubController;
    @FXML
    private Button confirmButton;
    @FXML
    private SearchContainer previousSearch;

    @FXML
    public void initialize(){

    }

    @Override
    public void initValues(SearchContainer searchContainer, Company company){
        previousSearch = searchContainer;
    }

    @FXML
    private void handleConfirmation(ActionEvent event){

    }
}
