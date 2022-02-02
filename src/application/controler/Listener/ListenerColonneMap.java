package application.controler.Listener;

import application.modele.Jeu;
import application.vue.AffichageMap;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.TilePane;

import java.io.IOException;

public class ListenerColonneMap implements ListChangeListener<Integer> {

    private Jeu zelda;
    private TilePane calque2;
    private AffichageMap affichageMapZeldaColision;



    public ListenerColonneMap(Jeu zelda, TilePane calque2, AffichageMap affichageMapZeldaColision) {
        this.zelda = zelda;
        this.calque2 = calque2;
        this.affichageMapZeldaColision = affichageMapZeldaColision;
    }



    @Override
    public void onChanged(Change<? extends Integer> change) {
        this.calque2.getChildren().clear();
        this.affichageMapZeldaColision.setMatriceMap(this.zelda.getEnv().getMatriceCollision());
        try {
            this.affichageMapZeldaColision.chargerMap(this.calque2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
