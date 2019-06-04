package Application.Controller.Find.FindToChange;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ConsultationSub;
import Application.Controller.SubControllers.Find.FindCompanySub;
import Application.SearchContainer;
import Domain.Consultation;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

public class FindCompanyToChange extends AbstractController {

    @FXML
    private FindCompanySub findCompanySubController;
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
