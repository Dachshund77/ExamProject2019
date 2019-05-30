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

    public Text providerIDText;
    public TextField providerNameTextField;
    public Tooltip providerNameTooltip;

    public SimpleBooleanProperty isValid; // Hook for parent class to activate confirm button
    public Provider selectedprovider;

    public void initialize(){
        //setup isValid
        //hookup bindings
    }

    @Override
    public void initValues(Provider provider) {
        // hook up provider
    }

    public void handleProviderNameInput(KeyEvent keyEvent){
        // whenever the user input something that textfield
    }

    public void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
    }

    public void setEditable(boolean bool){

    }
    public void resetForm(){
        //Reset fields, set field if it has a selected Domain object
    }

}
