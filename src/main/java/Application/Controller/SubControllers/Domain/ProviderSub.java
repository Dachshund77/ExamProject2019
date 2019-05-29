package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.Provider;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class ProviderSub extends AbstractController {

    @FXML
    public Text providerIDText;
    @FXML
    public TextField providerNameTextField;
    @FXML
    public Tooltip providerNameTooltip;

    public SimpleBooleanProperty isEditiable; //Hook to make the form unchangeable
    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button


    public void initialize(){
        //setup isValid
        //hookup bindings
    }

    @FXML
    public void handleProviderNameInput(KeyEvent keyEvent){
        // whenever the user input something that textfield
    }

    private void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
    }


}
