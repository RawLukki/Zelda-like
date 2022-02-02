package application.modele.objet.objetAttaque;



public class Epee extends ObjetAttaque {



    public Epee(String nom) {
        super(nom, "Epee", 20, 16);
    }



    public String toString() {
        return super.toString() + "une épée";
    }


}
