package Managers;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition translateTransition;


    public Shake (Node node){

        translateTransition = new TranslateTransition(Duration.millis(50), node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        translateTransition.playFromStart();
    }


}
