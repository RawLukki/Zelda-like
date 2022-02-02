package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	public void start(Stage primaryStage) throws IOException {
		Pane canvas = FXMLLoader.load(getClass().getResource("vue/vueFxml/MenuPrincipal.fxml"));
		canvas.setStyle("-fx-background-image: url(\"application/ressources/giphy.gif\");");
		Scene scene = new Scene(canvas, 768, 640);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.setTitle("Zelda The TriForce's Quest");
		primaryStage.show();
	}

}
