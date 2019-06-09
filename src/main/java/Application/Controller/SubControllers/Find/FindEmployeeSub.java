package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayEmployee;
import Domain.DomainObjects.Company;
import Domain.DomainObjects.Consultation;
import Domain.DomainObjects.Employee;
import Foundation.DbFacade;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class FindEmployeeSub extends AbstractController {
    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<DisplayEmployee> employeeTableView;
    @FXML
    private TableColumn<DisplayEmployee, Integer> employeeIDColumn;
    @FXML
    private TableColumn<DisplayEmployee, String> employeeCprColumn;
    @FXML
    private TableColumn<DisplayEmployee, String> employeeFirstNameColumn;
    @FXML
    private TableColumn<DisplayEmployee, String> employeeLastNameColumn;
    @FXML
    private TableColumn<DisplayEmployee, String> employeeEmailColumn;
    @FXML
    private TableColumn<DisplayEmployee, String> employeePhoneNrColumn;

    private ObservableList<DisplayEmployee> displayData;

    public void initialize() {
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController and register event handler
        Button searchButton = findSubController.getSearchButton();
        searchButton.setOnAction(event -> handleSearch());

        //Setup TableView
        employeeTableView.setItems(displayData);

        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        employeeCprColumn.setCellValueFactory(new PropertyValueFactory<>("cprNr"));
        employeeFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeFirstName"));
        employeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
        employeeEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        employeePhoneNrColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));


        employeeTableView.getColumns().setAll(employeeIDColumn, employeeCprColumn, employeeFirstNameColumn, employeeLastNameColumn, employeeEmailColumn, employeePhoneNrColumn);

    }

    private void handleSearch(){
        try {
            DbFacade.connect();
            SearchContainer container = findSubController.getCurrentSearchContainer();
            displayData.addAll(DbFacade.findDisplayEmployees(container));

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     * <br/><br/>
     * This implementation propagates the searchContainer to {@link Application.Controller.SubControllers.Find.FindSub#initValues(SearchContainer)}
     */
    @Override
    public void initValues(SearchContainer searchContainer) {
        //Propagate the call further to the subController
        findSubController.initValues(searchContainer);
    }



    public TableView<DisplayEmployee> getEmployeeTableView() {
        return employeeTableView;
    }

    public FindSub getFindSubController() {
        return findSubController;
    }
}



