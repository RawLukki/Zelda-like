package application.vue.vuePersonnages;

import application.modele.personnages.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class AfficherLink extends ImageView {

    private Pane pane;
    private String direction;



    public AfficherLink(Personnage personnage, Pane pane) {
        super.setImage(new Image("application/ressources/imageMapZelda/imageLink/Png/linkFixeGauche.png"));
        personnage = personnage;
        this.pane = pane;
        super.setId(personnage.getName());
        this.direction = "fixe";
        super.xProperty().bind(personnage.getXProperty());
        super.yProperty().bind(personnage.getYProperty());
        super.setFitWidth(28);
        super.setFitHeight(38);

        this.pane.getChildren().add(this);
    }



    public void changerImage(String direction) {

        if (direction.equals("fixe") && !(this.direction.equals(direction))) {
            super.setImage(new Image("application/ressources/imageMapZelda/imageLink/Png/linkFixe" + this.direction + ".png"));
        }

        else if (!(this.direction.equals(direction))) {
            super.setImage(new Image("application/ressources/imageMapZelda/imageLink/Gifs/linkMarche" + direction + ".gif"));
        }

        this.direction = direction;
        super.setFitWidth(28);
        super.setFitHeight(38);
    }


}
