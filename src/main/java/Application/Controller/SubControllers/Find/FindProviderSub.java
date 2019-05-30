package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FindProviderSub extends AbstractController {
    @FXML
    public FindSub findSubController;
    public TableView providerTableView;
    public TableColumn providerIDColumn;
    public TableColumn providerNameColumn;
}
