package application.modele;

import application.modele.objet.FireBall;
import application.modele.personnages.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import static application.modele.Paramètre.*;

/**
 * Classe qui permet de gérer certains éléments de l'environnement :
 * rechercher un personnage précisement
 * vérifier les colisions
 * si un personnage est en dehors du terrain
 * Si un ennemi est vaincu
 */

public class Environnement {

    private int width;
    private int height;
    private ObservableList<Personnage> listePersonnages;
    private ObservableList<FireBall> listeFireBall;
    private MatriceMap matriceCollision; // represente toutes les colision de la map //



    public Environnement(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.listePersonnages = FXCollections.observableArrayList();
        this.listeFireBall = FXCollections.observableArrayList();
        this.matriceCollision = new MatriceMap("src/application/ressources/mapZeldaAvecCollision(4).txt");
    }



    public int getWidth() {
        return width;
    }



    public int getHeight() {
        return height;
    }



    public void setWidth(int width) {
        this.width = width;
    }



    public void setHeight(int height) {
        this.height = height;
    }



    public MatriceMap getMatriceCollision() {
        return this.matriceCollision;
    }



    public  ObservableList<Personnage> getListePersonnages() {
        return this.listePersonnages;
    }



    public void setListePersonnages(ObservableList<Personnage> newListe) {
        this.listePersonnages = newListe;
    }



    public ObservableList<FireBall> getListeFireBall() {
        return this.listeFireBall;
    }



    public void addFireBall(FireBall newFireBall) {
        this.listeFireBall.add(newFireBall);
    }



    public void removeFireBall() {
        for (int i = this.listeFireBall.size()-1; i >= 0 ; i--) {
            if (this.getListeFireBall().get(i).finDeVie()) {
                this.listeFireBall.remove(this.getListeFireBall().get(i));
            }
        }
    }



    public Personnage getPersonnage(String nom) {
        for (Personnage personnage : this.listePersonnages) {
            if (personnage.getName().equals(nom)) {
                return personnage;
            }
        }

        return null;
    }



    public Link getLink() {
        for (Personnage personnage : this.listePersonnages) {
            if (personnage instanceof Link) {
                return (Link) personnage;
            }
        }

        return null;
    }



    public void addPersonnage(Personnage personnage) {
        this.listePersonnages.add(personnage);
    }



    public void removePersonnage(String nom) {
        for (Personnage personnage : this.listePersonnages) {
            if (personnage.getName().equals(nom)) {
                this.listePersonnages.remove(personnage);
            }
        }
    }


    // place tous les personnages aux bons endroits //
    public void initialiserJeu() throws IOException {

        // Le personnage principale //
        addPersonnage(new Link(this, 674, 448));
        //addPersonnage(new Link(this, 500, 400)); // test -> à supprimer //

        // Le Gardien (Le Boss final) //
        //this.env.addPersonnage(new Boss(this.env, 160, 64));
        addPersonnage(new Boss(this, 160, 104, "Bas"));


        // Les Gobelins //
        addPersonnage(new Gobelin(this, 64, 496, "Droite"));
        addPersonnage(new Gobelin(this, 160, 540, "Droite"));
        addPersonnage(new Gobelin(this, 644, 96, "Bas"));

        // Les sorciers //
        addPersonnage(new Sorcier(this, 710, 200, "Gauche"));
        addPersonnage(new Sorcier(this, 576, 140, "Droite"));

        // Les serpents //
        addPersonnage(new Serpent(this, 80, 340, "Droite"));
        addPersonnage(new Serpent(this, 330, 360, "Droite"));
        addPersonnage(new Serpent(this, 285, 490, "Haut"));

    }



    // Enlève tous les personnages et toutes les boules de feu du jeu //
    public void viderEnvironnement() {
        for (int i = this.listePersonnages.size() - 1; i >= 0; i--) {
            Personnage personnage = this.listePersonnages.get(i);
            this.listePersonnages.remove(personnage);
        }

        for (int i = this.listeFireBall.size() - 1; i >= 0; i--) {
            FireBall fireBall = this.listeFireBall.get(i);
            this.listeFireBall.remove(fireBall);
        }
    }



    // permet de verifier si un ennemis à été vaincu comme ils sont directement enlevé du jeu une fois mort //
    public boolean ennemisVaincu(String name) {

        boolean ennemisVaincu = true;

        for(Personnage personnageListe : this.listePersonnages) {
            if (personnageListe.getName().equals(name)) {
                ennemisVaincu = false;
                break;
            }
        }

        return ennemisVaincu;
    }


    /**
     * Parcours la liste de boule de feu
     * regarde à qui appartient la boule de feu
     * et en fontion vérifie si un personnage à été touché
     */
    public void cibleTouche() {

        FireBall fireBall;
        Personnage ennemi;
        boolean aucunEnnemisTouche = true;
        Personnage link = this.getPersonnage("Link");

        for (int objet = this.listeFireBall.size() - 1; objet >= 0; objet--) {

            fireBall = ((FireBall) this.listeFireBall.get(objet));

            if (fireBall.getType().equals("FireBall Link")) {

                for (int indEnnemi = 1 ; indEnnemi < this.listePersonnages.size(); indEnnemi++) {

                    ennemi = this.listePersonnages.get(indEnnemi);
                    if (fireBall.toucher(ennemi)) {
                        System.out.println("Ennemis Touché");
                        System.out.println(ennemi.getPtsDeVieValue());
                        this.listeFireBall.remove(fireBall);
                        if (ennemi.estMort()) this.getLink().incrementerEnnemiTuer();
                        aucunEnnemisTouche = false;
                        break;
                    }
                }
                if (aucunEnnemisTouche) fireBall.deplacerFireBall();
            }

            else {
                if (fireBall.toucher(link)) {
                    System.out.println("Link Touché");
                    System.out.println(link.getPtsDeVieValue());
                    this.listeFireBall.remove(fireBall);
                    aucunEnnemisTouche = false;
                }

                if (aucunEnnemisTouche) fireBall.deplacerFireBall();

            }

        }

    }


    /**
     * fait un deplacement "virtuelle" de link
     * en fonction de la direction envoyé
     * et vérifie si le link entre en colision avec un élément du décors
     * @throws IOException
     */
    public boolean collision(String direction) throws IOException {

        int ligne1;
        int ligne2;
        int colonne1;
        int colonne2;

        Personnage link = this.getPersonnage("Link");
        ObservableList<ObservableList<Integer>> matrice = this.matriceCollision.getMatrice();

        switch (direction) {

            case "Haut":
                ligne1 = (int) (((link.getY() + halfHeightLink) - 2) / 16);
                colonne1 = (int) (link.getX() / 16);
                colonne2 = (int) ((link.getX() + halfWidthLink) / 16);
                return (matrice.get(ligne1).get(colonne1) == -1 && matrice.get(ligne1).get(colonne2) == -1);

            case "Gauche":
                ligne1 = (int) ((link.getY() + halfHeightLink) / 16);
                ligne2 = (int) ((link.getY() + heightLink) / 16);
                colonne1 = (int) ((link.getX() - 2) / 16);
                return (matrice.get(ligne1).get(colonne1) == -1 && matrice.get(ligne2).get(colonne1) == -1);

            case "Bas":
                ligne1 = (int) (((link.getY() + heightLink) + 2) / 16);
                colonne1 = (int) (link.getX() / 16);
                colonne2 = (int) ((link.getX() + widthLink) / 16);
                return (matrice.get(ligne1).get(colonne1) == -1 && matrice.get(ligne1).get(colonne2) == -1);

            case "Droite":
                ligne1 = (int) ((link.getY() + halfHeightLink) / 16);
                ligne2 = (int) ((link.getY() + heightLink) / 16);
                colonne1 = (int) (((link.getX() + widthLink) + 2) / 16);
                return (matrice.get(ligne1).get(colonne1) == -1 && matrice.get(ligne2).get(colonne1) == -1);

        }

        return false;
    }



    public boolean dansTerrain(int x, int y) {
        return (0 <= x && x <= this.width && y >= 0 && y <= this.height);
    }


}
