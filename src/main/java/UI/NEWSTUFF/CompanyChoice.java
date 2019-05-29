package UI.NEWSTUFF;

import Application.NEWSTUFF.CompanyReturnableController;
import Application.NEWSTUFF.ConsultationReturnableController;
import Domain.Company;
import Domain.Consultation;
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
