package UI;

import Application.Controller.PopUp.CompanyReturnableController;
//import Application.NEWSTUFF.Controller.CompanyReturnableController;
import Application.Controller.ViewController;
import Domain.Company;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CompanyChoice extends Stage {

    public Company showAndReturn(CompanyReturnableController controller){
        Parent root = controller.getParent();
        super.setTitle("Select company");
        super.setScene(new Scene(root, 600, 500));
        initModality(Modality.APPLICATION_MODAL);
        super.showAndWait();
        return controller.getReturn();
    }

    public Company showAndReturn(CompanyReturnableController controller, Company company){
        controller.initValues(company);
        super.showAndWait();
        return controller.getReturn();
    }
}
