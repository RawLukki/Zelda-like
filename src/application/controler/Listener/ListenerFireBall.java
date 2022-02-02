package application.controler.Listener;

import application.modele.objet.FireBall;
import application.vue.AffichageFireBall;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListenerFireBall implements ListChangeListener<FireBall> {

    private Pane pane;


    public ListenerFireBall(Pane pane) {
        this.pane = pane;
    }


    @Override
    public void onChanged(Change<? extends FireBall> change) {

        while (change.next()) {

            if (change.wasAdded()) {
                for(FireBall fireBall : change.getAddedSubList()) {
                    new AffichageFireBall(fireBall, this.pane);
                }
            }

            if (change.wasRemoved()) {
                for (FireBall fireBall : change.getRemoved()) {
                    enleverSprite(fireBall);
                }
            }
        }
    }



    public  void enleverSprite(FireBall fireBall) {
        this.pane.getChildren().remove(this.pane.lookup("#" + fireBall.getId()));
    }

}