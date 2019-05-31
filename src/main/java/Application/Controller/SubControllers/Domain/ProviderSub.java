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
        providerNameTooltip.getProperties();
        providerNameTooltip.setText("tester1231231");

        providerNameTextField.setTooltip(providerNameTooltip);


        providerNameTextField.setOnKeyPressed(event ->

                updateIsValid());

    }

    public void updateIsValid(){
        // Manages the isValid property aka when all values are valid = true
        String test = providerNameTextField.getText();
        if (Provider.isValidProviderName(test)){
            providerNameTextField.getStyle();
            providerNameTextField.setStyle("-fx-border-color: green");

        }
        else
            providerNameTextField.setStyle("-fx-background-color: red");

    }

    public void setEditable(boolean bool){

    }
    public void resetForm(){
        //Reset fields, set field if it has a selected Domain object
    }

}
