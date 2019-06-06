package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.InterviewSub;
import Application.SearchContainer;
import Domain.Interview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteInterview extends AbstractController {
    @FXML
    private InterviewSub interviewSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){

    }

    @Override
    public void initValues(SearchContainer searchContainer, Interview interview){
        previousSearch = searchContainer;
        interviewSubController.initValues(interview);
    }


    public void handleConfirmation(ActionEvent actionEvent) {

    }
}
