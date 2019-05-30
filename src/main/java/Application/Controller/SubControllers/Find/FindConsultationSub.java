package Application.Controller.SubControllers.Find;

import Application.Controller.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FindConsultationSub extends AbstractController {
    @FXML
    public FindSub findSubController;
    public TableView consultationTableView;
    public TableColumn consultationIDColumn;
    public TableColumn consultationNameColumn;
}
