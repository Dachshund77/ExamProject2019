package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.ConsultationReturnableController;
import Application.Controller.SubControllers.Find.FindConsultationSub;
import Application.Controller.ViewController;
import Domain.DisplayObjects.DisplayConsultation;
import Domain.DomainObjects.Consultation;
import Foundation.DbFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FindConsultationPopUp extends ConsultationReturnableController {

    @FXML
    private FindConsultationSub findConsultationSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<DisplayConsultation> consultationTableView;
    private Consultation selectedConsultation;


    public void initialize() {
        //Init fields
        selectedConsultation = null;

        // Load the TableView reference from subController
        consultationTableView = findConsultationSubController.getConsultationTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(consultationTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void handleConfirmation(ActionEvent actionEvent) {
        //Get selection
        DisplayConsultation selectedItem = consultationTableView.getSelectionModel().getSelectedItem();
        int id = selectedItem.getConsultationID();

        //Fetch real from Database
        try{
            DbFacade.connect();
            selectedConsultation = DbFacade.findConsultationByID(id);
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
        selectedConsultation = null;
        stage.close();
    }

    @Override
    public String getURL() {
        return ViewController.FIND_CONSULTATION_POPUP.getURL();
    }

    /**
     * @return Loads the FindConsultationPopUp Stage
     */
    @Override
    public Consultation getReturn() {
        return selectedConsultation;
    }
}
