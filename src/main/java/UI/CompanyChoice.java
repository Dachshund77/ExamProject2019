package UI;

import Application.Controller.PopUp.CompanyReturnableController;
//import Application.NEWSTUFF.Controller.CompanyReturnableController;
import Domain.DomainObjects.Company;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CompanyChoice extends Stage {

    public Company showAndReturn(CompanyReturnableController controller)  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CompanyReturnableController loadedController = loader.getController();
        setTitle("Select company");
        Scene scene = new Scene(root, 600, 500);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
        return loadedController.getReturn();
    }

    public Company showAndReturn(CompanyReturnableController controller, Company company) {
        controller.initValues(company);
        super.showAndWait();
        return controller.getReturn();
    }
}
