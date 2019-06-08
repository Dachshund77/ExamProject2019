package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.Provider;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;

public class ProviderSub extends AbstractController {

    public Text providerIDText;
    public TextField providerNameTextfield;
    public Tooltip providerNameTooltip;

    public BooleanBinding isValid;
    public Provider selectedProvider;
    private SimpleBooleanProperty providerNameIsValid = new SimpleBooleanProperty(true);

    public void initialize() {

        /*
         adds a listener to the handler for providerNameTextfield
         */
        providerNameTextfield.textProperty().addListener((observable -> handleProviderNameInput()));

        /*
        binds the .isValid() to the controls
        and returns true or false depending
        if the requirements are met
         */
        isValid = new BooleanBinding() {

            @Override
            protected boolean computeValue() {
                bind(providerNameIsValid);
                if (providerNameIsValid.get()) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    /**
     * initializes the provider domain
     * and primes the resetform
     *
     * @param provider
     */
    @Override
    public void initValues(Provider provider) {
        selectedProvider = provider;
        resetForm();
    }

    /**
     * On every keypress the providerNameTextField
     * is updated to check if the content is valid
     * if not, the TextField will be red, and show
     * a tooltip with the cause for errors
     */
    public void handleProviderNameInput() {
        if (Provider.isValidProviderName(providerNameTextfield.getText())) {
            providerNameTextfield.setTooltip(null);
            providerNameIsValid.set(true);
            providerNameTextfield.getStyleClass().removeAll("TextField-Error");
        } else {
            String invalidCause = Provider.providerNameInvalidCause(providerNameTextfield.getText());
            providerNameTextfield.setTooltip(new Tooltip(invalidCause));
            providerNameIsValid.set(false);
            if (!providerNameTextfield.getStyleClass().contains("TextField-Error")) {
                providerNameTextfield.getStyleClass().add("TextField-Error");
            }
        }
    }


    /**
     * Checks if the provider Textfield
     * has content, if so, it enables the confirm button
     *
     * @param bool
     */
    public void setDisabled(boolean bool) {
        providerNameTextfield.setDisable(bool);
    }

    /**
     * when the user clicks "reset"
     * all TextFields are cleared
     */
    public void resetForm() {
        if (selectedProvider != null) {
            providerNameTextfield.setText(selectedProvider.getProviderName());
        } else {
            providerNameTextfield.setText("");
        }
    }

}
