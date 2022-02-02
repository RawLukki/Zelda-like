package application.modele.objet;

import application.modele.Environnement;
import application.modele.objet.Objet;
import application.modele.objet.objetAttaque.ObjetAttaque;
import application.modele.personnages.Link;
import application.modele.personnages.Personnage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import static application.modele.Paramètre.*;


public class FireBall extends Objet {

    private IntegerProperty x;
    private IntegerProperty y;
    private static int compteur = 0;
    private String id;
    private String direction;
    private IntegerProperty dureeVie;
    private int degat;
    private Environnement env;



    public FireBall(String direction, int x, int y, String typeFireBall, int degat, Environnement env) {
        super("FireBall", typeFireBall);
        this.id = "F" + compteur;
        this.compteur++;
        this.env = env;
        this.degat = degat;
        this.direction = direction;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dureeVie = new SimpleIntegerProperty(120); // représente 2 secondes //

    }



    public String getDirection() {
        return this.direction;
    }



    public int getDegat() {
        return this.degat;
    }



    public Environnement getEnv() {
        return this.env;
    }



    public String getId() {
        return id;
    }



    public IntegerProperty getXProperty() {
        return this.x;
    }



    public int getXValue() {
        return this.x.getValue();
    }



    public void setXProperty(IntegerProperty x) {
        this.x = x;
    }



    public void setXValue(int newValue) {
        this.x.setValue(newValue);
    }



    public IntegerProperty getYProperty() {
        return this.y;
    }



    public int getYValue() {
        return this.y.getValue();
    }



    public void setYProperty(IntegerProperty y) {
        this.y = x;
    }



    public void setYValue(int newValue) {
        this.y.setValue(newValue);
    }



    public IntegerProperty getDureeVieProperty() {
        return this.dureeVie;
    }



    public int getDureeVieValue() {
        return this.dureeVie.getValue();
    }



    public boolean finDeVie() {
        boolean finDeVie = false;
        if (this.dureeVie.getValue() <= 0) finDeVie = true;
        return  finDeVie;
    }



    public void deplacerFireBall() {

        int vitesse = 1;

        if (direction.equals("Haut") || direction.equals("Gauche")) vitesse = -vitesse;

        if (direction.equals("Haut") || direction.equals("Bas")) {
            this.y.setValue(this.y.getValue() + vitesse);
        }

        else this.x.setValue(this.x.getValue() + vitesse);

        this.dureeVie.setValue(this.dureeVie.getValue() - 1);

    }


    public Boolean toucher(Personnage personnage) {

        boolean touche = false;
        int widthPersonnage;
        int heightPersonnage;

        if (super.getType().equals("FireBall Link")) {
            widthPersonnage = widthLink;
            heightPersonnage = heightLink;
        }
        else {
            widthPersonnage = widthEnnemis;
            heightPersonnage = widthEnnemis;
        }

        if (personnage.getX() <= getXValue() && getXValue() <= personnage.getX() + widthPersonnage) {
            if (personnage.getY() <= getYValue() && getYValue() <= personnage.getY() + heightPersonnage) {

                if (personnage instanceof Link) {
                    if (personnage.getObjetEquipe() instanceof ObjetAttaque) {
                        personnage.decrementerPv(getDegat());
                        touche = true;
                    }
                    else System.out.println("Link se protège avec son bouclier");
                }
                else {
                    personnage.decrementerPv(getDegat());
                    touche = true;
                }
            }
        }

        return touche;
    }


}