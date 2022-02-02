package application.modele.personnages;

import application.modele.Environnement;
import application.modele.objet.objetAttaque.Baguette;

import static application.modele.Paramètre.*;

/**
 * Les sorcier possèdent une méthode d'attaque
 * ils créent une boule de feu grâce à leur baguette
 * cette boule de feu ne peut être créer que toutes les 3 secondes
 * elle est ensuite ajouter à la liste de boule de feu de l'environnement
 * puis l'environnement se charge de gérer les deplacement ou autre des boules de feu
 */

public class Sorcier extends Personnage {

    private static int id = 0;


    public Sorcier(Environnement env, int x, int y, String direction) {
        super("Sorcier" + ++id, 30, new Baguette("Baguette"), env, x, y, direction);
    }



    @Override
    public void seDeplace(String direction) {

    }



    public void attaqueDistance() {
        Baguette baguette;
        String direction = super.getDirection();
        baguette = ((Baguette)super.getObjetEquipe());
        Personnage link = super.getEnv().getLink();

        if (link.getX() + widthLink >= super.getX() && link.getX() <= super.getX() + widthEnnemis && link.getY() >= super.getY()) direction = "Bas";
        else if (link.getX() + widthLink >= super.getX() && link.getX() <= super.getX() + widthEnnemis && link.getY() <= super.getY() + heightEnnemis) direction = "Haut";

        if (super.getTour() == 180) {
            baguette.lancerFireBall(direction, super.getX() + halfWidthEnnemis, super.getY() + halfHeightEnnemis, "FireBall ennemi", super.getEnv());
            super.setTour(0);
        }
        else {
            super.incrementerTour();
        }
    }


}
