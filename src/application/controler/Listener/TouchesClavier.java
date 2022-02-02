package application.controler.Listener;


import application.modele.Jeu;
import application.modele.objet.objetAttaque.Baguette;
import application.modele.objet.objetAttaque.Epee;
import application.modele.objet.FireBall;
import application.modele.objet.objetDivers.Bouclier;
import application.modele.personnages.Link;
import application.vue.vuePersonnages.AfficherLink;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;


/**
 * Classe qui implémente l'interface eventHandler<T>
 * Gère toutes les intéractions utilisateurs au clavier
 */


public class TouchesClavier implements EventHandler<KeyEvent> {

    private Jeu zelda;
    private AfficherLink imageLink;
    private HBox inventaire;
    private IntegerProperty tour;



    public TouchesClavier(Jeu zelda, AfficherLink imageLink, HBox inventaire, IntegerProperty tour) {
        this.zelda = zelda;
        this.imageLink = imageLink;
        this.inventaire = inventaire;
        this.tour = new SimpleIntegerProperty(0);
        this.tour.bindBidirectional(tour);
    }



    @Override
    public void handle(KeyEvent event) {
        FireBall fireBall;
        String direction = "fixe";
        Link link = zelda.getEnv().getLink();

        if (!zelda.getPartieFINI()) {

            try {

                switch (event.getCode()) {

                    case Z:
                        direction = "Haut";
                        break;


                    case Q:
                        direction = "Gauche";
                        break;


                    case S:
                        direction = "Bas";
                        break;


                    case D:
                        direction = "Droite";
                        break;


                    case E:
                        link.ouvrirCoffre();
                        if (link.getPrendBateauAller() == false && link.getPrendBateauRetour() == false) {
                            link.prendreBateau();
                        }
                        break;


                    case ADD:
                        if (this.tour.getValue() == 60) {
                            if (link.getObjetEquipe() instanceof Epee) link.attaqueCAC();
                            else if (link.getObjetEquipe() instanceof Baguette) {
                                link.attaqueDistance();
                            }

                            this.tour.setValue(0);
                        }
                        break;


                    case NUMPAD1:

                        if (link.getObjetEquipe() instanceof Baguette || link.getObjetEquipe() instanceof Bouclier) {
                            link.setObjetEquipe("Epee");
                        } else System.out.println("Vous êtes déjà équipé de cette arme");
                        break;


                    case NUMPAD2:
                        if (link.getObjetEquipe() instanceof Epee || link.getObjetEquipe() instanceof Bouclier) {
                            link.setObjetEquipe("Baguette");
                        } else System.out.println("Vous êtes déjà équipé de cette arme");
                        break;


                    case NUMPAD3:
                        if (link.getObjetEquipe() instanceof Epee || link.getObjetEquipe() instanceof Baguette) {
                            link.setObjetEquipe("Bouclier");
                        } else System.out.println("Vous êtes déjà équipé de cette arme");
                        break;


                    case I:
                        if (inventaire.isVisible()) {
                            inventaire.setVisible(false);
                        } else {
                            inventaire.setVisible(true);
                        }

                        break;

                }

                link.seDeplace(direction);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageLink.changerImage(direction);
        }
    }


}
