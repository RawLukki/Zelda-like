package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {


    public static void main(String[] args) {

        launch(args);
    }


    public void start(Stage primaryStage) {
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("vue/vueFxml/vueMapZelda.fxml"));
            Scene scene = new Scene(root, 768, 640);
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.setTitle("Zelda The TriForce's Quest");
            primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
