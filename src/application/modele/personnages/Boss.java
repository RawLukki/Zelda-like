package application.modele.personnages;

import application.modele.Environnement;
import application.modele.objet.objetAttaque.BatteEnBois;
import application.modele.objet.objetAttaque.Hache;
import application.modele.objet.objetAttaque.ObjetAttaque;

import java.io.IOException;
import static application.modele.Paramètre.*;

/**
 * Le boss du jeu
 * possède une methode d'attaque au corps à corps
 * cette méthode défini une zone autour de lui
 * si link entre en contact avec cette zone, il prend des dégât
 * sauf si il est équipé d'un bouclier
 */

public class Boss extends Personnage {


    public Boss(Environnement env, int x, int y, String direction) {
        super("Guardian", 100, new Hache("Hache"), env, x, y, direction);
    }



    public void attaqueCAC() throws IOException {

        Personnage link = super.getEnv().getLink();
        int porteAtaque = ((ObjetAttaque) super.getObjetEquipe()).getPorteAtaque();

        if (super.getTour() == 120) {
            if (Math.sqrt(Math.pow(((link.getX() + halfWidthLink) - (getX() + halfWidthEnnemis)), 2) + Math.pow(((link.getY() + halfHeightLink) - (getY() + halfHeightEnnemis)), 2)) <= porteAtaque + 21) {
                if (link.getObjetEquipe() instanceof ObjetAttaque) {
                    link.decrementerPv(((ObjetAttaque) super.getObjetEquipe()).getPointAttaqueValue());
                    System.out.println(link.getPtsDeVieValue());
                }
                else System.out.println("Link se protège avec son bouclier");
            }

            super.setTour(0);
        }
        else super.incrementerTour();
    }



    public void attaqueSpeciale() {

    }


}