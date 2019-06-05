package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.CompanyReturnableController;
import Application.Controller.SubControllers.Find.FindCompanySub;
import Application.Controller.ViewController;
import Domain.Company;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FindCompanyPopUp extends CompanyReturnableController {

    @FXML
    private FindCompanySub findCompanySubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<Company> companyTableView;
    private Company selectedCompany;


    public void initialize() {
        //Init fields
        selectedCompany = null;

        // Load the TableView reference from subController
        companyTableView = findCompanySubController.getCompanyTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(companyTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    public void handleConfirmation(ActionEvent actionEvent) {
        Stage stage = (Stage) confirmationButton.getScene().getWindow();
        System.out.println("Setting the SelectedCompany " + companyTableView.getSelectionModel().getSelectedItem());
        selectedCompany = companyTableView.getSelectionModel().getSelectedItem(); //Confirmation can only be activated if something is selected
        stage.hide();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        selectedCompany = null;
        stage.fireEvent(new WindowEvent(stage,WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.hide();
    }

    @Override
    public Parent getParent() {
        return ViewController.FIND_COMPANY_POPUP.loadParent();
    }

    @Override
    public Company getReturn() {
        return selectedCompany;
    }
}
