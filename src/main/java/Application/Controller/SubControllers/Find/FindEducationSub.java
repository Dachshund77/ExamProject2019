package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FindEducationSub extends AbstractController {
    @FXML
    public FindSub findSubController;
    public TableView educationTableView;
    public TableColumn educationIDColumn;
    public TableColumn educationCprColumn;
    public TableColumn educationFirstNameColumn;
}
