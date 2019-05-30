package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class FindCompanySub extends AbstractController {

    @FXML
    public FindSub findSubController;
    public TableView companyTableView;
    public TableColumn companyIDColumn;
    public TableColumn cvrNrColumn;
    public TableColumn companyNameColumn;
    public Button searchButton;

    public void handleSearch(ActionEvent event) {
    }
}
