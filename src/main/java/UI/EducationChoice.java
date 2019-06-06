package UI;

import Application.Controller.PopUp.EducationReturnableController;
//import Application.NEWSTUFF.Controller.EducationReturnableController;
import Domain.Education;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EducationChoice extends Stage {

    public Education showAndReturn(EducationReturnableController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EducationReturnableController loadedController = loader.getController();
        setTitle("Select company");
        Scene scene = new Scene(root, 600, 400);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
        return loadedController.getReturn();
    }

    public Education showAndReturn(EducationReturnableController controller, Education education){
        controller.initValues(education);
        super.showAndWait();
        return controller.getReturn();
    }
}
