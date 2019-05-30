package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Application.ViewController;

public class FindCompMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = ViewController.FIND_COMPANY_CONTROLLER.loadParent();
        primaryStage.setTitle("Find company");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setResizable(true);
    }
    public static void main(String[] args) {
        launch(args);
    }
}