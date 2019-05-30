package Application.Controller;

import Application.SearchContainer;
import Domain.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Can be used to change and load the scenes easily.
 * The enum class will store the relative path to the fxml so that we don't need to type it multiple times.
 */
public enum ViewController {
    // Other
    MAIN_CONTROLLER("/FXML/Controller/Other/MainController.fxml"),

    //Alter
    ALTER_COMPANY("/FXML/Controller/Alter/AlterCompany.fxml"),
    ALTER_CONSULTATION("/FXML/Controller/Alter/AlterConsultation.fxml"),
    ALTER_EDUCATION("/FXML/Controller/Alter/AlterEducation.fxml"),
    ALTER_EMPLOYEE("/FXML/Controller/Alter/AlterEmployee.fxml"),
    ALTER_INTERVIEW("/FXML/Controller/Alter/AlterInterview.fxml"),
    ALTER_PROVIDER("/FXML/Controller/Alter/AlterProvider.fxml"),

    //Find to Change
    FIND_COMPANY_TO_CHANGE("/FXML/Controller/Find/FindToChange/FindCompanyToChange.fxml"),
    FIND_CONSULTATION_TO_CHANGE("/FXML/Controller/Find/FindToChange/FindConsultationToChange.fxml"),
    FIND_EDUCATION_TO_CHANGE("/FXML/Controller/Find/FindToChange/FindEducationToChange.fxml"),
    FIND_EMPLOYEE_TO_CHANGE("/FXML/Controller/Find/FindToChange/FindEmployeeToChange.fxml"),
    FIND_INTERVIEW_TO_CHANGE("/FXML/Controller/Find/FindToChange/FindInterviewToChange.fxml"),
    FIND_PROVIDER_TO_CHANGE("/FXML/Controller/Find/FindToChange/FindProviderToChange.fxml"),

    //Find to delete
    FIND_COMPANY_TO_DELETE("/FXML/Controller/Find/FindToDelete/FindCompanyToDelete.fxml"),
    FIND_CONSULTATION_TO_DELETE("/FXML/Controller/Find/FindToDelete/FindConsultationToDelete.fxml"),
    FIND_EDUCATION_TO_DELETE("/FXML/Controller/Find/FindToDelete/FindEducationToDelete.fxml"),
    FIND_EMPLOYEE_TO_DELETE("/FXML/Controller/Find/FindToDelete/FindEmployeeToDelete.fxml"),
    FIND_INTERVIEW_TO_DELETE("/FXML/Controller/Find/FindToDelete/FindInterviewToDelete.fxml"),
    FIND_PROVIDER_TO_DELETE("/FXML/Controller/Find/FindToDelete/FindProviderToDelete.fxml"),

    //Find to Record
    FIND_COMPANY_TO_RECORD("/FXML/Controller/Find/FindToRecord/FindCompanyToRecord.fxml"),
    FIND_CONSULTATION_TO_RECORD("/FXML/Controller/Find/FindToRecord/FindConsultationToRecord.fxml"),
    FIND_EDUCATION_TO_RECORD("/FXML/Controller/Find/FindToRecord/FindEducationToRecord.fxml"),
    FIND_EMPLOYEE_TO_RECORD("/FXML/Controller/Find/FindToRecord/FindEmployeeToRecord.fxml"),
    FIND_INTERVIEW_TO_RECORD("/FXML/Controller/Find/FindToRecord/FindInterviewToRecord.fxml"),
    FIND_PROVIDER_TO_RECORD("/FXML/Controller/Find/FindToRecord/FindProviderToRecord.fxml");



    private final String URL;

    ViewController(String url) {
        this.URL = url;
    }

    /**
     * Returns the root node for the Application.NEWSTUFF.OLDCONTROLLERS.AbstractController.
     * This method should be used when no previous scene is loaded.
     * @return Parent of Application.NEWSTUFF.OLDCONTROLLERS.AbstractController
     */
    public Parent loadParent(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(URL));
        } catch (IOException e){
            e.printStackTrace();
        }
        return root;
    }

    public void loadParent(String string) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(string);
    }

    public void loadParent(Provider provider) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(provider);
    }

    public void loadParent(Education education) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(education);
    }

    public void loadParent(Employee employee) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(employee);
    }

    public void loadParent(Interview interview) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(interview);
    }

    public void loadParent(Consultation consultation) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(consultation);
    }

    public void loadParent(Company company) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(company);
    }

    public void loadParent(SearchContainer searchContainer) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer);
    }

    public void loadParent(SearchContainer searchContainer, Provider provider) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, provider);
    }

    public void loadParent(SearchContainer searchContainer, Education education) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, education);
    }

    public void loadParent(SearchContainer searchContainer, Employee employee) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, employee);
    }

    public void loadParent(SearchContainer searchContainer, Interview interview) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, interview);
    }

    public void loadParent(SearchContainer searchContainer, Consultation consultation) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, consultation);
    }

    public void loadParent(SearchContainer searchContainer, Company company) {
        loadParent();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, company);
    }

    /**
     * Loads a new scene.
     * The scene URL is defined in the Enum constructor.
     *
     * @param scene The scene that will be replaced on reLoad
     */
    public void reLoad(Scene scene) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(URL));
            Scene newScene = new Scene(root, scene.getWidth(), scene.getHeight());
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a new scene and passes a value to the new controller.
     * The scene URL is defined in the Enum constructor.
     * <br><br>
     * <p>
     * <font color="red">
     * IMPORTANT:
     * </font>
     * In order to pass a value the new controller needs to extends the 'Application.NEWSTUFF.OLDCONTROLLERS.AbstractController' abstract class and override its methods.
     * </p>
     *
     * @param scene  The scene that will be replaced on reLoad
     * @param string Value that will passed on to the controller
     */
            /*
            If another parameter needs to passed follow thees steps:
            1) Create a initValues( - your parameters -) in the Application.OLDCONTROLLERS interface
            2) Create the same initValues in the Abstract class Application.NEWSTUFF.OLDCONTROLLERS.AbstractController, and please keep the style of error messages
            3) Copy paste this method in here and change the parameter and newController.initValues( - your parameters -)
            4) Override the created method in the controller you want it to be used.
            */
    public void reLoad(Scene scene, String string) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(string);
    }

    public void reLoad(Scene scene, Provider provider) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(provider);
    }

    public void reLoad(Scene scene, Education education) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(education);
    }

    public void reLoad(Scene scene, Employee employee) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(employee);
    }

    public void reLoad(Scene scene, Interview interview) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(interview);
    }

    public void reLoad(Scene scene, Consultation consultation) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(consultation);
    }

    public void reLoad(Scene scene, Company company) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(company);
    }

    public void reLoad(Scene scene, SearchContainer searchContainer) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer);
    }

    public void reLoad(Scene scene, SearchContainer searchContainer, Provider provider) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, provider);
    }

    public void reLoad(Scene scene, SearchContainer searchContainer, Education education) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, education);
    }

    public void reLoad(Scene scene, SearchContainer searchContainer, Employee employee) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, employee);
    }

    public void reLoad(Scene scene, SearchContainer searchContainer, Interview interview) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, interview);
    }

    public void reLoad(Scene scene, SearchContainer searchContainer, Consultation consultation) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, consultation);
    }

    public void reLoad(Scene scene, SearchContainer searchContainer, Company company) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(searchContainer, company);
    }
}
