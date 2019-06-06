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
 * LoadParent methods can be used to replace any node on a controller with the specified controller.
 */
 /*
            If another parameter needs to passed follow thees steps:
            1) Create a initValues( - your parameters -) in the Controllers interface
            2) Create the same initValues in the Abstract class AbstractController, and please keep the style of error messages
            3) Copy paste this method in here and change the parameter and initValues( - your parameters -)
            4) Override the created method in the controller you want it to be used.
            */
@SuppressWarnings("Duplicates")
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
    FIND_PROVIDER_TO_RECORD("/FXML/Controller/Find/FindToRecord/FindProviderToRecord.fxml"),

    //Pop up Controllers
    FIND_COMPANY_POPUP("/FXML/Controller/PopUp/Find/FindCompanyPopUp.fxml"),
    FIND_CONSULTATION_POPUP("/FXML/Controller/PopUp/Find/FindConsultationPopUp.fxml"),
    FIND_EDUCATION_POPUP("/FXML/Controller/PopUp/Find/FindEducationPopUp.fxml"),
    FIND_EMPLOYEE_POPUP("/FXML/Controller/PopUp/Find/FindEmployeePopUp.fxml"),
    FIND_INTERVIEW_POPUP("/FXML/Controller/PopUp/Find/FindInterviewPopUp.fxml"),
    FIND_PROVIDER_POPUP("/FXML/Controller/PopUp/Find/FindProviderPopUp.fxml"),

    //GoTo Delete X domain record
    DELETE_COMPANY("/FXML/Controller/Delete/DeleteCompany.fxml"),
    DELETE_CONSULTATION("/FXML/Controller/Delete/DeleteConsultation.fxml"),
    DELETE_EDUCATION("/FXML/Controller/Delete/DeleteEducation.fxml"),
    DELETE_EMPLOYEE("/FXML/Controller/Delete/DeleteEmployee.fxml"),
    DELETE_INTERVIEW("/FXML/Controller/Delete/DeleteInterview.fxml"),
    DELETE_PROVIDER("/FXML/Controller/Delete/DeleteProvider.fxml");


    private final String URL;

    ViewController(String url) {
        this.URL = url;
    }


    public String getURL() {
        return URL;
    }

    /**
     * Returns the root node for the controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controller root node.
     *
     * @return Parent of specified Controller.
     */
    public Parent loadParent() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(URL));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(String) initValues(String)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param string Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(String string) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(Provider) initValues(Provider)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param provider Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(Provider provider) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(provider);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(Education) initValues(Education)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param education Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(Education education) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(education);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(Employee) initValues(Employee)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param employee Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(Employee employee) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(Interview) initValues(Interview)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param interview Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(Interview interview) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(interview);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(Consultation) initValues(Consultation)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param consultation Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(Consultation consultation) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(consultation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(Company) initValues(Company)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param company Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(Company company) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(company);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(SearchContainer) initValues(SearchContainer)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param searchContainer Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(SearchContainer searchContainer) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(SearchContainer, Provider) initValues(SearchContainer, Provider)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param searchContainer Value that will be passed to the new Controller.
     * @param provider        Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(SearchContainer searchContainer, Provider provider) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, provider);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(SearchContainer, Education) initValues(SearchContainer, Education)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param searchContainer Value that will be passed to the new Controller.
     * @param education       Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(SearchContainer searchContainer, Education education) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, education);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(SearchContainer, Employee) initValues(SearchContainer, Employee)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param searchContainer Value that will be passed to the new Controller.
     * @param employee        Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(SearchContainer searchContainer, Employee employee) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(SearchContainer, Interview) initValues(SearchContainer, Interview)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param searchContainer Value that will be passed to the new Controller.
     * @param interview       Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(SearchContainer searchContainer, Interview interview) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, interview);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(SearchContainer, Consultation) initValues(SearchContainer, Consultation)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param searchContainer Value that will be passed to the new Controller.
     * @param consultation    Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(SearchContainer searchContainer, Consultation consultation) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, consultation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    /**
     * Returns the root node for the new controller.
     * This method should be used when no previous scene is loaded or
     * when a node should be replaced with the called controllers root node.
     * <br/><br/>
     * <font color=red>NOTE</font> that this method will invoke
     * {@link Controllers#initValues(SearchContainer, Company) initValues(SearchContainer, Company)} in the newly loaded controller and hence
     * the method needs to be overwritten in the new controller.
     *
     * @param searchContainer Value that will be passed to the new Controller.
     * @param company         Value that will be passed to the new Controller.
     * @return Parent of specified Controller.
     */
    public Parent loadParent(SearchContainer searchContainer, Company company) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            parent = loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, company);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }


    /**
     * Loads a new scene.
     * The scene URL is defined in the Enum constructor.
     *
     * @param scene The scene that will be replaced on reLoad
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene  The scene that will be replaced on reLoad
     * @param string Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, String string) {
        reLoad(scene);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(string);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene    The scene that will be replaced on reLoad
     * @param provider Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, Provider provider) {
        reLoad(scene);
        try {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(provider);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene     The scene that will be replaced on reLoad
     * @param education Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, Education education) {
        reLoad(scene);
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(education);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene    The scene that will be replaced on reLoad
     * @param employee Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, Employee employee) {
        reLoad(scene);
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(employee);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene     The scene that will be replaced on reLoad
     * @param interview Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, Interview interview) {
        reLoad(scene);
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(interview);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene        The scene that will be replaced on reLoad
     * @param consultation Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, Consultation consultation) {
        reLoad(scene);
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(consultation);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene   The scene that will be replaced on reLoad
     * @param company Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, Company company) {
        reLoad(scene);
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(company);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene           The scene that will be replaced on reLoad
     * @param searchContainer Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, SearchContainer searchContainer) {
        reLoad(scene);
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene           The scene that will be replaced on reLoad
     * @param searchContainer Value that will passed on to the controller
     * @param provider        Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, SearchContainer searchContainer, Provider provider) {
        reLoad(scene);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, provider);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene           The scene that will be replaced on reLoad
     * @param searchContainer Value that will passed on to the controller
     * @param education       Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, SearchContainer searchContainer, Education education) {
        reLoad(scene);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, education);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene           The scene that will be replaced on reLoad
     * @param searchContainer Value that will passed on to the controller
     * @param employee        Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, SearchContainer searchContainer, Employee employee) {
        reLoad(scene);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            Controllers newController = loader.getController();
            loader.load();
            newController.initValues(searchContainer, employee);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene           The scene that will be replaced on reLoad
     * @param searchContainer Value that will passed on to the controller
     * @param interview       Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, SearchContainer searchContainer, Interview interview) {
        reLoad(scene);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, interview);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene           The scene that will be replaced on reLoad
     * @param searchContainer Value that will passed on to the controller
     * @param consultation    Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, SearchContainer searchContainer, Consultation consultation) {
        reLoad(scene);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, consultation);
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
     * In order to pass a value the new controller needs to extends the AbstractController abstract class and override its methods.
     * </p>
     *
     * @param scene           The scene that will be replaced on reLoad
     * @param searchContainer Value that will passed on to the controller
     * @param company         Value that will passed on to the controller
     * @deprecated rather use LoadParent to replace nodes.
     */
    @Deprecated
    public void reLoad(Scene scene, SearchContainer searchContainer, Company company) {
        reLoad(scene);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(URL));
            loader.load();
            Controllers newController = loader.getController();
            newController.initValues(searchContainer, company);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
