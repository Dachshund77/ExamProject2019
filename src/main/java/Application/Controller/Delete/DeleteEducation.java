package Application.Controller.Delete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Domain.EducationSub;
import Application.SearchContainer;
import Domain.Education;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class DeleteEducation extends AbstractController {
    @FXML
    private EducationSub educationSubController;
    @FXML
    private Button confirmationButton;

    private SearchContainer previousSearch;

    @FXML
    public void initialize(){

    }

    /**
     * When the user has selected an education
     * the TextField in the scene will be populated
     * with the consultation name
     * @param searchContainer
     * @param education
     */
    @Override
    public void initValues(SearchContainer searchContainer, Education education){
        previousSearch = searchContainer;
        educationSubController.initValues(education);
    }

    /**
     * When the user clicks "Confirm"
     * a new education object with all
     * the information is deleted from the database
     * @param actionEvent
     */
    public void handleConfirmation(ActionEvent actionEvent) {
        Education createToDeleteEducationObj = new Education(educationSubController.selectedEducation.getAmuNr(),
                educationSubController.selectedEducation.getEducationName(), educationSubController.selectedEducation.getDescription(),
                educationSubController.selectedEducation.getNoOfDays(), educationSubController.selectedEducation.getDates(),
                educationSubController.selectedEducation.getProvider());

        try {
            DbFacade.connect();
            DbFacade.deleteEducation(createToDeleteEducationObj.getAmuNr());
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
