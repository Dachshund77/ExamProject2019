package UI.NEWSTUFF;

import Application.Controller.PopUp.EducationReturnableController;
//import Application.NEWSTUFF.Controller.EducationReturnableController;
import Domain.Education;
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
