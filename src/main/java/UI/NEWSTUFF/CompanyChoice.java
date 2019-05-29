package UI.NEWSTUFF;

import Application.NEWSTUFF.Controller.CompanyReturnableController;
import Domain.Company;
import javafx.stage.Stage;

public class CompanyChoice extends Stage {

    public Company showAndReturn(CompanyReturnableController controller){
        super.showAndWait();
        return controller.getReturn();
    }

    public Company showAndReturn(CompanyReturnableController controller, Company company){
        controller.initValues(company);
        super.showAndWait();
        return controller.getReturn();
    }
}
