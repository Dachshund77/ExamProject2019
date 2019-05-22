package Application.Controller;

import Domain.Education;
import Domain.Provider;
import Foundation.DB;
import Persistance.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NewEducationController {

    @FXML
    public TextArea descriptionTextArea;
    @FXML
    public TextField educationNameTextfield;
    @FXML
    public MenuButton providerMenuButton;
    @FXML
    public MenuButton noOfDaysMenuButton;

    private Provider selectedProvider = null;
    private Integer selectedNoOfDays = null;

    public void initialize() {
        //Populate providers
        DB database = DB.getInstance();
        try {
            database.connect();
            populateProviderMenuButton();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                database.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //populate Days menu button
        populateNoOfDaysMenuButton();
    }

    @FXML
    public void handleConfirmForm(ActionEvent event) { /// FIXME: 22/05/2019 need implementation
        //Gather needed values
        Provider provider = selectedProvider;
        String educationName = educationNameTextfield.getText();
        String description = descriptionTextArea.getText();
        Integer noOfDays = selectedNoOfDays;

        //Write object to Database
        //Education newEduction = new Education(null,educationName,description,noOfDays,,provider)
    }

    private void populateProviderMenuButton() throws SQLException {
        //Get values
        HashMap<Integer, Provider> providers = DbFacade.findAllProviders();
        //We want to sort its easier with array
        Collection<Provider> values = providers.values();
        ArrayList<Provider> providerArrayList = new ArrayList<Provider>(values);
        // Adding menuItem
        for (Provider provider : providerArrayList) {
            MenuItem newMenuItem = new MenuItem(provider.getProviderName());
            newMenuItem.setOnAction(e -> {
                selectedProvider = provider;
                providerMenuButton.setText(provider.getProviderName());
            });
            providerMenuButton.getItems().add(newMenuItem);
        }
    }

    private void populateNoOfDaysMenuButton() {
        //How many maximum days
        int max = 15;
        // Init needed values
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        //Build the list
        for (int i = 0; i < max; i++) {
            integerArrayList.add(i + 1);
        }
        //Add to the MenuButton
        for (Integer integer : integerArrayList) {
            MenuItem newMenuItem = new MenuItem(integer.toString());
            newMenuItem.setOnAction(e -> {
                selectedNoOfDays = integer;
                noOfDaysMenuButton.setText(integer.toString());
            });
            noOfDaysMenuButton.getItems().add(newMenuItem);
        }
    }

}
