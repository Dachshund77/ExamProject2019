package UI.NEWSTUFF;

import Application.NEWSTUFF.ConsultationReturnableController;
import Application.NEWSTUFF.ProviderReturnableController;
import Domain.Consultation;
import Domain.Provider;
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
