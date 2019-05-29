package UI.NEWSTUFF;

import Application.NEWSTUFF.EducationReturnableController;
import Application.NEWSTUFF.ProviderReturnableController;
import Domain.Education;
import Domain.Provider;
import javafx.stage.Stage;

public class EducationChoice extends Stage {

    public Education showAndReturn(EducationReturnableController controller){
        super.showAndWait();
        return controller.getReturn();
    }

    public Education showAndReturn(EducationReturnableController controller, Education education){
        controller.initValues(education);
        super.showAndWait();
        return controller.getReturn();
    }
}
