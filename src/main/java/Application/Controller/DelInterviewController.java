package Application.Controller;

import Domain.Interview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DelInterviewController {

    @FXML
    TableView interviewTable;
    @FXML
    ComboBox compDrop;
    @FXML
    ComboBox consultationDrop;
    @FXML
    Button confirmBtn;
    @FXML
    Button deleteBtn;

    /**
     * Initialize the company combobox with company names
     * When a company has been selected, it will send all consultations
     * to the next combobox
     */
    public void initialize(){

    }

    /**
     * Initializing columns to have the correct column names to the corresponding data
     */
    /*
    TableColumn<Integer, Interview> interviewIDColumn = new TableColumn<>("ID");
    interviewIDColumn.setCellValueFactory(new propertyValueFactory<>("ID"));
    interviewTable.getItems().add();

    TableColumn<String, Interview> interviewName = new TableColumn<>("Name");
    interviewName.setCellValueFactory(New propertyValueFactory<>("Name"));
    interviewName.getItems().add();

    TableColumn<Integer, Interview> interviewEmpID = new TableColumns<>("EMPID");
    interviewEmoID.setCellValueFactory(new propertyValueFactory<>("Employee ID"));
    interivewEmpID.getItems().add();

    */

    /**
     * When a consultation has been selected from the combobox
     * interviews from the database should populate the tableview
     * with information about the different interview
     * @param actionEvent
     */
    public void handleConsultation(ActionEvent actionEvent) {
    }

    public void handleDeleteInterview(ActionEvent actionEvent) {
    }

    public void handleConfirm(ActionEvent actionEvent) {
    }

    public void handleSelected(ActionEvent actionEvent) {
    }


}
