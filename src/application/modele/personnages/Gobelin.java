package application.modele.personnages;

import application.modele.Environnement;
import application.modele.objet.objetAttaque.BatteEnBois;
import application.modele.objet.objetAttaque.ObjetAttaque;

import java.io.IOException;

import static application.modele.Paramètre.*;
import static application.modele.Paramètre.halfHeightEnnemis;


public class Gobelin extends Personnage {

    private static int id = 0;

    public Gobelin(Environnement env, int x, int y, String direction) {
        super("Gobelin" + ++id, 50, new BatteEnBois("Batte en Bois"), env, x, y, direction);
    }



    @Override
    public void seDeplace(String direction) {

    }



    public void attaqueCAC() throws IOException {

        boolean linkPresent = false;
        Personnage link = super.getEnv().getLink();
        int porteAtaque = ((ObjetAttaque) super.getObjetEquipe()).getPorteAtaque();

        if (super.getTour() == 120) {

            // link devant l'ennemi (carré) //
            if (link.getY() + halfHeightLink >= this.getY() && link.getY() <= ((this.getY() + heightEnnemis) + porteAtaque)) {
                if (Math.abs(((link.getX()) + halfWidthLink) - (this.getX() + halfWidthEnnemis)) <= 16) {
                    linkPresent = true;
                }
            }


            // link derrière l'ennemi (carré) //
            if (link.getY() + halfHeightLink <= this.getY() + heightEnnemis && link.getY() + heightLink >= (this.getY() - porteAtaque)) {
                if (Math.abs((link.getX() + halfWidthLink) - (this.getX() + halfWidthEnnemis)) <= 16) {
                    linkPresent = true;
                }
            }


            // link à gauche de l'ennemi (carré) //
            if (link.getX() + widthLink <= this.getX() + halfWidthEnnemis && link.getX() + widthLink >= (this.getX() - porteAtaque)) {
                if (Math.abs((link.getY() + halfHeightLink) - (this.getY() + halfHeightEnnemis)) <= 16) {
                    linkPresent = true;
                }
            }


            // link à droite de l'ennemi (carré) //
            if (link.getX() >= this.getX() + halfWidthEnnemis && link.getX() <= ((this.getX() + widthEnnemis) + porteAtaque)) {
                if (Math.abs((link.getY() + halfHeightLink) - (this.getY() + halfHeightEnnemis)) <= 16) {
                    linkPresent = true;
                }
            }


            if (linkPresent) {
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


}


