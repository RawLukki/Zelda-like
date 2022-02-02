package application.modele.personnages;

import application.modele.Environnement;
import application.modele.objet.*;
import application.modele.objet.objetAttaque.Baguette;
import application.modele.objet.objetAttaque.Epee;
import application.modele.objet.objetAttaque.ObjetAttaque;
import application.modele.objet.objetDivers.Bouclier;
import application.modele.objet.objetDivers.LegendaryRing;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

import static application.modele.Paramètre.*;

/**
 * Link est capable de posséder plusieurs objet : une épée, une baguette et un bouclier
 * Il peut également obtenir un artefact qui lui augmentera toutes ses stats
 * Il peut ouvrir des coffres, detruire des tronc d'arbres et les arbres en bonne état
 * en détruisant des arbres en bonne état il récupère du bois
 * Il peut construire un bateau magique grâce au bois récupérer (il faut détruire les deux arbres)
 * Link peut également se déplacer et attaquer que ce soit avec son épéee ou sa baguette mais toutes les 1 secondes
 */

public class Link extends Personnage {

    private IntegerProperty defProperty;
    private ObservableList<Objet> listeObjet;
    private IntegerProperty boisProperty;
    private boolean prendBateauAller;
    private boolean prendBateauRetour;
    private IntegerProperty nbrEnnemiTuer;
    private Boolean triForce;



    public Link(Environnement env, int x, int y) {
        super("Link", 300, /*new Bouclier("Bouclier")*//*new Baguette("Baguette Magique")*/ new Epee("Epee"), env, x, y, "Gauche");
        this.defProperty = new SimpleIntegerProperty(5);
        this.listeObjet = FXCollections.observableArrayList();
        this.listeObjet.add(super.getObjetEquipe());
        this.boisProperty = new SimpleIntegerProperty(0);
        this.prendBateauAller = false;
        this.prendBateauRetour = false;
        this.nbrEnnemiTuer = new SimpleIntegerProperty(0);
        this.triForce = false;
    }



    public IntegerProperty getDefProperty() {
        return this.defProperty;
    }



    public int getDefValue() {
        return defProperty.getValue();
    }



    public void incrementerDef(int upDEF) {
        this.defProperty.setValue(this.defProperty.getValue() + upDEF);
    }



    public IntegerProperty getBoisProperty() {
        return this.boisProperty;
    }



    public int getBoisValue() {
        return this.boisProperty.getValue();
    }



    public void incrementerBois(int upBOIS) {
        this.boisProperty.setValue(this.boisProperty.getValue() + upBOIS);
    }



    public int getNbrEnnemiTuerValue() {
        return this.nbrEnnemiTuer.getValue();
    }



    public IntegerProperty getNbrEnnemiTuerProperty() {
        return this.nbrEnnemiTuer;
    }



    public void incrementerEnnemiTuer() {
        this.nbrEnnemiTuer.set(this.nbrEnnemiTuer.getValue()+1);
    }



    public boolean getPrendBateauAller() {
        return prendBateauAller;
    }



    public void setPrendBateauAller(boolean prendBateauAller) {
        this.prendBateauAller = prendBateauAller;
    }



    public boolean getPrendBateauRetour() {
        return prendBateauRetour;
    }



    public void setPrendBateauRetour(boolean prendBateauRetour) {
        this.prendBateauRetour = prendBateauRetour;
    }



    public ObservableList<Objet> getListeObjet() {
        return this.listeObjet;
    }



    public Boolean getTriForce() {
        return triForce;
    }



    public void ajouterObjet(Objet o) {
        this.listeObjet.add(o);
    }



    public Objet getObjetEquipe() {
        return super.getObjetEquipe();
    }



    public void setObjetEquipe(String nom) {
        for (Objet a : this.listeObjet) {
            if (a.getNom().equals(nom)) {
                super.setObjetEquipé(a);
                System.out.println(super.getObjetEquipe());
                break;
            }
        }
    }



    public void ringOn() {
        for (Objet a : this.listeObjet) {
            if (a instanceof LegendaryRing) {
                this.defProperty.setValue(this.defProperty.getValue() + ((LegendaryRing) a).getBonusDef());
                super.setPv(getPtsDeVieValue() + ((LegendaryRing) a).getBonusHealth());
                for (Objet b : this.listeObjet) {
                    if (b instanceof ObjetAttaque) {
                        ((ObjetAttaque) b).setPointAttaqueValue(((ObjetAttaque) b).getPointAttaqueValue() + ((LegendaryRing) a).getBonusAtt());
                    }
                }
            }
        }
    }



    public void feuDeCamp() throws IOException {
        ObservableList<ObservableList<Integer>> matriceCollision = super.getEnv().getMatriceCollision().getMatrice();

        int ligne = (int) ((super.getY() + halfHeightLink)/ 16);
        int colonne = (int) ((super.getX() + halfWidthLink) / 16);

        if ((42 <= colonne && colonne <= 47) && (ligne <= 31 && ligne >= 26)) {
            if (super.getPtsDeVieValue() < 300) {
                super.incrementerPv(10);
                if (super.getPtsDeVieValue() > 300) {
                    super.setPv(300);
                }
            }
        }

    }



    public boolean ouvrirCoffre() throws IOException {

        ObservableList<ObservableList<Integer>> matriceCollision = super.getEnv().getMatriceCollision().getMatrice();

        int ligne = (int) ((super.getY() + halfHeightLink)/ 16);
        int colonne = (int) ((super.getX() + halfWidthLink) / 16);
        int tuile = matriceCollision.get(ligne - 1).get(colonne);
        int colonne2;

        boolean coffreLock = true;

        if (tuile == 150 || tuile == 151 || tuile == 154 || tuile == 155) {

            if (tuile == 150 || tuile == 154) colonne2 = colonne + 1;
            else colonne2 = colonne - 1;

            if (ligne == 33 && super.getEnv().ennemisVaincu("Gobelin1") && super.getEnv().ennemisVaincu("Gobelin2")) coffreLock = false;
            else if(ligne == 5 && super.getEnv().ennemisVaincu("Gobelin3")) coffreLock = false;
            else if (ligne == 6 && super.getEnv().ennemisVaincu("Guardian")) coffreLock = false;
            else if (ligne == 37) coffreLock = false;

            if (!coffreLock) {
                for (int i = ligne - 1; i >= ligne - 2; i--) {
                    tuile = matriceCollision.get(i).get(colonne);
                    matriceCollision.get(i).set(colonne, tuile + 2);
                    tuile = matriceCollision.get(i).get(colonne2);
                    matriceCollision.get(i).set(colonne2, tuile + 2);
                }

                if (ligne - 1 == 32) this.ajouterObjet(new Baguette("Baguette"));
                else if (ligne - 1 == 4) this.ajouterObjet(new Bouclier("Bouclier"));
                else if (ligne - 1 == 5) this.triForce = true;
                else {
                    this.ajouterObjet(new LegendaryRing("Bague"));
                    this.ringOn();
                }
            }

            return true;
        }

        return false;
    }



    public boolean detruireTroncArbre() throws IOException {

        ObservableList<ObservableList<Integer>> matriceCollision = super.getEnv().getMatriceCollision().getMatrice();

        int ligne = (int) (super.getY() / 16);
        int colonne = (int) ((super.getX() + widthLink)/ 16);
        int tuile = matriceCollision.get(ligne).get(colonne + 1);

        if (tuile == 16 || tuile == 41 || tuile == 66 || tuile == 91) {
            System.out.println("tronc d'arbre détecté");
            for (int i = 14; i < 18; i++) {
                matriceCollision.get(i).set(colonne + 1, -1);
                matriceCollision.get(i).set(colonne + 2, -1);
            }
            System.out.println("tronc d'arbre détruit");
            return true;
        }

        return false;
    }



    public boolean detruireArbre() throws IOException {

        ObservableList<ObservableList<Integer>> matriceCollision = super.getEnv().getMatriceCollision().getMatrice();

        int lignePerso = (int) (super.getY() / 16);
        int colonnePerso = (int) (super.getX() / 16);
        int tuile = matriceCollision.get(lignePerso+3).get(colonnePerso);


        if (super.getDirection().equals("Bas") && (tuile >= 108 && tuile <= 111)) {

            System.out.println("Arbre détecté");

            for (int ligne = lignePerso + 3; ligne <= lignePerso + 6; ligne++) {

                if (colonnePerso >= 35 && colonnePerso <= 38) {
                    for (int colonne = 35; colonne <= 38; colonne++) {
                        matriceCollision.get(ligne).set(colonne, -1);
                    }
                }
                else {
                    for (int colonne = 43; colonne <= 46; colonne++) {
                        matriceCollision.get(ligne).set(colonne, -1);
                    }
                }
            }

            System.out.println("Arbre détruit");
            this.incrementerBois(20);
            System.out.println("Nbr de bois : " + this.boisProperty.getValue());
            return  true;
        }

        System.out.println("Aucun arbre");
        return false;
    }



    public void prendreBateau() throws IOException {

        ObservableList<ObservableList<Integer>> matriceCollision = super.getEnv().getMatriceCollision().getMatrice();

        int ligne = (int) (super.getY() / 16);
        int colonne = (int) (super.getX() / 16);
        int tuile = matriceCollision.get(ligne + 3).get(colonne);
        int tuile2 = matriceCollision.get(ligne).get(colonne-1);

        System.out.println(super.getDirection());
        if (this.boisProperty.getValue() == 40) {
            if (tuile == 26 && (colonne >= 26 && colonne <= 29) && super.getDirection().equals("Bas")) {
                super.setY(super.getY() + heightLink);
                this.setPrendBateauAller(true);
            }
            else if (tuile2 == 26 && (ligne >= 35 && ligne <= 38 && colonne == 37) && super.getDirection().equals("Gauche")) {
                super.setX(super.getX() - widthLink);
                this.setPrendBateauRetour(true);
            }
            else System.out.println("Il n'y a pas de bateau");
        }

        else  {
            System.out.println("Vous n'avez pas assez de bois");
        }

    }



    public void allerEnBateau() throws IOException {

        ObservableList<ObservableList<Integer>> matriceCollision = super.getEnv().getMatriceCollision().getMatrice();

        int ligne = (int) ((super.getY() + halfHeightLink) / 16);
        int colonne = (int) ((super.getX() + halfWidthLink) / 16);
        int tuile = matriceCollision.get(ligne).get(colonne);

        if (ligne!=37) {
            super.setY(super.getY() + 2);
        }

        else if (ligne==37) {
            if (tuile != -1){
                super.setX(super.getX() + 2);
            }
            else {
                super.setX(super.getX() + widthLink);
                this.setPrendBateauAller(false);
            }
        }
    }



    public void retourEnBateau() throws IOException {

        ObservableList<ObservableList<Integer>> matriceCollision = super.getEnv().getMatriceCollision().getMatrice();

        int ligne = (int) ((super.getY() + halfHeightLink) / 16);
        int colonne = (int) ((super.getX() + halfWidthLink) / 16);
        int tuile = matriceCollision.get(ligne - 1).get(colonne);

        if (colonne!=28) {
            super.setX(super.getX() - 2);
        }

        else if (colonne==28) {
            if (tuile != -1) {
                super.setY(super.getY() - 2);
            }
            else {
                super.setY(super.getY() - heightLink);
                this.setPrendBateauRetour(false);
            }
        }

    }



    public void attaqueDistance() {

        Baguette baguetteLink;
        int x = super.getX() + halfWidthLink;
        int y = super.getY() + halfHeightLink;

        baguetteLink = ((Baguette)super.getObjetEquipe());
        baguetteLink.lancerFireBall(super.getDirection(), x, y, "FireBall Link", super.getEnv());
    }



    public void attaqueCAC() throws IOException {

        Personnage ennemi;
        boolean ennemiPresent = false;
        int porteAtaque = ((ObjetAttaque) super.getObjetEquipe()).getPorteAtaque();

        if (super.getObjetEquipe() instanceof ObjetAttaque) {

            for (int i = 1; i < super.getEnv().getListePersonnages().size(); i++) {

                ennemi = super.getEnv().getListePersonnages().get(i);

                switch (this.getDirection()) {

                    case "Haut":
                        if (ennemi.getY() <= this.getY() && ennemi.getY() + heightEnnemis >= (this.getY() - porteAtaque)) {
                            if (Math.abs(((ennemi.getX()) + halfWidthEnnemis) - (this.getX() + halfWidthLink)) <= 16) {
                                ennemiPresent = true;
                            }
                        }
                        break;

                    case "Gauche":
                        if (ennemi.getX() <= this.getX() && ennemi.getX() + widthEnnemis >= (this.getX() - porteAtaque)) {
                            if(Math.abs((ennemi.getY() + halfHeightEnnemis) - (this.getY() + halfHeightLink)) <= 21) {
                                ennemiPresent = true;
                            }
                        }
                        break;

                    case "Bas":
                        if (ennemi.getY() + heightEnnemis >= this.getY() + heightLink && ennemi.getY() <= ((this.getY() + heightLink) + porteAtaque)) {
                            if(Math.abs((ennemi.getX() + halfWidthEnnemis) - (this.getX() + halfWidthLink)) <= 16){
                                ennemiPresent = true;
                            }
                        }
                        break;

                    case "Droite":
                        if (ennemi.getX() + widthEnnemis >= this.getX() + widthLink && ennemi.getX() <= ((this.getX() + widthLink) + porteAtaque)) {
                            if(Math.abs((ennemi.getY() + halfHeightEnnemis) - (this.getY() + halfHeightLink)) <= 21) {
                                ennemiPresent = true;
                            }
                        }
                        break;
                }

                if (ennemiPresent) {
                    ennemi.decrementerPv(((ObjetAttaque) super.getObjetEquipe()).getPointAttaqueValue());
                    if (ennemi.estMort()) this.incrementerEnnemiTuer();
                    System.out.println(ennemi);
                    ennemiPresent = false;
                }
            }

            if (!ennemiPresent) System.out.println("Pas d'ennemis à l'horizon");

            this.detruireTroncArbre();
            this.detruireArbre();
        }

    }


}