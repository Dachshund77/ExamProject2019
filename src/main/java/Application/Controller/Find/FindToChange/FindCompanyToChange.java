package Application.Controller.Find.FindToChange;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindCompanySub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Company;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class FindCompanyToChange extends AbstractController {

    @FXML
    private FindCompanySub findCompanySubController;
    @FXML
    private Button confirmationButton;

    private TableView<Company> companyTableView;


    @FXML
    private void initialize(){
        // Load the TableView reference from subController
        companyTableView = findCompanySubController.getCompanyTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(companyTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * {@inheritDoc}
     * <br/><br/>
     * Calling this initValues will ultimately invoke {@link Application.Controller.SubControllers.Find.FindSub#initValues(SearchContainer) FindSub} reset.
     * This will set the search form to the values of the given SearchContainer.
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findCompanySubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen
        Parent root = confirmationButton.getScene().getRoot();
        root.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        //Get selection
        Company toBeChangedCompany = companyTableView.getSelectionModel().getSelectedItem();

        //Get the search container
        SearchContainer currentSearch = findCompanySubController.getFindSubController().getCurrentSearchContainer();

        //Goto Change Company
        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.ALTER_COMPANY.loadParent(currentSearch, toBeChangedCompany));

    }
}
