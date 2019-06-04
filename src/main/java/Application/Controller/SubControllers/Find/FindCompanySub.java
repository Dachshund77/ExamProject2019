package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.Company;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FindCompanySub extends AbstractController {

    @FXML
    private FindSub findSubController;
    @FXML
    private TableView<Company> companyTableView;
    @FXML
    private TableColumn<Company,Integer> companyIDColumn;
    @FXML
    private TableColumn<Company,String> cvrNrColumn;
    @FXML
    private TableColumn<Company,String> companyNameColumn;

    private ObservableList<Company> searchResultList;
    private ObservableList<Company> displayData;

    public void initialize(){
        //Init values
        displayData = FXCollections.observableArrayList();

        //Fetch the subController needed references
        searchResultList = findSubController.getSearchResultList();

        //Register listener
        searchResultList.addListener((ListChangeListener<? super Company>) observable -> formatDisplayData());

        //Setup TableView
        companyTableView.setItems(displayData);

        companyIDColumn.setCellValueFactory(new PropertyValueFactory<>("companyID"));
        cvrNrColumn.setCellValueFactory(new PropertyValueFactory<>("cvrNr"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        companyTableView.getColumns().setAll(companyIDColumn,cvrNrColumn,companyNameColumn);

    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        //Propagate the call further to the subController
        findSubController.initValues(searchContainer);
    }

    private void formatDisplayData(){
        //Cleans out previous data
        displayData.clear();
        //
        for (Company company : searchResultList) {
            if (!displayData.contains(company)){
                displayData.add(company);
            }
        }
    }
}
