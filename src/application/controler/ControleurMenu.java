package application.controler;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ControleurMenu implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private Button start;

	@FXML
	private Button controles;

	@FXML
	private Button quete;

	@FXML
	private Button quitter;

	@FXML
	private Pane pane;

	@FXML
	private ImageView flecheRetourQuete;

	@FXML
	private ImageView flecheRetourCommandes;

	@FXML
	void retourCommandes(MouseEvent event) throws IOException {
		Pane newRoot = FXMLLoader.load(getClass().getResource("../vue/vueFxml/MenuPrincipal.fxml"));
		newRoot.setStyle("-fx-background-image: url(\"application/ressources/giphy.gif\");");
		flecheRetourCommandes.getScene().setRoot(newRoot);
	}

	@FXML
	void retourQuete(MouseEvent event) throws IOException {
		Pane newRoot = FXMLLoader.load(getClass().getResource("../vue/vueFxml/MenuPrincipal.fxml"));
		newRoot.setStyle("-fx-background-image: url(\"application/ressources/giphy.gif\");");
		flecheRetourQuete.getScene().setRoot(newRoot);
	}

	@FXML
	void Controles(ActionEvent event) throws IOException {
		Pane newRoot = FXMLLoader.load(getClass().getResource("../vue/vueFxml/commandes.fxml"));
		newRoot.setStyle("-fx-background-image: url(\"application/ressources/giphy.gif\");");
		pane.getScene().setRoot(newRoot);

	}

	@FXML
	void Quete(ActionEvent event) throws IOException {
		Pane newRoot = FXMLLoader.load(getClass().getResource("../vue/vueFxml/Quete.fxml"));
		newRoot.setStyle("-fx-background-image: url(\"application/ressources/fondQuete.jpg\");");
		pane.getScene().setRoot(newRoot);

	}

	@FXML
	void Quitter(ActionEvent event) {
		pane.getScene().getWindow().hide();

	}

	@FXML
	public void start(ActionEvent event) throws IOException {
		Pane newRoot = FXMLLoader.load(getClass().getResource("../vue/vueFxml/vueMapZelda.fxml"));
		pane.getScene().setRoot(newRoot);
	}
}
