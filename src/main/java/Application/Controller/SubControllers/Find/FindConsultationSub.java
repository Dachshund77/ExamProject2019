package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayConsultation;
import Domain.DomainObjects.Company;
import Domain.DomainObjects.Consultation;
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
import java.time.LocalDate;
import java.util.ArrayList;

public class FindConsultationSub extends AbstractController {

    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<DisplayConsultation> consultationTableView;
    @FXML
    private TableColumn<DisplayConsultation, Integer> consultationIDColumn;
    @FXML
    private TableColumn<DisplayConsultation, String> consultationNameColumn;
    @FXML
    private TableColumn<DisplayConsultation, LocalDate> startDateColumn;
    @FXML
    private TableColumn<DisplayConsultation, LocalDate> endDateColumn;

    private ObservableList<DisplayConsultation> displayData;

    public void initialize() {
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController and register event handler
        Button searchButton = findSubController.getSearchButton();
        searchButton.setOnAction(event -> handleSearch());

        Button resetButton = findSubController.getResetButton();
        resetButton.setOnAction(event -> displayData.clear());

        //Setup TableView
        consultationTableView.setItems(displayData);

        consultationIDColumn.setCellValueFactory(new PropertyValueFactory<>("consultationID"));
        consultationNameColumn.setCellValueFactory(new PropertyValueFactory<>("consultationName"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        consultationTableView.getColumns().setAll(consultationIDColumn, consultationNameColumn, startDateColumn, endDateColumn);

    }

    private void handleSearch(){
        try {
            DbFacade.connect();
            SearchContainer container = findSubController.getCurrentSearchContainer();
            displayData.addAll(DbFacade.findDisplayConsultations(container));

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



    public TableView<DisplayConsultation> getConsultationTableView() {
        return consultationTableView;
    }

    public FindSub getFindSubController() {
        return findSubController;
    }
}
