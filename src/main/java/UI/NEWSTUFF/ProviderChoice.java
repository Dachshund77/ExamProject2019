package UI.NEWSTUFF;

import Application.NEWSTUFF.ProviderReturnableController;
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
