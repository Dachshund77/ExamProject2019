package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindEmployeeSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FindEmployeeToDelete extends AbstractController {

    @FXML
    private FindEmployeeSub findEmployeeSubController;

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
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        //goto next
    }
}
