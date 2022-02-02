package application.controler.Listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ObservateurObjEquipe implements ChangeListener<String> {


    private Label atk;
    private ImageView imageObjEquipe;
    private Label objEquipe;



    public ObservateurObjEquipe(Label atk, ImageView imageObjEquipe, Label objEquipe) {
        this.atk = atk;
        this.imageObjEquipe = imageObjEquipe;
        this.objEquipe = objEquipe;
    }


    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        this.atk.setText(t1);

        this.objEquipe.setText(t1);

        this.imageObjEquipe.setImage(new Image("application/ressources/imageMapZelda/ArmeEtAccessoire/" + t1 + ".png"));
        this.imageObjEquipe.setFitWidth(28);
        this.imageObjEquipe.setFitHeight(28);
    }


}
