package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.Company;
import Domain.Consultation;
import Domain.Employee;
import Domain.Interview;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class FindInterviewSub extends AbstractController {
    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<Interview> interviewTableView;
    @FXML
    private TableColumn<Interview, Integer> interviewIDColumn;
    @FXML
    private TableColumn<Interview, String> interviewNameColumn;

    private ObservableList<Company> searchResultList;
    private ObservableList<Interview> displayData;

    public void initialize() {
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController needed references
        searchResultList = findSubController.getSearchResultList();

        //Register listener
        searchResultList.addListener((ListChangeListener<? super Company>) observable -> formatDisplayData());

        //Setup TableView
        interviewTableView.setItems(displayData);

        interviewIDColumn.setCellValueFactory(new PropertyValueFactory<>("interviewID"));
        interviewNameColumn.setCellValueFactory(new PropertyValueFactory<>("interviewName"));

        interviewTableView.getColumns().setAll(interviewIDColumn, interviewNameColumn);

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
     * Formats the output from the search query into Interview objects that only occur once.
     *
     * @see Interview
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
                    ArrayList<Interview> tempInterviews = employee.getInterviews();
                    for (Interview tempInterview : tempInterviews) {
                        if (!displayData.contains(tempInterview)) {
                            displayData.add(tempInterview);
                        }
                    }
                }
            }
        }
    }
}
