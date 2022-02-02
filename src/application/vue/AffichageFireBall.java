package application.vue;

import application.modele.objet.FireBall;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AffichageFireBall extends ImageView {

    private Pane pane;
    private Circle bouleDeFeu;



    public AffichageFireBall(FireBall fireBall, Pane pane) {

        this.pane = pane;
        this.bouleDeFeu = new Circle(7);
        this.bouleDeFeu.setFill(Color.ORANGE);
        this.bouleDeFeu.setId(fireBall.getId());
        this.bouleDeFeu.centerXProperty().bind(fireBall.getXProperty());
        this.bouleDeFeu.centerYProperty().bind(fireBall.getYProperty().add(0));

        this.pane.getChildren().add(this.bouleDeFeu);
    }


}