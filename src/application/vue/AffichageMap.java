package application.vue;

import application.modele.MatriceMap;
import application.modele.personnages.Personnage;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.ArrayList;



public class AffichageMap {

    private MatriceMap matriceMap;



    public AffichageMap(String PathFichierMatrice) throws IOException {

        this.matriceMap = new MatriceMap(PathFichierMatrice);
    }



    public MatriceMap getMatriceMap() {

        return this.matriceMap;
    }



    public void setMatriceMap(MatriceMap newMatriceMap) {

        this.matriceMap = newMatriceMap;
    }



    public String getPathMatrice() {

        return this.matriceMap.getPathFichierMatrice();
    }



    public void setPathMatrice(String newPath) {

        this.matriceMap.setPathFichierMatrice(newPath);
    }



    public void chargerMap(TilePane map) throws IOException {

        ObservableList<ObservableList<Integer>> matrice = this.matriceMap.getMatrice();

        Image tileSet = new Image("application/ressources/imageMapZelda/tileSetVolcanique3.png");
        ImageView image;

        for(int ligne = 0; ligne < matrice.size(); ligne++) {
            for (int colonne = 0; colonne < matrice.get(ligne).size() ; colonne++) {
                image = new ImageView(tileSet);
                Rectangle2D rect = new Rectangle2D(matrice.get(ligne).get(colonne) % 25 * 16, matrice.get(ligne).get(colonne) / 25 * 16, 16, 16);
                image.setViewport(rect);
                map.getChildren().add(image);
            }
        }
    }


}
