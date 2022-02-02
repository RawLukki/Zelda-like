package application.modele.objet;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Objet {

    private String nom;
    private String type;


    public Objet(String nom, String type ){
        this.nom = nom;
        this.type = type;
    }



    public String getNom() {
        return this.nom;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }



    public String getType() {
        return this.type;
    }



    public void setType(String type) {
        this.type = type;
    }



    public String toString(){
        return "Un objet ";
    }


}
