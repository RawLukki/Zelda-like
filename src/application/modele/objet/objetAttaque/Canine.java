package application.modele.objet.objetAttaque;



public class Canine extends ObjetAttaque {



    public Canine(String nom) {
        super(nom, "Canine", 10, 8);
    }



    public String toString(){
        return super.toString() + "une paire de canine";
    }


}
