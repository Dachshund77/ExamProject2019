package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.InterviewSub;
import Application.SearchContainer;
import Domain.Interview;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class DeleteInterview extends AbstractController {
    @FXML
    private InterviewSub interviewSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){

    }

    /**
     * When the user has selected an employee
     * the TextField in the scene will be populated
     * with the consultation name
     *
     * @param searchContainer
     * @param interview
     */
    @Override
    public void initValues(SearchContainer searchContainer, Interview interview){
        previousSearch = searchContainer;
        interviewSubController.initValues(interview);
    }

    /**
     * When the user clicks "Confirm"
     * a new interview object with all
     * the information is deleted from the database
     *
     * @param actionEvent
     */
    public void handleConfirmation(ActionEvent actionEvent) {
        Interview createToDeleteInterviewObj = new Interview(interviewSubController.selectedInterview.getInterviewID(),
                interviewSubController.selectedInterview.getInterviewName(),
                interviewSubController.selectedInterview.getProductUnderstanding(),
                interviewSubController.selectedInterview.getProblemUnderstanding(),
                interviewSubController.selectedInterview.getFlexibility(),
                interviewSubController.selectedInterview.getQualityAwareness(),
                interviewSubController.selectedInterview.getCooperation(),
                interviewSubController.selectedInterview.getFinishedEducations(),
                interviewSubController.selectedInterview.getEducationWishes());

        try {
            DbFacade.connect();
            DbFacade.deleteInterview(createToDeleteInterviewObj.getInterviewID());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
