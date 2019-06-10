package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.EducationReturnableController;
import Application.Controller.SubControllers.Find.FindEducationSub;
import Application.Controller.ViewController;
import Domain.DisplayObjects.DisplayEducation;
import Domain.DomainObjects.Education;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FindEducationPopUp extends EducationReturnableController {
    @FXML
    private FindEducationSub findEducationSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<DisplayEducation> educationTableView;
    private Education selectedEducation;


    public void initialize() {
        //Init fields
        selectedEducation = null;

        // Load the TableView reference from subController
        educationTableView = findEducationSubController.getEducationTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(educationTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void handleConfirmation(ActionEvent actionEvent) {
        //Get selection
        DisplayEducation selectedItem = educationTableView.getSelectionModel().getSelectedItem();
        int id = selectedItem.getAmuNr();

        //Fetch real from Database
        try{
            DbFacade.connect();
            selectedEducation = DbFacade.findEducationByID(id);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) confirmationButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        selectedEducation = null;
        stage.close();
    }

    @Override
    public String getURL() {
        return ViewController.FIND_EDUCATION_POPUP.getURL();
    }

    /**
     * @return Loads the FindEducationPopUp Stage
     */
    @Override
    public Education getReturn() {
        return selectedEducation;
    }
}
