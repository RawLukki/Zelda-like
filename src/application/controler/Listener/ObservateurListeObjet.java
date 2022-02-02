package application.controler.Listener;

import application.modele.objet.Objet;
import application.modele.objet.objetAttaque.Baguette;
import application.modele.objet.objetAttaque.ObjetAttaque;
import application.modele.objet.objetDivers.Bouclier;
import application.modele.objet.objetDivers.LegendaryRing;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ObservateurListeObjet implements ListChangeListener<Objet> {

    private Pane pane;
    private Label baguette;
    private Label bouclier;
    private ImageView bague;


    public ObservateurListeObjet(Pane pane, Label baguette, Label bouclier, ImageView bague) {
        this.pane = pane;
        this.baguette = baguette;
        this.bouclier = bouclier;
        this.bague = bague;
    }



    @Override
    public void onChanged(Change<? extends Objet> change) {

        while (change.next()) {
            for (Objet objet : change.getAddedSubList()) {
                if (objet.getNom().equals("Baguette")) this.baguette.setText("Posséder");
                else if (objet.getNom().equals("Bouclier")) this.bouclier.setText("Posséder");
                else if (objet.getNom().equals("Bague")) this.bague.setVisible(true);
            }
        }
    }


}
