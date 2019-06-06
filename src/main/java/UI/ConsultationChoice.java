package UI;

import Application.Controller.PopUp.ConsultationReturnableController;
//import Application.NEWSTUFF.Controller.ConsultationReturnableController;
import Domain.Consultation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ConsultationChoice extends Stage {

    public Consultation showAndReturn(ConsultationReturnableController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConsultationReturnableController loadedController = loader.getController();
        setTitle("Select company");
        Scene scene = new Scene(root, 600, 400);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
        return loadedController.getReturn();
    }

    public Consultation showAndReturn(ConsultationReturnableController controller, Consultation consultation){
        controller.initValues(consultation);
        super.showAndWait();
        return controller.getReturn();
    }
}
