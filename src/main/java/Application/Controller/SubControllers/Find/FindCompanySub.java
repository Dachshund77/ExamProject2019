package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.DisplayObjects.DisplayCompany;
import Domain.DomainObjects.Company;
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

public class FindCompanySub extends AbstractController {

    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<DisplayCompany> companyTableView;
    @FXML
    private TableColumn<DisplayCompany,Integer> companyIDColumn;
    @FXML
    private TableColumn<DisplayCompany,String> cvrNrColumn;
    @FXML
    private TableColumn<DisplayCompany,String> companyNameColumn;


    private ObservableList<DisplayCompany> displayData;

    public void initialize(){
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController and register event handler
        Button searchButton = findSubController.getSearchButton();
        searchButton.setOnAction(event -> handleSearch());

        //Setup TableView
        companyTableView.setItems(displayData);

        companyIDColumn.setCellValueFactory(new PropertyValueFactory<>("companyID"));
        cvrNrColumn.setCellValueFactory(new PropertyValueFactory<>("cvrNr"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        companyTableView.getColumns().setAll(companyIDColumn,cvrNrColumn,companyNameColumn);

    }

    private void handleSearch(){
        try {
            DbFacade.connect();
            SearchContainer container = findSubController.getCurrentSearchContainer();
            displayData.addAll(DbFacade.findDisplayCompanies(container));

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



    public TableView<DisplayCompany> getCompanyTableView() {
        return companyTableView;
    }

    public FindSub getFindSubController() {
        return findSubController;
    }
}
