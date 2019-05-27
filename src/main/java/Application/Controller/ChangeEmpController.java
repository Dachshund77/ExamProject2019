package Application.Controller;

import Domain.Company;
import Foundation.DB;
import Persistance.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;


public class ChangeEmpController {

    @FXML
    public MenuBar taskBar;

    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField cprNrField;
    @FXML
    TextField emailField;
    @FXML
    TextField phoneNrField;
    @FXML
    ChoiceBox compDrop;
    @FXML
    ChoiceBox consultDrop;
    @FXML
    ChoiceBox consultantDrop;
    @FXML
    Button confirmBtn;
    @FXML
    Button deleteBtn;
    @FXML
    ChoiceBox empDrop;

    private Company companyName = null;

    /**
     * Calling the database and asking for all company names
     * with the populateCompany() method
     */
    public void initialize() {
        //Populate company choice box
        DB database = DB.getInstance();
        try {
            database.connect();
            populateCompany();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                database.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    //stuck trying to fill the first choice box with company names
    //TODO tried a lot of things but, console says that fld_CompanyID is INVALID -MC
    //Sorry hat to comment that out -Sven
    private void populateCompany() throws SQLException {
        /*
        HashMap<Integer, Company> companyNames = DbFacade.FindAllCompanies();
        Collection<Company> names = companyNames.values();
        ArrayList<Company> namesArraylist = new ArrayList<>(names);

        for (Company companies: namesArraylist) {
            compDrop.getItems().addAll(companyNames);
        }
        */
    }


    public void handleText(ActionEvent actionEvent) {

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        int phoneNo = Integer.parseInt(phoneNrField.getText());
        int cprNo = Integer.parseInt(cprNrField.getText());


    }


    public void handleDiscard(ActionEvent actionEvent) {
    }
}
