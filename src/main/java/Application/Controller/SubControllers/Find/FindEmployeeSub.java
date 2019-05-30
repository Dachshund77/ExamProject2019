package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FindEmployeeSub extends AbstractController {
    @FXML
    public FindSub findSubController;
    public TableView educationTableView;
    public TableColumn employeeIDColumn;
    public TableColumn employeeCprColumn;
    public TableColumn employeeFirstNameColumn;
    public TableColumn employeeLastNameColumn;
    public TableColumn employeeEmailColumn;
    public TableColumn employeePhoneNrColumn;
}
