package UI;

import Application.Controller.PopUp.InterviewReturnableController;
//import Application.NEWSTUFF.Controller.InterviewReturnableController;
import Domain.Interview;
import javafx.stage.Stage;

public class InterviewChoice extends Stage {

    public Interview showAndReturn(InterviewReturnableController controller){
        super.showAndWait();
        return controller.getReturn();
    }

    public Interview showAndReturn(InterviewReturnableController controller, Interview interview){
        controller.initValues(interview);
        super.showAndWait();
        return controller.getReturn();
    }
}
