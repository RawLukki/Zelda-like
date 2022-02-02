package application.vue.vuePersonnages;

import application.modele.personnages.Personnage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class AfficherSerpent extends ImageView{

    private Pane pane;
    private String direction;



    public AfficherSerpent(Personnage personnage, Pane pane) {
        super.setImage(new Image("application/ressources/imageMapZelda/Serpent/SnakeDroite.gif"));
        personnage = personnage;
        this.pane = pane;
        super.setId(personnage.getName());
        this.direction = "droite";
        super.xProperty().bind(personnage.getXProperty());
        super.yProperty().bind(personnage.getYProperty());
        super.setFitWidth(32);
        super.setFitHeight(32);

        this.pane.getChildren().add(this);

        ChangeListener<String> listener = ((obs, old, nouv) -> {
                super.setImage(new Image("application/ressources/imageMapZelda/Serpent/Snake" + nouv + ".gif"));
        });

        if (!personnage.getName().equals("Serpiente3")) {
            personnage.getDirectionProperty().addListener(listener);
        }
    }


}
