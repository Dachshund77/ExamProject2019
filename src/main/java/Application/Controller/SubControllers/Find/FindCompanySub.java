package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.DomainObjects.Company;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
     * Formats the output from the search query into Company objects that only occur once.
     * @see Company
     */
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

    public TableView<Company> getCompanyTableView() {
        return companyTableView;
    }

    public FindSub getFindSubController() {
        return findSubController;
    }
}
