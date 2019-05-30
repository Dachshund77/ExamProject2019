package Application.Controller.Find.FindToChange;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindEducationSub;
import Application.SearchContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FindEducationToChange extends AbstractController {

    @FXML
    private FindEducationSub findEducationSubController;
    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct

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
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        //goto next
    }
}
