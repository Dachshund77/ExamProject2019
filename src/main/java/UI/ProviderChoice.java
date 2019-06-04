package UI;

import Application.Controller.PopUp.ProviderReturnableController;
import Domain.Provider;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProviderChoice extends Stage {



    public Provider showAndReturn(ProviderReturnableController controller){
        Parent root = controller.getParent();
        super.setTitle("Select provider");
        super.setScene(new Scene(root, 600, 400));
        initModality(Modality.APPLICATION_MODAL);
        super.showAndWait();
        return controller.getReturn();
    }

    public Provider showAndReturn(ProviderReturnableController controller, Provider provider){
        controller.initValues(provider);
        super.showAndWait();
        return controller.getReturn();
    }
}
