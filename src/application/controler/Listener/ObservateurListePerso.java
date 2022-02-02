package application.controler.Listener;

import application.modele.personnages.Personnage;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurListePerso implements ListChangeListener<Personnage> {

    private Pane pane;



    public ObservateurListePerso(Pane pane) {
        this.pane = pane;
    }



    @Override
    public void onChanged(Change<? extends Personnage> change) {

        while (change.next()) {

            for (Personnage personnage : change.getRemoved()) {
                enleverImage(personnage);
            }
        }
    }



    public  void enleverImage(Personnage personnage) {
        this.pane.getChildren().remove(this.pane.lookup("#" + personnage.getName()));
    }


}
