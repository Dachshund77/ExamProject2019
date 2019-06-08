package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindCompanySub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FindCompanyToDelete extends AbstractController {


    @FXML
    private FindCompanySub findCompanySubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct
    @FXML
    private Button cancelButton;

    @FXML
    private void initialize(){
        // hook up the  button with subcontroller form correctness
        confirmationButton.disableProperty().bind(findCompanySubController.getCompanyTableView().getSelectionModel().selectedItemProperty().isNull());

    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findCompanySubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        //goto next
    }
}
