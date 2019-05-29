package Application.Controller;

import Application.NEWSTUFF.Controller.AbstractController;
import Domain.Employee;
import Foundation.DB;
import Persistance.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.SQLException;

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
            String employeeFirstName = firstNameTextfield.getText();
            String employeeLastName = lastNameTextfield.getText();
            String cprNr = CprTextField.getText();
            String eMail = EmailTextField.getText();
            String phoneNr = phoneNrTextfield.getText();

            boolean CPRIsCorrect = true;
            //Checks if the CPR Nr field has been filled out and is only with numbers, else set text field to
            if (!cprNr.matches("[0-9]+") || cprNr.length() != 10) {
                CPRIsCorrect = false;

                //Displays a dialog box to inform about a wrong input
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wrong CPR nr");
                alert.setHeaderText(null);
                alert.setContentText("Please input the correct CPR nr");

                alert.showAndWait();
            }

            //If fields contain no text, the data is replaced with a null
            if (employeeFirstName.equals(""))
                employeeFirstName = null;

            if (employeeLastName.equals(""))
                employeeLastName = null;

            if (eMail.equals(""))
                eMail = null;

            if (phoneNr.equals(""))
                phoneNr = null;

            //Connecting to the DB and executing sp_InsertEmployee
            if (CPRIsCorrect) {
                DB database = DB.getInstance();
                try {
                    database.connect();
                    DbFacade.insertEmployee(new Employee(null, employeeFirstName, employeeLastName, cprNr, eMail, phoneNr,null));
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
        }
}
