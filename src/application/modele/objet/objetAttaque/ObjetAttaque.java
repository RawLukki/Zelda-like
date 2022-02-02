package application.modele.objet.objetAttaque;

import application.modele.objet.Objet;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public abstract class ObjetAttaque extends Objet {

    private IntegerProperty pointAttaque;
    private int porteAtaque;


    public ObjetAttaque(String nom, String type, int pointAttaque , int porteAtaque) {
        super(nom, type);
        this.pointAttaque = new SimpleIntegerProperty(pointAttaque);
        this.porteAtaque = porteAtaque;
    }



    public int getPointAttaqueValue() {
        return pointAttaque.getValue();
    }



    public IntegerProperty getPointAttaqueProperty() {
        return this.pointAttaque;
    }



    public void setPointAttaqueValue(int newPointAttaque) {
        this.pointAttaque.setValue(newPointAttaque);
    }



    public int getPorteAtaque() {
        return porteAtaque;
    }



    public void setPorteAtaque(int newPorteAtaque) {
        this.porteAtaque = newPorteAtaque;
    }



    public String toString(){
        return super.toString() + "d'attaque qui est ";
    }


}
