package UI;

import Application.Controller.PopUp.ConsultationReturnableController;
//import Application.NEWSTUFF.Controller.ConsultationReturnableController;
import Domain.Consultation;
import javafx.stage.Stage;

public class ConsultationChoice extends Stage {

    public Consultation showAndReturn(ConsultationReturnableController controller){
        super.showAndWait();
        return controller.getReturn();
    }

    public Consultation showAndReturn(ConsultationReturnableController controller, Consultation consultation){
        controller.initValues(consultation);
        super.showAndWait();
        return controller.getReturn();
    }
}
