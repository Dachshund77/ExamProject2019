package UI;

import Application.Controller.PopUp.ProviderReturnableController;
//import Application.NEWSTUFF.Controller.ProviderReturnableController;
import UI.NEWSTUFF.*;
import Domain.Provider;
import javafx.stage.Stage;

public class ProviderChoice extends Stage {



    public Provider showAndReturn(ProviderReturnableController controller){
        super.showAndWait();
        return controller.getReturn();
    }

    public Provider showAndReturn(ProviderReturnableController controller, Provider provider){
        controller.initValues(provider);
        super.showAndWait();
        return controller.getReturn();
    }
}
