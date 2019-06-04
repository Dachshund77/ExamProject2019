package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class FindEmployeeSub extends AbstractController {
    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<Employee> employeeTableView;
    @FXML
    private TableColumn<Employee, Integer> employeeIDColumn;
    @FXML
    private TableColumn<Employee, String> employeeCprColumn;
    @FXML
    private TableColumn<Employee, String> employeeFirstNameColumn;
    @FXML
    private TableColumn<Employee, String> employeeLastNameColumn;
    @FXML
    private TableColumn<Employee, String> employeeEmailColumn;
    @FXML
    private TableColumn<Employee, String> employeePhoneNrColumn;

    private ObservableList<Company> searchResultList;
    private ObservableList<Employee> displayData;

    public void initialize() {
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController needed references
        searchResultList = findSubController.getSearchResultList();

        //Register listener
        searchResultList.addListener((ListChangeListener<? super Company>) observable -> formatDisplayData());

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

    /**
     * Formats the output from the search query into Employee objects that only occur once.
     *
     * @see Employee
     */
    private void formatDisplayData() {
        //Cleans out previous data
        displayData.clear();
        //
        for (Company company : searchResultList) {
            ArrayList<Consultation> tempConsultations = company.getConsultations();
            for (Consultation consultation : tempConsultations) {
                ArrayList<Employee> tempEmployees = consultation.getEmployees();
                for (Employee employee : tempEmployees) {
                    if (!displayData.contains(employee)) {
                        displayData.add(employee);
                    }
                }
            }
        }
    }
}



