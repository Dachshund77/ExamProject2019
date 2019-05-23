package Application.Controller;

import Domain.Education;
import Domain.Provider;
import Foundation.DB;
import Persistance.DbFacade;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    @FXML
    public DatePicker datePicker;
    @FXML
    public TableView<Date> datesTable;
    @FXML
    public TableColumn<Date,Date> datesColumn; //2 skal være bindable property


    private Provider selectedProvider = null;
    private Integer selectedNoOfDays = 1;

    private Education selectedEducation;

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

        //Create empty Education container
        selectedEducation = new Education(null,null,null,null,null,null);
        selectedEducation.educationNameProperty().bind(educationNameTextfield.textProperty());
        selectedEducation.descriptionProperty().bind(descriptionTextArea.textProperty());
        selectedEducation.noOfDaysProperty().bind(new SimpleIntegerProperty(selectedNoOfDays));
        selectedEducation.providerProperty().bind(new SimpleObjectProperty<>(selectedProvider));

        //set Table
        datesTable.setItems(FXCollections.observableArrayList(selectedEducation.getDates()));

        //Set columns
        datesColumn.setCellValueFactory(new PropertyValueFactory<>("dates")); //TODO SO DAMN STUCK


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
        ArrayList<Provider> providerArrayList = new ArrayList<Provider>(values); /// FIXME: 22/05/2019 need to implement comperator to make this work
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

    @SuppressWarnings("MagicConstant")
    @FXML
    public void handleAddDate(ActionEvent event) {
        LocalDate datePicked = datePicker.getValue();
        Date date = Date.from(datePicked.atStartOfDay(ZoneId.systemDefault()).toInstant());

        selectedEducation.getDates().add(date);
        datesTable.getItems().add(date);
        System.out.println(selectedEducation.getDates()); //TODO remove later


    }
}
