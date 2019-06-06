package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.Find.FindToDelete.FindCompanyToDelete;
import Application.Controller.SubControllers.Domain.CompanySub;
import Application.Controller.SubControllers.Find.FindCompanySub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Company;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteCompany extends AbstractController {

    @FXML
    private CompanySub deleteCompanySubController;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private SearchContainer previousSearch;

    @FXML
    public void initialize(){

    }

    //TODO set searchContainer results into correspondingt textFields
    @Override
    public void initValues(SearchContainer searchContainer, Company company){
        previousSearch = searchContainer;
        deleteCompanySubController.initValues(searchContainer);
    }

    public void handleCancel(ActionEvent actionEvent) {
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    public void handleConfirmation(ActionEvent actionEvent) {

    }
}
