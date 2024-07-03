package org.example.proglab8;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import MainClasses.SpaceMarine;
import Managers.RegisterStatus;
import Network.Client;
import Network.Request;
import Network.Response;
import Network.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import Commands.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Commands {

    Client client = ApplicationClient.getClient();

    static Vector<SpaceMarine> marines;


    User user = MainPage.user;

    public static boolean isAdd = true;


    @FXML
    private ResourceBundle resources;
    @FXML
    private TextField Id;

    @FXML
    private URL location;

    @FXML
    private Button Add;

    @FXML
    private Button AddifMin;

    @FXML
    private Button Exit;

    @FXML
    private Button Help;

    @FXML
    private Button Info;

    @FXML
    private Button Clear;

    @FXML
    private Button Max_by_chapter;

    @FXML
    private Button PrintField;

    @FXML
    private Button Remove;

    @FXML
    private Button Sort;

    @FXML
    private Button Update;

    @FXML
    private TextArea ServerResponnse;

    @FXML
    void initialize() {
        Info.setOnAction(actionEvent -> {
            try {
                var response = client.sendRequest(new Request(new Info(), user));
                ServerResponnse.setText(response.getResult());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        Help.setOnAction(actionEvent -> {
            try {
                ServerResponnse.clear();
                var response = client.sendRequest(new Request(new Help(), user));
                ServerResponnse.setText(response.getResult());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        });

        Max_by_chapter.setOnAction(actionEvent -> {
            ServerResponnse.clear();
            try {
                var response = client.sendRequest(new Request(new MaxByChapter(), user));
                ServerResponnse.setText(response.getResult());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Exit.setOnAction(actionEvent -> {
            System.exit(0);


        });

        PrintField.setOnAction(actionEvent -> {
            ServerResponnse.clear();
            try {
                var response = client.sendRequest(new Request(new PrintFieldAscendingCategory(), user));
                ServerResponnse.setText(response.getResult());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Add.setOnAction(actionEvent -> {
            isAdd = true;
            showChildWindow("SpaceMarineCreation.fxml");


        });
        AddifMin.setOnAction(actionEvent -> {
            isAdd = false;
            showChildWindow("SpaceMarineCreation.fxml");


        });
        Sort.setOnAction(actionEvent -> {
            ServerResponnse.clear();

            try {
                var response = client.sendRequest(new Request(new Sort(), user));
                ServerResponnse.setText(response.getResult());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        Remove.setOnAction(actionEvent -> {
            showChildWindow("Remove.fxml");


        });

        Update.setOnAction(actionEvent -> {
            try {
                marines = client.sendRequest(new Request(new Marines(), user)).getMarines();
                showChildWindow("Update.fxml");


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        Clear.setOnAction(actionEvent -> {
            try {
                var response = client.sendRequest(new Request(new Clear(), user));
                ServerResponnse.setText(response.getResult());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void showChildWindow(String resource) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Commands.class.getResource(resource));
            fxmlLoader.load();
            Parent root = fxmlLoader.getRoot();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
