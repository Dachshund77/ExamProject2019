package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayProvider;
import Domain.DomainObjects.*;
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

public class FindProviderSub extends AbstractController {
    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<DisplayProvider> providerTableView;
    @FXML
    private TableColumn<DisplayProvider, Integer> providerIDColumn;
    @FXML
    private TableColumn<DisplayProvider, String> providerNameColumn;

    private ObservableList<DisplayProvider> displayData;

    public void initialize() {
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController and register event handler
        Button searchButton = findSubController.getSearchButton();
        searchButton.setOnAction(event -> handleSearch());

        Button resetButton = findSubController.getResetButton();
        resetButton.setOnAction(event -> displayData.clear());

        //Setup TableView
        providerTableView.setItems(displayData);

        providerIDColumn.setCellValueFactory(new PropertyValueFactory<>("providerID"));
        providerNameColumn.setCellValueFactory(new PropertyValueFactory<>("providerName"));


        providerTableView.getColumns().setAll(providerIDColumn, providerNameColumn);

    }

    private void handleSearch(){
        try {
            DbFacade.connect();
            SearchContainer container = findSubController.getCurrentSearchContainer();
            displayData.addAll(DbFacade.findDisplayProvider(container));

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



    public TableView<DisplayProvider> getProviderTableView() {
        return providerTableView;
    }

    public FindSub getFindSubController() {
        return findSubController;
    }
}
