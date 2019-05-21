package Application.Controller;

import Application.AbstractController;
import Application.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

/**
 * TaskBar Controller that can be used as a subController across the whole application.
 */
public class TaskBarController extends AbstractController {

    @FXML
    public MenuBar taskBar; //Need this to get scene

    public void HandleNewEducation(ActionEvent event) {
    }

    public void handleNewInterview(ActionEvent event) {
    }

    /**
     * Changes the Scene to {@link NewEmpController}.
     * @param event Clicked menuButton new -> Provider
     */
    public void handleNewEmployee(ActionEvent event) {
        ViewController.NEW_EMPLOYEE_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleNewConsultation(ActionEvent event) {
    }

    public void handleNewCompany(ActionEvent event) {
    }

    /**
     * Changes the Scene to {@link NewProviderController}.
     * @param event Clicked menuButton new -> Provider
     */
    public void handleNewProvider(ActionEvent event) {
        ViewController.NEW_PROVIDER_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleChangeEducation(ActionEvent event) {
    }

    public void handleChangeInterview(ActionEvent event) {
    }

    public void handleChangeEmployee(ActionEvent event) {
    }

    public void handleChangeConsultation(ActionEvent event) {
    }

    public void handleChangeCompany(ActionEvent event) {
    }

    public void handleDeleteEducation(ActionEvent event) {
    }

    public void handleDeleteInterview(ActionEvent event) {
    }

    public void handleDeleteEmployee(ActionEvent event) {
    }

    public void handleDeleteConsultation(ActionEvent event) {
    }

    public void handleDeleteCompany(ActionEvent event) {
    }

    public void handleFindEducation(ActionEvent event) {
    }

    public void handleFindInterview(ActionEvent event) {
    }

    public void handleFindEmployee(ActionEvent event) {
    }

    public void handleFindConsultation(ActionEvent event) {
    }

    public void handleFindCompany(ActionEvent event) {
    }
}
