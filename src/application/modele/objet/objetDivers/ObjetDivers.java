package application.modele.objet.objetDivers;

import application.modele.objet.Objet;



public abstract class ObjetDivers extends Objet {



    public ObjetDivers(String nom, String type) {
        super(nom, type);
    }



    public String toString(){
        return super.toString() + "divers qui est ";
    }
}
