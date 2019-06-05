package UI;

import Application.Controller.PopUp.InterviewReturnableController;
//import Application.NEWSTUFF.Controller.InterviewReturnableController;
import Domain.Interview;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InterviewChoice extends Stage {

    public Interview showAndReturn(InterviewReturnableController controller){
        Parent root = controller.getParent();
        super.setTitle("Select interview");
        super.setScene(new Scene(root, 600, 500));
        initModality(Modality.APPLICATION_MODAL);
        super.showAndWait();
        return controller.getReturn();
    }

    public Interview showAndReturn(InterviewReturnableController controller, Interview interview){
        controller.initValues(interview);
        super.showAndWait();
        return controller.getReturn();
    }
}
