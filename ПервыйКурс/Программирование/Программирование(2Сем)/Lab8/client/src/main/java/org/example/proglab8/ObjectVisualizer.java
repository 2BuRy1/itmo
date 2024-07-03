package org.example.proglab8;

import MainClasses.SpaceMarine;
import Network.Client;
import Network.Request;
import Commands.AllMarines;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class ObjectVisualizer {

    Client client = ApplicationClient.getClient();

    private Vector<SpaceMarine> marines = client.sendRequest(new Request(new AllMarines(), MainPage.user)).getMarines();

    private Vector<SpaceMarine> marinesCopy = client.sendRequest(new Request(new AllMarines(), MainPage.user)).getMarines();


    public ObjectVisualizer() throws InterruptedException {
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea info;



    @FXML
    private AnchorPane Pane;

    @FXML
    void initialize() throws InterruptedException {

        drawMarine(marines);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {


            try {
                marines = client.sendRequest(new Request(new AllMarines(), MainPage.user)).getMarines();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!marines.equals(marinesCopy)) {
                Pane.getChildren().clear();
                drawMarine(marines);
                marinesCopy = marines;
            }


        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        if(!MainPage.flag){
            timeline.stop();
        }
    }


    private void drawMarine(Vector<SpaceMarine> marines) {
        var y = 40;
        var x = 100;


        for (var e : marines) {

            if (y >= 360) {
                x += 100;
                y = 40;
            }
            Circle circle = new Circle(25);
            circle.setCenterX(x);
            circle.setCenterY(y);
            if (e.getUserLogin().equals(MainPage.user.getLogin())) {
                circle.setFill(Color.CADETBLUE);
            } else {
                circle.setFill(Color.PINK);
            }
            Pane.getChildren().add(circle);
            y += 50;
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                info.setText(e.toString());
            });


        }
    }

}
