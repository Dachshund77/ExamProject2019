package Application.Controller;

import Domain.Company;
import Foundation.DB;
import Persistance.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class ChangeEmpController {

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

    private Company companyName = null;

    public void initialize() {
        //Populate providers
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

    private void populateCompany() throws SQLException {
        HashSet<Company> companyNames = DbFacade.findAllCompanies();
        ArrayList<Company> companyArrayList = new ArrayList<>(companyNames);

        for (Company companies : companyArrayList) {
            compDrop.getItems().addAll(companies);
        }


    }


    public void handleText(ActionEvent actionEvent) {
    }
}
