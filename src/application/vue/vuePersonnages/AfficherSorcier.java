package application.vue.vuePersonnages;

import application.modele.personnages.Personnage;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class AfficherSorcier extends ImageView {

    private Pane pane;



    public AfficherSorcier(Personnage personnage, Pane pane) {

        if (personnage.getDirection().equals("Gauche")) {
            super.setImage(new Image("application/ressources/imageMapZelda/Sorcier/MageAttaqueGauche.png"));
        }
        else super.setImage(new Image("application/ressources/imageMapZelda/Sorcier/MageAttaqueDroite.png"));
        personnage = personnage;
        this.pane = pane;
        super.setId(personnage.getName());
        super.xProperty().bind(personnage.getXProperty());
        super.yProperty().bind(personnage.getYProperty());
        super.setFitWidth(32);
        super.setFitHeight(42);
        Rectangle r = new Rectangle(30, 3);
        r.xProperty().bind(personnage.getXProperty().subtract(5));
        r.yProperty().bind(personnage.getYProperty().subtract(5));
        r.setFill(Color.RED);
        r.setId("HP" + personnage.getName());

        ChangeListener<Number> l1 = (((observableValue, number, t1) -> {
            if ((Integer)t1 <= 0) {
                System.out.println(pane.getChildren().remove(pane.lookup("#" + r.getId())));
                pane.getChildren().remove(r);
            }
            else r.setWidth(t1.intValue());
        }));
        personnage.getPtsDeVieProperty().addListener(l1);

        this.pane.getChildren().add(this);
        this.pane.getChildren().add(r);
    }


}
