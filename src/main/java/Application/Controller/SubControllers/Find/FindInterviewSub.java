package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayInterview;
import Domain.DisplayObjects.DisplayProvider;
import Domain.DomainObjects.Company;
import Domain.DomainObjects.Consultation;
import Domain.DomainObjects.Employee;
import Domain.DomainObjects.Interview;
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

public class FindInterviewSub extends AbstractController {
    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<DisplayInterview> interviewTableView;
    @FXML
    private TableColumn<DisplayInterview, Integer> interviewIDColumn;
    @FXML
    private TableColumn<DisplayInterview, String> interviewNameColumn;

    private ObservableList<DisplayInterview> displayData;

    public void initialize() {
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController and register event handler
        Button searchButton = findSubController.getSearchButton();
        searchButton.setOnAction(event -> handleSearch());

        Button resetButton = findSubController.getResetButton();
        resetButton.setOnAction(event -> displayData.clear());

        //Setup TableView
        interviewTableView.setItems(displayData);

        interviewIDColumn.setCellValueFactory(new PropertyValueFactory<>("interviewID"));
        interviewNameColumn.setCellValueFactory(new PropertyValueFactory<>("interviewName"));

        interviewTableView.getColumns().setAll(interviewIDColumn, interviewNameColumn);

    }

    private void handleSearch(){
        try {
            DbFacade.connect();
            SearchContainer container = findSubController.getCurrentSearchContainer();
            displayData.addAll(DbFacade.findDisplayInterviews(container));

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
        handleSearch();
    }


    public TableView<DisplayInterview> getInterviewTableView() {
        return interviewTableView;
    }

    public FindSub getFindSubController() {
        return findSubController;
    }
}
