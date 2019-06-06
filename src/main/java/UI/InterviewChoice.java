package UI;

import Application.Controller.PopUp.InterviewReturnableController;
//import Application.NEWSTUFF.Controller.InterviewReturnableController;
import Domain.Interview;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InterviewChoice extends Stage {

    public Interview showAndReturn(InterviewReturnableController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InterviewReturnableController loadedController = loader.getController();
        setTitle("Select company");
        Scene scene = new Scene(root, 600, 400);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
        return loadedController.getReturn();
    }

    public Interview showAndReturn(InterviewReturnableController controller, Interview interview){
        controller.initValues(interview);
        super.showAndWait();
        return controller.getReturn();
    }
}
