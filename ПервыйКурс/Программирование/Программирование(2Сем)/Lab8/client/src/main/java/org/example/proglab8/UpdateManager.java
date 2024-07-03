package org.example.proglab8;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import MainClasses.SpaceMarine;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class UpdateManager {

    Vector<SpaceMarine> marines = Commands.marines;

    static SpaceMarine spaceMarine;

    static Long id;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton Marines;

    @FXML
    void initialize() {
        for (SpaceMarine item : marines) {
            Marines.getItems().addAll(new MenuItem(String.valueOf(item.getId())));
        }
        ObservableList<MenuItem> objects = Marines.getItems();
        for (MenuItem menuItem : objects) {
            menuItem.setOnAction(actionEvent -> {
                spaceMarine = getById(Integer.parseInt(menuItem.getText()));
                id = Long.parseLong(menuItem.getText());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainPage.class.getResource("ObjectUpdater.fxml"));
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Parent root = fxmlLoader.getRoot();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();


            });

        }

    }


    private SpaceMarine getById(int id) {
        for (SpaceMarine item : marines) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }


}


