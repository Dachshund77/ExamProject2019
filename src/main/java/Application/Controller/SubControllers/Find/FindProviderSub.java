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

public class FindProviderSub extends AbstractController {
    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<Provider> providerTableView;
    @FXML
    private TableColumn<Provider, Integer> providerIDColumn;
    @FXML
    private TableColumn<Provider, String> providerNameColumn;

    private ObservableList<Company> searchResultList;
    private ObservableList<Provider> displayData;

    public void initialize() {
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController needed references
        searchResultList = findSubController.getSearchResultList();

        //Register listener
        searchResultList.addListener((ListChangeListener<? super Company>) observable -> formatDisplayData());

        //Setup TableView
        providerTableView.setItems(displayData);

        providerIDColumn.setCellValueFactory(new PropertyValueFactory<>("providerID"));
        providerNameColumn.setCellValueFactory(new PropertyValueFactory<>("providerName"));


        providerTableView.getColumns().setAll(providerIDColumn, providerNameColumn);

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
     * Formats the output from the search query into Provider objects that only occur once.
     *
     * @see Provider
     */
    private void formatDisplayData() {
        //Cleans out previous data
        displayData.clear();
        //
        for (Company company : searchResultList) {
            ArrayList<Education> educationList = company.getEducationList();
            for (Education education : educationList) {
                if (education != null) {
                    Provider tempProvider = education.getProvider();
                    if (!displayData.contains(tempProvider)) {
                        displayData.add(tempProvider);
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
                                Provider tempProvider = tempEducation.getProvider();
                                if (!displayData.contains(tempProvider)) {
                                    displayData.add(tempProvider);
                                }
                            }
                            ArrayList<EducationWish> tempEducationWishes = interview.getEducationWishes();
                            for (EducationWish tempEducationWish : tempEducationWishes) {
                                Education tempEducation = tempEducationWish.getEducation();
                                if (tempEducation != null) {
                                    Provider tempProvider = tempEducation.getProvider();
                                    if (!displayData.contains(tempProvider)) {
                                        displayData.add(tempProvider);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
