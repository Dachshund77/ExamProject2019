package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.CompanySub;
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

    @Override
    public void initValues(SearchContainer searchContainer, Company company){
        previousSearch = searchContainer;

        //deleteCompanySubController.cvrNrTextField.setText(searchContainer.getCvrNr());
        //deleteCompanySubController.companyNameTextField.setText(searchContainer.getCompanyName());
        //deleteCompanySubController.companyIDText.setText(searchContainer.getCompanyIDasString());
        //System.out.println(searchContainer.getCompanyName());
        //System.out.println(searchContainer.getCompanyID());
        //System.out.println(searchContainer.getCvrNr());

    }

    public void handleCancel(ActionEvent actionEvent) {
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    public void handleConfirmation(ActionEvent actionEvent) {

    }
}
