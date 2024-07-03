package org.example.proglab8;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import Managers.RegisterStatus;
import Network.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainPage {
  public static User user = RegisterStatus.isLoggin == false ? RegisterWindow.user : LoginWindow.user;

  static Locale locale = RegisterStatus.isLoggin == false ? RegisterWindow.locale : LoginWindow.locale;

  public static boolean flag = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton Menu;

    @FXML
    private TextField UserField;

    @FXML
    void initialize() {

        if(locale!=null) {
            resources = ResourceBundle.getBundle("org.example.proglab8.l", locale);
        }
        else {
            locale = Locale.getDefault();
            resources = ResourceBundle.getBundle("org.example.proglab8.l", locale);
        }

        UserField.setText(user.getLogin());
        MenuItem commands = new MenuItem(resources.getString("Command_Section"));
        MenuItem tables = new MenuItem(resources.getString("Table_Section"));
        MenuItem canvas = new MenuItem(resources.getString("Canvas_Section"));
        Menu.setText(resources.getString("Menu"));
        Menu.getItems().addAll(commands, tables, canvas);

        commands.setOnAction(actionEvent -> {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainPage.class.getResource("Commands.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("org.example.proglab8.l", locale));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = fxmlLoader.getRoot();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();

        });

        tables.setOnAction(actionEvent -> {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainPage.class.getResource("Table.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("org.example.proglab8.l", locale));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = fxmlLoader.getRoot();
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();



        });

        canvas.setOnAction(actionEvent -> {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainPage.class.getResource("ObjectVisualizer.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("org.example.proglab8.l", locale));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            if(!stage.isShowing()){
                stage.close();
                flag=false;
            }
        });



    }

}
