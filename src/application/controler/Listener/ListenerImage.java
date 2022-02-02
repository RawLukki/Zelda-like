package application.controler.Listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ListenerImage implements ChangeListener<String> {

    ImageView image;

    public ListenerImage(ImageView image) {
        this.image = image;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            this.image.setImage(new Image("application/ressources/imageMapZelda/Serpent/Snake" + t1 + ".gif"));
    }


}
