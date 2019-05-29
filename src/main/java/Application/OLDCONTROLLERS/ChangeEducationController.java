package Application.OLDCONTROLLERS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChangeEducationController {

    @FXML
    TextField providerField;
    @FXML
    TextField AMUField;
    @FXML
    TextField nameField;
    @FXML
    TextArea educationDescription;
    @FXML
    Button searchBtn;
    @FXML
    Button confirmBtn;

    /**
     * This handler will get the text/numbers from the TextFields
     * and update the database with new values
     * @param actionEvent
     */
    public void handleGetText(ActionEvent actionEvent) {

        String providerName = providerField.getText();
        String newName = nameField.getText();



    }

    public void handleAMUSearch(ActionEvent actionEvent) {

        //TODO after a AMU search the textarea should be filled with the last description, easier to edit that way

        int amuSearch = Integer.parseInt(AMUField.getText());

    }
}
