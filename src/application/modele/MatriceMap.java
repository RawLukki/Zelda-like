package application.modele;

import application.modele.personnages.Personnage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Grâce au path d'un fichier dans lequel est contenu une matrice
 * crée une double ArrayList d'integer
 */

public class MatriceMap {

    private String pathFichierMatrice;
    private ObservableList<ObservableList<Integer>> matrice;


    /**
     * Prend le path d'un fichier
     * Utilise la methode readAllLines de la classe Files pour lire ligne par ligne le fichier
     * @param pathFichierMatrice
     * @throws IOException
     */
    public MatriceMap(String pathFichierMatrice) throws IOException {
        this.pathFichierMatrice = pathFichierMatrice;
        this.matrice = FXCollections.observableArrayList();

        ObservableList<Integer> ligneMatrice;

        for (String ligne : Files.readAllLines(Paths.get(this.pathFichierMatrice))) {
            ligneMatrice = FXCollections.observableArrayList();
            for (String chaine : ligne.split(",")) {
                Integer entier = Integer.valueOf(chaine);
                ligneMatrice.add(entier);
            }
            this.matrice.add(ligneMatrice);
        }
    }



    public String getPathFichierMatrice() {
        return this.pathFichierMatrice;
    }



    public void setPathFichierMatrice(String path) {
        this.pathFichierMatrice = path;
    }



    public ObservableList<ObservableList<Integer>> getMatrice() throws IOException {

        return this.matrice;
    }



    public void setMatrice(ObservableList<ObservableList<Integer>> matrice) {
        this.matrice = matrice;
    }


}
