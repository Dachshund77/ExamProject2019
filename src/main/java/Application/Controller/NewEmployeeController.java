package Application.Controller;

import Application.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewEmployeeController extends AbstractController {

    @FXML
    public TextField firstNameTextfield;
    @FXML
    public TextField lastNameTextfield;
    @FXML
    public TextField CprTextField;
    @FXML
    public TextField EmailTextField;
    @FXML
    public TextField phoneNrTextfield;

    @FXML
    public void handleConfirmForm(ActionEvent event) {
        //Validate so that "" become no
    }
}
