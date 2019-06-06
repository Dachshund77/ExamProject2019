package UI;

import Application.Controller.PopUp.ProviderReturnableController;
import Domain.Provider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProviderChoice extends Stage {



    public Provider showAndReturn(ProviderReturnableController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProviderReturnableController loadedController = loader.getController();
        setTitle("Select company");
        Scene scene = new Scene(root, 600, 500);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
        return loadedController.getReturn();
    }

    public Provider showAndReturn(ProviderReturnableController controller, Provider provider){
        controller.initValues(provider);
        super.showAndWait();
        return controller.getReturn();
    }
}
