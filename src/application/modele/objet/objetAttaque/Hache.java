package application.modele.objet.objetAttaque;



public class Hache extends ObjetAttaque {



    public Hache(String nom) {
        super(nom, "Hache", 30, 16);
    }



    public String toString(){
        return super.toString() + "une hache";
    }


}
