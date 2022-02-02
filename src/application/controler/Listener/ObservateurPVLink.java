package application.controler.Listener;

import application.modele.personnages.Link;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ObservateurPVLink implements ChangeListener<Number> {


    private ImageView coeur1;
    private ImageView coeur2;
    private ImageView coeur3;
    private ImageView coeur4;



    public ObservateurPVLink(ImageView c1, ImageView c2, ImageView c3, ImageView c4, Label pv, Link link) {
        this.coeur1 = c1;
        this.coeur2 = c2;
        this.coeur3 = c3;
        this.coeur4 = c4;
        pv.textProperty().bind(link.getPtsDeVieProperty().asString());
    }



    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        int oldPV = (int)oldValue;
        int newPV = (int)newValue;

        if (newPV < oldPV) {
            if (newPV < 225) this.coeur4.setVisible(false);
            if (newPV < 150) this.coeur3.setVisible(false);
            if (newPV < 75) this.coeur2.setVisible(false);
            if (newPV <= 0) this.coeur1.setVisible(false);
        }

        else {
            if (newPV > 0) this.coeur1.setVisible(true);
            if (newPV > 75) this.coeur2.setVisible(true);
            if (newPV > 150) this.coeur3.setVisible(true);
            if (newPV > 225) this.coeur4.setVisible(true);
        }

    }


}
