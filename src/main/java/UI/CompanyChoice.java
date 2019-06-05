package UI;

import Application.Controller.PopUp.CompanyReturnableController;
//import Application.NEWSTUFF.Controller.CompanyReturnableController;
import Application.Controller.ViewController;
import Domain.Company;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class CompanyChoice extends Stage {

    public Company showAndReturn(CompanyReturnableController controller)  {
        Parent root = controller.getParent();
        super.setTitle("Select company");
        Scene scene = new Scene(root, 600, 400);
        super.setScene(scene);
        initModality(Modality.APPLICATION_MODAL);

        super.showAndWait();

        System.out.println("Returned value to Company choice " + controller.getReturn());
        return controller.getReturn();
    }

    public Company showAndReturn(CompanyReturnableController controller, Company company) {
        throw new UnsupportedOperationException("Not impleneted");
        //controller.initValues(company);
        //super.showAndWait();
        //return controller.getReturn();
    }
}
