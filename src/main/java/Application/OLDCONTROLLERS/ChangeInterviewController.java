package Application.OLDCONTROLLERS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class ChangeInterviewController {

    @FXML
    ComboBox companyDrop;
    @FXML
    ComboBox consultationDrop;
    @FXML
    ComboBox interviewDrop;
    @FXML
    TableView employeeTable;
    @FXML
    TextField nameField;
    int b = 5;
    String bParse = Integer.toString(b);

    public void handleGetText(ActionEvent actionEvent) {
    }

    /**
     * When the user has selected a company from the first combobox
     * all consultations from that specific company
     * should fill the consultation combobox
     * @param actionEvent
     */
    public void handleSetConsultation(ActionEvent actionEvent) {
    }

    /**
     * When the user has selected a consultation from the second combobox
     * all interviews should be shown from that consultation
     * in the third combobox
     * @param actionEvent
     */
    public void handleSetInterview(ActionEvent actionEvent) {
    }

    /**
     * When an interview has been selected
     * a tableview should be shown with employee name, ID, and rating(Maybe)
     * @param actionEvent
     */
    public void handleSetTable(ActionEvent actionEvent) {
    }
}
