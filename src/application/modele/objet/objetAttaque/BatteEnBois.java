package application.modele.objet.objetAttaque;



public class BatteEnBois extends ObjetAttaque {



    public BatteEnBois(String nom) {
        super(nom, "BatteEnBois", 15, 16);
    }



    public String toString(){
        return super.toString() + "une batte en bois";
    }


}
