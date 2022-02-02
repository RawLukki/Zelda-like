package application.modele.personnages;

import application.modele.Environnement;
import application.modele.objet.objetAttaque.ObjetAttaque;
import application.modele.objet.objetDivers.Bouclier;
import application.modele.objet.Objet;
import javafx.beans.property.*;

import java.io.IOException;


public abstract class Personnage {

    private String nomPerso;
    private IntegerProperty ptsDeVie;
    private Objet objetEquipé;
    private StringProperty nomObjetEquipe;
    private IntegerProperty pointAtkArme;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Environnement env;
    private StringProperty directionProperty;
    private int tour;



    public Personnage(String nom, int pv, Objet objetEquipé, Environnement env, int x, int y, String direction){
        this.nomPerso = nom;
        this.ptsDeVie = new SimpleIntegerProperty(pv);
        this.objetEquipé = objetEquipé;
        this.env = env;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.directionProperty = new SimpleStringProperty(direction);
        this.nomObjetEquipe = new SimpleStringProperty(this.objetEquipé.getNom());
        this.pointAtkArme = new SimpleIntegerProperty(0);
        this.tour = 0;
    }



    public IntegerProperty getXProperty() {
        return this.xProperty;
    }



    public IntegerProperty getYProperty() {
        return this.yProperty;
    }



    public int getX() {
        return this.xProperty.getValue();
    }



    public int getY() {
        return this.yProperty.getValue();
    }



    public void setX(int x) {
        this.xProperty.setValue(x);
    }



    public void setY(int y) {
        this.yProperty.setValue(y);
    }



    public StringProperty getDirectionProperty() {
        return this.directionProperty;
    }



    public String getDirection() {
        return this.directionProperty.getValue();
    }



    public void setDirection(String direction) {
        this.directionProperty.setValue(direction);
    }



    public String getName(){
        return this.nomPerso;
    }



    public IntegerProperty getPtsDeVieProperty() {
        return this.ptsDeVie;
    }



    public int getPtsDeVieValue(){
        return this.ptsDeVie.getValue();
    }



    public void setPv(int newPv){
        this.ptsDeVie.setValue(newPv);
    }



    public IntegerProperty getPointAtkArmeProperty() {
        return this.pointAtkArme;
    }



    public int getTour() {
        return this.tour;
    }



    public void setTour(int tour) {
        this.tour = tour;
    }



    public void incrementerTour() {
        this.tour += 1;
    }



    public int getPointAtkArmeValue() {
        return this.pointAtkArme.getValue();
    }



    public void setPointAtkArmeValue(int pointAtkArme) {
        this.pointAtkArme.setValue(pointAtkArme);
        System.out.println(this.pointAtkArme);
    }



    public StringProperty getNomObjetEquipeProperty() {
        return this.nomObjetEquipe;
    }



    public String getNomObjetEquipeValue() {
        return this.nomObjetEquipe.getValue();
    }



    public void setNomObjetEquipe(String nomObjetEquipe) {
        this.nomObjetEquipe.set(nomObjetEquipe);
    }



    public Objet getObjetEquipe(){
        return this.objetEquipé;
    }



    public void setObjetEquipé(Objet a){
        this.objetEquipé = a;
        this.setNomObjetEquipe(a.getNom());
        if (a instanceof ObjetAttaque) {
            this.setPointAtkArmeValue(((ObjetAttaque) a).getPointAttaqueValue());
        }
        else this.setPointAtkArmeValue(0);
    }



    public Environnement getEnv() {
        return env;
    }



    public void incrementerPv(int pvUP) {
        this.ptsDeVie.setValue(this.ptsDeVie.getValue() + pvUP);
    }



    public void decrementerPv(int pvDown) {
        this.ptsDeVie.setValue(this.ptsDeVie.getValue() - pvDown);
        if (this.ptsDeVie.getValue() < 0) this.ptsDeVie.setValue(0);
    }



    public Boolean estMort(){

        boolean estMort = false;
        if(this.getPtsDeVieValue()<=0){
            estMort = true;
        }
        if (this.getPtsDeVieValue()<0) {
            this.setPv(0);
        }
        return estMort;
    }



    public void seDeplace(String direction) throws IOException {

        int vitesse = 2;

        if (this.env.dansTerrain(this.getX(), this.getY()) && this.env.collision(direction)) {

            if (!(this.getDirection().equals(direction))) this.setDirection(direction);

            if (direction.equals("Haut") || direction.equals("Gauche")) vitesse = -vitesse;

            if (direction.equals("Haut") || direction.equals("Bas")) {
                this.yProperty.setValue(this.yProperty.getValue() + vitesse);
            } else this.xProperty.setValue(this.xProperty.getValue() + vitesse);
        }

    }



    public String toString() {
        return "Nom : " + this.nomPerso + " pv : " + this.getPtsDeVieValue() + " | posX " + this.getX() + " | posY " + this.getY();
    }


}