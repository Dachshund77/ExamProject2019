package Application.Controller;

import Application.AbstractController;
import Application.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

import javax.swing.text.View;

/**
 * TaskBar Controller that can be used as a subController across the whole application.
 */
public class TaskBarController extends AbstractController {

    @FXML
    public MenuBar taskBar; //Need this to get scene

    /**
     * Changes the Scene to {@link NewEducationController}.
     * @param event Clicked menuButton new -> Education
     */
    public void HandleNewEducation(ActionEvent event) {
        ViewController.NEW_EDUCATION_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleNewInterview(ActionEvent event) {
        ViewController.NEW_INTERVIEW_CONTROLLER.reLoad(taskBar.getScene());
    }

    /**
     * Changes the Scene to {@link NewEmployeeController}.
     * @param event Clicked menuButton new -> Employee
     */
    public void handleNewEmployee(ActionEvent event) {
        ViewController.NEW_EMPLOYEE_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleNewConsultation(ActionEvent event) {
        ViewController.NEW_COONSULTATION_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleNewCompany(ActionEvent event) {
        ViewController.NEW_COMPANY_CONTROLLER.reLoad(taskBar.getScene());
    }

    /**
     * Changes the Scene to {@link NewProviderController}.
     * @param event Clicked menuButton new -> Provider
     */
    public void handleNewProvider(ActionEvent event) {
        ViewController.NEW_PROVIDER_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleChangeEducation(ActionEvent event) {
        ViewController.CHANGE_EDUCATION_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleChangeInterview(ActionEvent event) {
        ViewController.CHANGE_INTERVIEW_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleChangeEmployee(ActionEvent event) {
        ViewController.CHANGE_EMP_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleChangeConsultation(ActionEvent event) {
        ViewController.CHANGE_CONSULTATION_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleChangeCompany(ActionEvent event) {
        ViewController.CHANGE_COMPANY_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleDeleteEducation(ActionEvent event) {
    }

    public void handleDeleteInterview(ActionEvent event) {
        ViewController.DEL_INTERVIEW_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleDeleteEmployee(ActionEvent event) {
        ViewController.DEL_EMPLOYEE_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleDeleteConsultation(ActionEvent event) {
        ViewController.DEL_COUN_CONTROLLER.reLoad(taskBar.getScene());
    }

    public void handleDeleteCompany(ActionEvent event) {
        ViewController.DEL_COMP_CONTROLLER.reLoad(taskBar.getScene());
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
        ViewController.FIND_COMPANY_CONTROLLER.reLoad(taskBar.getScene());
    }
}
