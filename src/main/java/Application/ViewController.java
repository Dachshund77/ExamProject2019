package Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Can be used to change and load the scenes easily.
 * The enum class will store the relative path to the fxml so that we don't need to type it multiple times.
 */
public enum ViewController {
    LOGIN_CONTROLLER("/FXML/LoginView.fxml"),
    START_PAGE_CONTROLLER("/FXML/MainStartPageView.fxml"),
    NEW_PROVIDER_CONTROLLER("/FXML/NewProviderView.fxml"),
    NEW_EMPLOYEE_CONTROLLER("/FXML/NewEmployeeView.fxml"),
    NEW_EDUCATION_CONTROLLER("/FXML/NewEducationView.fxml"),
    NEW_INTERVIEW_CONTROLLER("/FXML/NewInterview.fxml"),
    NEW_COONSULTATION_CONTROLLER("/FXML/NewConsultView.fxml"),
    NEW_COMPANY_CONTROLLER("/FXML/NewCompController.fxml"),
    CHANGE_EMP_CONTROLLER("/FXML/ChangeEmpView.fxml"),
    DEL_COMP_CONTROLLER("/FXML/DelCompView.FXML"),
    DEL_COUN_VIEW("/FXML/DelCounView.fxml"),
    SA_CUST_VIEW("/FXML/SACustView.fxml");



    private final String URL;

    ViewController(String url) {
        this.URL = url;
    }

    /**
     * Returns the root node for the Application.AbstractController.
     * This method should be used when no previous scene is loaded.
     * @return Parent of Application.AbstractController
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
     * In order to pass a value the new controller needs to extends the 'Application.AbstractController' abstract class and override its methods.
     * </p>
     *
     * @param scene  The scene that will be replaced on reLoad
     * @param string Value that will passed on to the controller
     */
            /*
            If another parameter needs to passed follow thees steps:
            1) Create a initValues( - your parameters -) in the Application.Controller interface
            2) Create the same initValues in the Abstract class Application.AbstractController, and please keep the style of error messages
            3) Copy paste this method in here and change the parameter
            4) Make sure you call the correct initValues in this method
            */
    public void reLoad(Scene scene, String string) {
        reLoad(scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        Controllers newController = loader.getController();
        newController.initValues(string);
    }
}
