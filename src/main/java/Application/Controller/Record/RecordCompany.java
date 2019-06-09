package Application.Controller.Record;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Company;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordCompany extends AbstractController {

    @FXML
    private CompanySub companySubController;
    @FXML
    private Button returnButton;

    private SearchContainer previousSearchContainer = null;

    public void initialize(){
        companySubController.setDisabled(true);
    }

    @Override
    public void initValues(SearchContainer searchContainer, Company company) {
        previousSearchContainer = searchContainer;
        companySubController.initValues(company);
    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_COMPANY_TO_RECORD.loadParent(previousSearchContainer));
    }
}
