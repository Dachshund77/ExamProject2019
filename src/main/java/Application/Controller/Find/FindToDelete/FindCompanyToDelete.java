package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindCompanySub;
import Application.Controller.SubControllers.Find.FindSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

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

    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        //Return to main screen
        Parent root = cancelButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        //goto next
    }
}
