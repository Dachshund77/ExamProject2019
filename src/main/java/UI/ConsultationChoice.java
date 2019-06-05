package UI;

import Application.Controller.PopUp.ConsultationReturnableController;
//import Application.NEWSTUFF.Controller.ConsultationReturnableController;
import Domain.Consultation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConsultationChoice extends Stage {

    public Consultation showAndReturn(ConsultationReturnableController controller){
        Parent root = controller.getParent();
        super.setTitle("Select consultation");
        super.setScene(new Scene(root, 600, 500));
        initModality(Modality.APPLICATION_MODAL);
        super.showAndWait();
        return controller.getReturn();
    }

    public Consultation showAndReturn(ConsultationReturnableController controller, Consultation consultation){
        controller.initValues(consultation);
        super.showAndWait();
        return controller.getReturn();
    }
}
