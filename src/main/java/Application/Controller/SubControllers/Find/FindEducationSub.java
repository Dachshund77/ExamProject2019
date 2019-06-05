package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class FindEducationSub extends AbstractController {
    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<Education> educationTableView;
    @FXML
    private TableColumn<Education, Integer> educationIDColumn;
    @FXML
    private TableColumn<Education, String> educationDaysColumn;
    @FXML
    private TableColumn<Education, String> educationNameColumn;
    @FXML
    private TableColumn<Education, String> providerNameColumn;

    private ObservableList<Company> searchResultList;
    private ObservableList<Education> displayData;

    public void initialize() {
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController needed references
        searchResultList = findSubController.getSearchResultList();

        //Register listener
        searchResultList.addListener((ListChangeListener<? super Company>) observable -> formatDisplayData());

        //Setup TableView
        educationTableView.setItems(displayData);

        educationIDColumn.setCellValueFactory(new PropertyValueFactory<>("amuNr"));
        educationDaysColumn.setCellValueFactory(new PropertyValueFactory<>("noOfDays"));
        educationNameColumn.setCellValueFactory(new PropertyValueFactory<>("educationName"));
        providerNameColumn.setCellValueFactory(Education -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            if (Education == null || Education.getValue() == null || Education.getValue().getProvider() == null || Education.getValue().getProvider().getProviderName()== null){
                return null;
            }
            property.setValue(Education.getValue().getProvider().getProviderName());
            return property;
        });

        educationTableView.getColumns().setAll(educationIDColumn, educationDaysColumn, educationNameColumn,providerNameColumn);

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
     * Formats the output from the search query into Education objects that only occur once.
     *
     * @see Education
     */
    private void formatDisplayData() {
        //Cleans out previous data
        displayData.clear();
        //
        for (Company company : searchResultList) {
            ArrayList<Education> educationList = company.getEducationList();
            for (Education education : educationList) {
                if (!displayData.contains(education)) {
                    displayData.add(education);
                }
            }
            ArrayList<Consultation> tempConsultations = company.getConsultations();
            for (Consultation consultation : tempConsultations) {
                ArrayList<Employee> tempEmployees = consultation.getEmployees();
                for (Employee employee : tempEmployees) {
                    ArrayList<Interview> tempInterview = employee.getInterviews();
                    for (Interview interview : tempInterview) {
                        ArrayList<FinishedEducation> tempFinEducations = interview.getFinishedEducations();
                        for (FinishedEducation tempFinEducation : tempFinEducations) {
                            Education tempEducation = tempFinEducation.getEducation();
                            if (!displayData.contains(tempEducation)) {
                                displayData.add(tempEducation);
                            }
                        }
                        ArrayList<EducationWish> tempEducationWishes = interview.getEducationWishes();
                        for (EducationWish tempEducationWish : tempEducationWishes) {
                            Education tempEducation = tempEducationWish.getEducation();
                            if (!displayData.contains(tempEducation)) {
                                displayData.add(tempEducation);
                            }
                        }
                    }
                }
            }
        }
    }

    public TableView<Education> getEducationTableView() {
        return educationTableView;
    }

    public FindSub getFindSubController() {
        return findSubController;
    }
}
