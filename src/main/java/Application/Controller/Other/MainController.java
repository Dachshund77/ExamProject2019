package Application.Controller.Other;

import Application.Controller.AbstractController;
import Application.Controller.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainController extends AbstractController {
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private void handleNewCompany(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.ALTER_COMPANY.loadParent());
    }

    @FXML
    private void handleNewConsultation(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.ALTER_CONSULTATION.loadParent());
    }

    @FXML
    private void handleNewEmployee(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.ALTER_EMPLOYEE.loadParent());
    }

    @FXML
    private void handleNewInterview(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.ALTER_INTERVIEW.loadParent());
    }

    @FXML
    private void handleNewEducation(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.ALTER_EDUCATION.loadParent());
    }

    @FXML
    private void handleNewProvider(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.ALTER_PROVIDER.loadParent());
    }

    @FXML
    private void handleChangeCompany(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_COMPANY_TO_CHANGE.loadParent());
    }

    @FXML
    private void handleChangeConsultation(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_CONSULTATION_TO_CHANGE.loadParent());
    }

    @FXML
    private void handleChangeEmployee(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_EMPLOYEE_TO_CHANGE.loadParent());
    }

    @FXML
    private void handleChangeInterview(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_INTERVIEW_TO_CHANGE.loadParent());
    }

    @FXML
    private void handleChangeEducation(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_EDUCATION_TO_CHANGE.loadParent());
    }

    @FXML
    private void handleChangeProvider(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_PROVIDER_TO_CHANGE.loadParent());
    }

    @FXML
    private void handleFindCompany(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_COMPANY_TO_RECORD.loadParent());
    }

    @FXML
    private void handleFindConsultation(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_CONSULTATION_TO_RECORD.loadParent());
    }

    @FXML
    private void handleFindEmployee(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_EMPLOYEE_TO_RECORD.loadParent());
    }

    @FXML
    private void handleFindInterview(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_INTERVIEW_TO_RECORD.loadParent());
    }

    @FXML
    private void handleFindEducation(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_EDUCATION_TO_RECORD.loadParent());
    }

    @FXML
    private void handleFindProvider(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_PROVIDER_TO_RECORD.loadParent());
    }

    @FXML
    private void handleDeleteCompany(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_COMPANY_TO_DELETE.loadParent());
    }

    @FXML
    private void handleDeleteConsultation(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_CONSULTATION_TO_DELETE.loadParent());
    }

    @FXML
    private void handleDeleteEmployee(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_EMPLOYEE_TO_DELETE.loadParent());
    }

    @FXML
    private void handleDeleteInterview(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_INTERVIEW_TO_DELETE.loadParent());
    }

    @FXML
    private void handleDeleteEducation(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_EDUCATION_TO_DELETE.loadParent());
    }

    @FXML
    private void handleDeleteProvider(ActionEvent event) {
        mainBorderPane.setCenter(ViewController.FIND_PROVIDER_TO_DELETE.loadParent());
    }
}
