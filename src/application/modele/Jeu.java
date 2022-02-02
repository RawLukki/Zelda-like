package application.modele;

import application.modele.personnages.*;

import java.io.IOException;


/**
 * La classe jeu possède un environnement qui représente une map
 * une methode jouer qui permet de faire un tour de jeu
 */

public class Jeu {

    private int tour;
    private Environnement env;
    private boolean partieFINI;



    public Jeu() throws IOException {
        this.env = new Environnement(768, 640);
        this.tour = 0;
        this.partieFINI = false;
        this.env.initialiserJeu();
    }



    public Environnement getEnv() {
        return env;
    }


    // Pour pour changer de map même si il n'y en a qu'une //
    public void setEnv(Environnement env) {
        this.env = env;
    }



    // permet à ce qui est en dehors du modèle de savoir si la partie est fini //
    public boolean getPartieFINI() {
        return this.partieFINI;
    }


    /**
     * Si la partie n'est pas fini :
     * enlève les personnages du jeu s'ils sont morts
     * fait avancer les serpents
     * fait attaquer les ennemis toutes les 2 secondes
     * active le feu de camp
     * @throws IOException
     */
    public void unTour() throws IOException {

        if (!this.env.getLink().getTriForce()) {

            for (int i = this.env.getListePersonnages().size() - 1; i >= 0; i--) {
                Personnage personnage = this.env.getListePersonnages().get(i);
                if (personnage.estMort()) {
                    this.env.getListePersonnages().remove(personnage);
                }
            }

            ((Serpent)this.env.getPersonnage("Serpiente1")).garderDonjon();
            ((Serpent)this.env.getPersonnage("Serpiente2")).garderDonjon();
            ((Serpent)this.env.getPersonnage("Serpiente3")).garderDonjon();


            for (int i = 1; i <= 3; i++) {

                if (!this.env.ennemisVaincu("Gobelin" + i)) {
                    ((Gobelin) this.env.getPersonnage("Gobelin" + i)).attaqueCAC();
                }

                if (!this.env.ennemisVaincu("Serpiente" + i)) {
                    ((Serpent) this.env.getPersonnage("Serpiente" + i)).attaqueCAC();
                }
            }

            for (int i = 1; i <= 2; i++) {

                if (!this.env.ennemisVaincu("Sorcier" + i)) {
                    ((Sorcier) this.env.getPersonnage("Sorcier" + i)).attaqueDistance();
                }
            }

            if (!this.env.ennemisVaincu("Guardian")) {
                ((Boss) this.env.getPersonnage("Guardian")).attaqueCAC();
            }


            if (this.tour == 60) {
                this.getEnv().getLink().feuDeCamp();
                this.tour = 0;
            }

            else this.tour++;


            this.getEnv().cibleTouche();
            this.env.removeFireBall();


            if (this.getEnv().getLink().getPrendBateauAller()) {
                try {
                    this.getEnv().getLink().allerEnBateau();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (this.getEnv().getLink().getPrendBateauRetour()) {
                try {
                    this.getEnv().getLink().retourEnBateau();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        else {
            this.partieFINI = true;
            getEnv().viderEnvironnement();
        }
    }


}
