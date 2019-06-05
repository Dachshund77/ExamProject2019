package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.Company;
import Domain.Consultation;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class FindConsultationSub extends AbstractController {

    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<Consultation> consultationTableView;
    @FXML
    private TableColumn<Consultation, Integer> consultationIDColumn;
    @FXML
    private TableColumn<Consultation, String> consultationNameColumn;
    @FXML
    private TableColumn<Consultation, LocalDate> startDateColumn;
    @FXML
    private TableColumn<Consultation, LocalDate> endDateColumn;

    private ObservableList<Company> searchResultList;
    private ObservableList<Consultation> displayData;

    public void initialize() {
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController needed references
        searchResultList = findSubController.getSearchResultList();

        //Register listener
        searchResultList.addListener((ListChangeListener<? super Company>) observable -> formatDisplayData());

        //Setup TableView
        consultationTableView.setItems(displayData);

        consultationIDColumn.setCellValueFactory(new PropertyValueFactory<>("consultationID"));
        consultationNameColumn.setCellValueFactory(new PropertyValueFactory<>("consultationName"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        consultationTableView.getColumns().setAll(consultationIDColumn, consultationNameColumn, startDateColumn, endDateColumn);

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
     * Formats the output from the search query into Consultation objects that only occur once.
     *
     * @see Consultation
     */
    private void formatDisplayData() {
        //Cleans out previous data
        displayData.clear();
        //
        for (Company company : searchResultList) {
            ArrayList<Consultation> tempConsultations = company.getConsultations();
            for (Consultation consultation : tempConsultations) {
                if (!displayData.contains(consultation)) {
                    displayData.add(consultation);
                }
            }
        }
    }

    public TableView<Consultation> getConsultationTableView() {
        return consultationTableView;
    }

    public FindSub getFindSubController() {
        return findSubController;
    }
}
