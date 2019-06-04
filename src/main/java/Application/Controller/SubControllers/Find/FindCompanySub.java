package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import Application.SearchContainer;
import Domain.Company;
import Domain.Consultation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class FindCompanySub extends AbstractController {

    @FXML
    public FindSub findSubController;
    public TableView<Company> companyTableView;
    public TableColumn<Company,Integer> companyIDColumn;
    public TableColumn<Company,String> cvrNrColumn;
    public TableColumn<Company,String> companyNameColumn;

    public void initialize(){
        ObservableList<Company> list = FXCollections.observableArrayList(findSubController.searchResult);
        companyTableView.setItems(list);

        companyIDColumn.setCellValueFactory(new PropertyValueFactory<>("companyID"));
        cvrNrColumn.setCellValueFactory(new PropertyValueFactory<>("cvrNr"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        companyTableView.getColumns().setAll(companyIDColumn,cvrNrColumn,companyNameColumn);

        list.add(new Company(2,"12345678","SvenInc",null,null));
        for (Company company : list) {
            company.getConsultations();
        }
        ObservableSet<Company> set = FXCollections.observableSet();


    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        //Propagate the call further to the subController
        findSubController.initValues(searchContainer);
    }
}
