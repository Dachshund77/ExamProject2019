package Application.Controller.Find.FindToDelete;

import Application.Controller.AbstractController;
import Application.Controller.SubControllers.Find.FindEducationSub;
import Application.Controller.ViewController;
import Application.SearchContainer;
import Domain.Education;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class FindEducationToDelete extends AbstractController {


    @FXML
    private FindEducationSub findEducationSubController;

    @FXML
    private Button confirmationButton; //Button needs to be disable when form is not correct
    @FXML
    private Button cancelButton;

    @FXML
    private void initialize(){
        // hook up the  button with subcontroller form correctness
        confirmationButton.disableProperty().bind(findEducationSubController.getEducationTableView().getSelectionModel().selectedItemProperty().isNull());
    }

    @Override
    public void initValues(SearchContainer searchContainer) {
        //Send to FindSub controller to fill out form and reset
        findEducationSubController.initValues(searchContainer);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        cancelButton.getScene().setRoot(ViewController.MAIN_CONTROLLER.loadParent());
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        //goto next
        Education toBeDeletedEducation = findEducationSubController.getEducationTableView().getSelectionModel().getSelectedItem();

        //Get the search container
        SearchContainer currentSearch = findEducationSubController.getFindSubController().getCurrentSearchContainer();

        Parent root = confirmationButton.getScene().getRoot();
        ((BorderPane) root).setCenter(ViewController.DELETE_COMPANY.loadParent(currentSearch, toBeDeletedEducation));

    }
}
