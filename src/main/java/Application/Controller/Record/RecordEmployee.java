package Application.Controller.Record;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EmployeeSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordEmployee extends AbstractController {
    @FXML
    private EmployeeSub employeeSubController;
    @FXML
    private Button returnButton;

    private SearchContainer previousSearchContainer = null;

    public void initialize(){
        employeeSubController.setDisabled(true);
    }

    @Override
    public void initValues(SearchContainer searchContainer, Employee employee) {
        previousSearchContainer = searchContainer;
        employeeSubController.initValues(employee);
    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_EMPLOYEE_TO_RECORD.loadParent(previousSearchContainer));
    }
}
