package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayEducation;
import Domain.DomainObjects.*;
import Foundation.DbFacade;
import javafx.beans.property.SimpleObjectProperty;
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

public class FindEducationSub extends AbstractController {
    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<DisplayEducation> educationTableView;
    @FXML
    private TableColumn<DisplayEducation, Integer> educationIDColumn;
    @FXML
    private TableColumn<DisplayEducation, String> educationDaysColumn;
    @FXML
    private TableColumn<DisplayEducation, String> educationNameColumn;
    @FXML
    private TableColumn<DisplayEducation, String> providerNameColumn;


    private ObservableList<DisplayEducation> displayData;

    public void initialize() {
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController and register event handler
        Button searchButton = findSubController.getSearchButton();
        searchButton.setOnAction(event -> handleSearch());

        //Setup TableView
        educationTableView.setItems(displayData);

        educationIDColumn.setCellValueFactory(new PropertyValueFactory<>("amuNr"));
        educationDaysColumn.setCellValueFactory(new PropertyValueFactory<>("noOfDays"));
        educationNameColumn.setCellValueFactory(new PropertyValueFactory<>("educationName"));
        providerNameColumn.setCellValueFactory(new PropertyValueFactory<>("providerName") );

        educationTableView.getColumns().setAll(educationIDColumn, educationDaysColumn, educationNameColumn,providerNameColumn);

    }

    private void handleSearch(){
        try {
            DbFacade.connect();
            SearchContainer container = findSubController.getCurrentSearchContainer();
            displayData.addAll(DbFacade.findDisplayEducations(container));

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

    public TableView<DisplayEducation> getEducationTableView() {
        return educationTableView;
    }

    public FindSub getFindSubController() {
        return findSubController;
    }
}
