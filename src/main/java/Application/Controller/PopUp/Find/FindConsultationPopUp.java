package Application.Controller.PopUp.Find;

import Application.Controller.PopUp.ConsultationReturnableController;
import Application.Controller.SubControllers.Find.FindConsultationSub;
import Application.Controller.ViewController;
import Domain.DomainObjects.Consultation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class FindConsultationPopUp extends ConsultationReturnableController {

    @FXML
    private FindConsultationSub findConsultationSubController;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private TableView<Consultation> consultationTableView;
    private Consultation selectedConsultation;


    public void initialize() {
        //Init fields
        selectedConsultation = null;

        // Load the TableView reference from subController
        consultationTableView = findConsultationSubController.getConsultationTableView();

        // hook up the confirmation button
        confirmationButton.disableProperty().bind(consultationTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    public void handleConfirmation(ActionEvent actionEvent) {
        Stage stage = (Stage) confirmationButton.getScene().getWindow();
        selectedConsultation = consultationTableView.getSelectionModel().getSelectedItem(); //Confirmation can only be activated if something is selected
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
