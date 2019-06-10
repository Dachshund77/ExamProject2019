package Application.Controller.Record;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.InterviewSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.DomainObjects.Interview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class RecordInterview extends AbstractController {
    @FXML
    private InterviewSub interviewSubController;
    @FXML
    private Button returnButton;

    private SearchContainer previousSearchContainer = null;

    public void initialize(){
        interviewSubController.setDisabled(true);
    }

    @Override
    public void initValues(SearchContainer searchContainer, Interview interview) {
        previousSearchContainer = searchContainer;
        interviewSubController.initValues(interview);
    }

    public void handleReturn(ActionEvent event) {
        Parent root = returnButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.FIND_INTERVIEW_TO_RECORD.loadParent(previousSearchContainer));
    }
}
