package application.modele.personnages;

import application.modele.Environnement;
import application.modele.objet.objetAttaque.Canine;
import application.modele.objet.objetAttaque.ObjetAttaque;

import java.io.IOException;
import static application.modele.Paramètre.*;

/**
 * Les serpents font peut de dégât
 * mais attaque toutes les 1 secondes et ils sont invincibles
 * ces derniers se déplacent continuellement pour protéger le donjon
 * le but est de les contourner
 * si link s'approche de trop près il prend des dégât
 */

public class Serpent extends Personnage {

    private int tour;
    private static int id = 0;



    public Serpent(Environnement env, int x, int y, String direction) {
        super("Serpiente" + ++id, 1000000, new Canine("Canines"), env, x, y, direction);
        this.tour = 0;
    }



    public void attaqueCAC() throws IOException {

        boolean linkPresent = false;
        Personnage link = super.getEnv().getLink();
        int porteAtaque = ((ObjetAttaque) super.getObjetEquipe()).getPorteAtaque();


        if (super.getTour() == 60) {

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



    public void garderDonjon() throws IOException {

        String name = super.getName();

        if (this.tour < 300 && this.tour % 4 == 0) {

            if (name.equals("Serpiente1") || name.equals("Serpiente2")) {
                super.seDeplace("Droite");
                if (!(super.getDirection().equals("Droite"))) {
                    super.setDirection("Droite");
                }
            }

            else {
                super.seDeplace("Haut");
                if (!(super.getDirection().equals("Haut"))) {
                    super.setDirection("Haut");
                }
            }

        }

        else if (this.tour >= 300 && this.tour < 600 && this.tour % 4 == 0) {

            if (name.equals("Serpiente1") || name.equals("Serpiente2")) {
                super.seDeplace("Gauche");
                if (!(super.getDirection().equals("Gauche"))) {
                    super.setDirection("Gauche");
                }
            }

            else {
                super.seDeplace("Bas");
                if (!(super.getDirection().equals("Bas"))) {
                    super.setDirection("Bas");
                }
            }
        }

        if (this.tour >= 600) {
            this.tour = 0;
        }

        else this.tour++;


    }


}
