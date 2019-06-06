package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EmployeeSub;
import Application.SearchContainer;
import Domain.Education;
import Domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteEmployee extends AbstractController {
    @FXML
    private EmployeeSub employeeSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){

    }

    @Override
    public void initValues(SearchContainer searchContainer, Employee employee){
        previousSearch = searchContainer;
        employeeSubController.initValues(employee);
    }


    public void handleConfirmation(ActionEvent actionEvent) {

    }
}
