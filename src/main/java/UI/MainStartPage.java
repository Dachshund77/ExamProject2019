package UI;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Application.NEWSTUFF.Controller.ViewController;

public class MainStartPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = ViewController.START_PAGE_CONTROLLER.loadParent();
        primaryStage.setTitle("Main page");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setResizable(true);
    }


    public static void main(String[] args) {
        launch(args);
    }
}