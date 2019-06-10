package Application.Controller.Record;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.ConsultationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Consultation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordConsultation extends AbstractController {
    @FXML
    private ConsultationSub consultationSubController;
    @FXML
    private Button returnButton;


    private SearchContainer previousSearchContainer = null;

    public void initialize(){
        consultationSubController.setDisabled(true);
    }

    @Override
    public void initValues(SearchContainer searchContainer, Consultation consultation) {
        previousSearchContainer = searchContainer;
        consultationSubController.initValues(consultation);
    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_CONSULTATION_TO_RECORD.loadParent(previousSearchContainer));
    }
}
