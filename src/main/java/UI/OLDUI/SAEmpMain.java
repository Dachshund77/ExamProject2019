package UI.OLDUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class SAEmpMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = ViewController.SA_EMPLOYEE_CONTROLLER.loadParent(); //TODO having issue here
        primaryStage.setTitle("Template");
        //primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setResizable(true);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
