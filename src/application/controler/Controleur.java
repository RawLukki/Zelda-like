package application.controler;

import application.controler.Listener.*;
import application.modele.Jeu;
import application.vue.*;
import application.vue.vuePersonnages.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controleur implements Initializable {

    // La partie //
    private Jeu zelda = new Jeu();

    // Boucle du jeu //
    private Timeline gameLoop;
    private IntegerProperty tour = new SimpleIntegerProperty(0);

    // vue de la map //
    private AffichageMap affichageMapZelda;
    private AffichageMap affichageMapZeldaColision;

    // vue des personnages //
    private AfficherGobelin imageGobelin1;
    private AfficherGobelin imageGobelin2;
    private AfficherGobelin imageGobelin3;
    private AfficherBoss imageGardien;
    private AfficherLink imageLink;
    private AfficherSerpent imageSerpent1;
    private AfficherSerpent imageSerpent2;
    private AfficherSerpent imageSerpent3;
    private AfficherSorcier imageSorcier1;
    private AfficherSorcier imageSorcier2;


    // FX:ID du FXML //

    @FXML
    private BorderPane borderPane;

    @FXML
    private Pane pane;

    @FXML
    private TilePane calque1;

    @FXML
    private TilePane calque2;

    @FXML
    private HBox inventaire;

    @FXML
    private Pane ecranFIN;

    @FXML
    private ImageView coeur1;

    @FXML
    private ImageView coeur2;

    @FXML
    private ImageView coeur3;

    @FXML
    private ImageView coeur4;

    @FXML
    private ImageView imageArme;

    @FXML
    private ImageView imageBague;

    @FXML
    private Label labelObjEquipe;

    @FXML
    private Label labelBaguette;

    @FXML
    private Label labelBouclier;

    @FXML
    private Label labelBois;

    @FXML
    private Label labelPV;

    @FXML
    private Label labelATK;

    @FXML
    private Label labelDEF;

    @FXML
    private Label labelEnnemisTuer;

    private TouchesClavier eventHandler;



    public Controleur() throws IOException {

    }


    /**
     * Appelé au lancement du programme
     * implémente la plus part des binds et listeners du projet
     * lance la gameloop
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.gameLoop = new Timeline();

        try {

            initialisationMAP();

            this.eventHandler = new TouchesClavier(this.zelda, this.imageLink, this.inventaire, this.tour);
            this.pane.setFocusTraversable(true);
            this.pane.setOnKeyPressed(eventHandler);

            // Listener pour supprimer ou afficher les spites des fireBalls //
            ListenerFireBall l1 = new ListenerFireBall(this.pane);
            this.zelda.getEnv().getListeFireBall().addListener(l1);

            // Listener pour supprimer les spites des ennemis //
            ObservateurListePerso l2 = new ObservateurListePerso(this.pane);
            this.zelda.getEnv().getListePersonnages().addListener(l2);

            // 2 Listeners qui permetent d'actualiser la map lorsqu'elle change //
            ListenerLigneMap listenerLigne = new ListenerLigneMap(this.zelda, this.calque2, this.affichageMapZeldaColision);
            ListenerColonneMap listenerColonneMap = new ListenerColonneMap(this.zelda, this.calque2, this.affichageMapZeldaColision);
            this.zelda.getEnv().getMatriceCollision().getMatrice().addListener(listenerLigne);
            for (int ligne = 0; ligne < this.zelda.getEnv().getMatriceCollision().getMatrice().size(); ligne++) {
                this.zelda.getEnv().getMatriceCollision().getMatrice().get(ligne).addListener(listenerColonneMap);
            }

            // Listener qui affiche ou enlève les coeurs de LINK //
            ObservateurPVLink l3 = new ObservateurPVLink(coeur1, coeur2, coeur3, coeur4, labelPV, zelda.getEnv().getLink());
            this.zelda.getEnv().getPersonnage("Link").getPtsDeVieProperty().addListener(l3);

            // listener qui affiche si tel objet est posséder dans l'INVENTAIRE //
            ObservateurListeObjet l4 = new ObservateurListeObjet(this.pane, this.labelBaguette, this.labelBouclier, this.imageBague);
            this.zelda.getEnv().getLink().getListeObjet().addListener(l4);

            // listener qui écoute les changements d'objet équipé //
            ObservateurObjEquipe l5 = new ObservateurObjEquipe(this.labelATK, this.imageArme, this.labelObjEquipe);
            this.zelda.getEnv().getPersonnage("Link").getNomObjetEquipeProperty().addListener(l5);

            // Listener qui écoute les changements d'att du perso lié aux dégâts d'arme //
            ChangeListener<Number> l6 = ((obs, old, nouv) -> this.labelATK.setText("" + nouv));
            this.zelda.getEnv().getPersonnage("Link").getPointAtkArmeProperty().addListener(l6);

            // BIND entre la DEF de LINK et le label DEF de l'INVENTAIRE //
            this.labelDEF.textProperty().bind(this.zelda.getEnv().getLink().getDefProperty().asString());

            // BIND entre le nbr de BOIS posséder par LINK et le label BOIS de l'INVENTAIRE //
            this.labelBois.textProperty().bind(this.zelda.getEnv().getLink().getBoisProperty().asString());

            // BIND entre le nbr d'ennemis tué par LINK et le label ennemis tué de l'INVENTAIRE //
            this.labelEnnemisTuer.textProperty().bind(this.zelda.getEnv().getLink().getNbrEnnemiTuerProperty().asString());

            initAnimation();

            this.gameLoop.play();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Permet de creer l'affichage de la map + tous les personnages de la map
     * fait pour ne pas surcharger l'initialize
     * @throws IOException
     */
    public void initialisationMAP() throws IOException {

        this.affichageMapZelda = new AffichageMap("src/application/ressources/mapZeldaSansCollision(3).txt");
        this.affichageMapZeldaColision = new AffichageMap("src/application/ressources/mapZeldaAvecCollision(4).txt");

        try {
            this.imageGobelin1 = new AfficherGobelin(this.zelda.getEnv().getPersonnage("Gobelin1"), this.pane);
            this.imageGobelin2 = new AfficherGobelin(this.zelda.getEnv().getPersonnage("Gobelin2"), this.pane);
            this.imageGobelin3 = new AfficherGobelin(this.zelda.getEnv().getPersonnage("Gobelin3"), this.pane);
            this.imageGardien = new AfficherBoss(this.zelda.getEnv().getPersonnage("Guardian"), this.pane);
            this.imageSerpent1 = new AfficherSerpent(this.zelda.getEnv().getPersonnage("Serpiente1"), this.pane);
            this.imageSerpent2 = new AfficherSerpent(this.zelda.getEnv().getPersonnage("Serpiente2"), this.pane);
            this.imageSerpent3 = new AfficherSerpent(this.zelda.getEnv().getPersonnage("Serpiente3"), this.pane);
            this.imageSorcier1 = new AfficherSorcier(this.zelda.getEnv().getPersonnage("Sorcier1"), this.pane);
            this.imageSorcier2 = new AfficherSorcier(this.zelda.getEnv().getPersonnage("Sorcier2"), this.pane);
            this.imageLink = new AfficherLink(this.zelda.getEnv().getPersonnage("Link"), this.pane);

            this.affichageMapZelda.chargerMap(this.calque1);
            this.affichageMapZeldaColision.chargerMap(this.calque2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    /**
     * Permet simplement de mettre les images de link lorsqu'il s'arrête de marcher
     */
    public void sarreter() {
        this.borderPane.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                imageLink.changerImage("fixe");
            }
        });
    }



    /**
     * Méthode qui décrit ce que fait la gameloop
     * la methode jouer de la classe zelda qui permet de faire un tour de jeu
     * elle est appelé toute les 17ms;
     * @throws IOException
     */

    private void initAnimation() throws IOException {

        this.gameLoop = new Timeline();
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),  //0.017 pour la fluidité
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev -> {

                    if (zelda.getPartieFINI()) {
                        this.ecranFIN.setVisible(true);
                        this.gameLoop.stop();
                    }

                    else {
                        try {

                            if (zelda.getEnv().getLink().getPtsDeVieValue() <= 0) {
                                pane.getScene().getWindow().hide();
                            }

                            zelda.unTour();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (tour.getValue() < 60) tour.setValue(tour.getValue()+1);

                }));

        this.gameLoop.getKeyFrames().add(kf);
    }


}