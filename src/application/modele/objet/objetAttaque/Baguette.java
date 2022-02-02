package application.modele.objet.objetAttaque;


import application.modele.Environnement;
import application.modele.objet.FireBall;


public class Baguette extends ObjetAttaque {


    private int tour;

    public Baguette(String nom) {
        super(nom, "Baguette", 10, 0);
    }



    public void lancerFireBall(String direction, int x, int y, String typeFireBall, Environnement env) {
            FireBall fireBall = new FireBall(direction, x, y, typeFireBall, super.getPointAttaqueValue(), env);
            env.addFireBall(fireBall);
    }



    public String toString(){
        return super.toString() + "une baguette";
    }


}
